package com.qihoo.oxpecker.core.rpc.netty.outbox;

import com.qihoo.oxpecker.core.rpc.adddress.RpcAddress;
import com.qihoo.oxpecker.core.rpc.netty.NettyRpcEnv;
import org.apache.spark.network.client.TransportClient;

import javax.annotation.concurrent.GuardedBy;
import java.util.LinkedList;
import java.util.concurrent.Future;


/**
 * Created by chengguangqing on 2018/5/7.
 */
public class OutBox {

    private NettyRpcEnv nettyRpcEnv;
    private RpcAddress address;


    @GuardedBy("this")
    private LinkedList<OutboxMessage> messages = new LinkedList<OutboxMessage>();

    @GuardedBy("this")
    private TransportClient client = null;

    @GuardedBy("this")
    private Future connectFuture = null;

    @GuardedBy("this")
    private boolean stopped = false;

    @GuardedBy("this")
    private boolean draining = false;


    public OutBox(NettyRpcEnv nettyRpcEnv, RpcAddress address){
        this.nettyRpcEnv = nettyRpcEnv;
        this.address = address;
    }


    /**
     * Send a message. If there is no active connection, cache it and launch a new connection. If
     * [[Outbox]] is stopped, the sender will be notified with a [[SparkException]].
     */
    public void send(OutboxMessage message){
        boolean dropped = false;
        synchronized (this){
            if(stopped){
                dropped = true;
            }
            else{
                messages.add(message);
                dropped = false;
            }
        }
        if(dropped){
            //new SparkException("Message is dropped because Outbox is stopped")
            message.onFailure(new Exception("Message is dropped because Outbox is stopped"));
        }
        else{
            drainOutbox();
        }
    }

    /**
     * Drain the message queue. If there is other draining thread, just exit. If the connection has
     * not been established, launch a task in the `nettyEnv.clientConnectionExecutor` to setup the
     * connection.
     */
    private void drainOutbox(){
        OutboxMessage message = null;

        synchronized(this) {
            if (stopped) {
                return;
            }
            if (connectFuture != null) {
                // We are connecting to the remote address, so just exit
                return;
            }
            if (client == null) {
                // There is no connect task but client is null, so we need to launch the connect task.
                launchConnectTask();
                return;
            }
            if (draining) {
                // There is some thread draining, so just exit
                return;
            }
            message = messages.poll();
            if (message == null) {
                return;
            }
            draining = true;
        }

        while (true){
            try{
                synchronized (client) {
                    if(client != null){
                        message.sendWith(client);
                    } else{
                        assert(stopped == true);
                    }
                }
            } catch (Throwable e){
                if(javaNonFatal(e)){
                    handleNetworkFailure(e);
                    return;
                }
            }

            synchronized (this){
                if(stopped){
                    return;
                }
                message = messages.poll();
                if(message == null){
                    draining = false;
                    return;
                }
            }
        }
    }


    private void handleNetworkFailure(Throwable e){
        synchronized (this){
            assert (connectFuture == null);
            if(stopped){
               return;
            }
            stopped = true;
            closeClient();
        }

        // Remove this Outbox from nettyEnv so that the further messages will create a new Outbox along
        // with a new connection
        //nettyEnv.removeOutbox(address)

        OutboxMessage message = messages.poll();

        while(message != null){
            message.onFailure(e);
            message = messages.poll();
        }

        assert (messages.isEmpty());
    }

    private void closeClient(){
        synchronized (this){
            // Just set client to null. Don't close it in order to reuse the connection.
            client = null;
        }
    }

    private void launchConnectTask(){

    }

    //Java版本的NonFatal
    private boolean javaNonFatal(Throwable e){
        if( e instanceof StackOverflowError){
            return true;
        }
        if(e instanceof VirtualMachineError
                || e instanceof ThreadDeath
                || e instanceof InterruptedException
                || e instanceof LinkageError
                //|| e instanceof ControlThrowable
                //|| e instanceof NotImplementedError
                                                    ){
            return  false;
        }
        return true;
    }

    /**
     * Stop [[Outbox]]. The remaining messages in the [[Outbox]] will be notified with a
     * [[SparkException]].
     */
    public void stop(){
        synchronized (this){
            if(stopped){
                return;
            }
            else{
                stopped = true;
                if(connectFuture != null){
                    connectFuture.cancel(true);
                }
                closeClient();
            }
        }

        // We always check `stopped` before updating messages, so here we can make sure no thread will
        // update messages and it's safe to just drain the queue.

        OutboxMessage message = messages.poll();
        while(message != null){
            message.onFailure(new Exception("Message is dropped because Outbox is stopped"));
            message = messages.poll();
        }
    }

}
