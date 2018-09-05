package com.qihoo.oxpecker.core.rpc.netty.inbox;

/**
 * Created by chengguangqing on 2018/4/30.
 */

import com.qihoo.oxpecker.core.rpc.RpcCallContext;
import com.qihoo.oxpecker.core.rpc.netty.dispatcher.Dispatcher;
import com.qihoo.oxpecker.core.rpc.rpcEndpoint.RpcEndpoint;
import com.qihoo.oxpecker.core.rpc.netty.NettyRpcEndpointRef;

import javax.annotation.concurrent.GuardedBy;
import java.util.LinkedList;

/**
 * An inbox that stores messages for an [[RpcEndpoint]] and posts messages to it thread-safely.
 */
public class Inbox {
    public NettyRpcEndpointRef endpointRef;
    public RpcEndpoint endpoint;

    public Inbox(NettyRpcEndpointRef endpointRef,
                 RpcEndpoint endpoint){
        this.endpointRef = endpointRef;
        this.endpoint = endpoint;
    }



    @GuardedBy("this")
    protected LinkedList<InboxMessage> messages = new LinkedList<InboxMessage>();

    @GuardedBy("this")
    private boolean stopped = true;

    @GuardedBy("this")
    private boolean enableConcurrent =false;

    @GuardedBy("this")
    private int numActiveThreads = 0;


    // OnStart should be the first message to process
    //inbox.synchronized {
    //    messages.add(OnStart)
    //}

    //不确定是否在正确
    {
        synchronized (this){
            messages.add(new OnStart());
        }
    }

    /**
     * Process stored messages.
     */
    public void process(Dispatcher dispatcher){
        InboxMessage message = null;
        synchronized (this) {
            if(!enableConcurrent && numActiveThreads != 0){
                return;
            }
            message = messages.poll();
            if(message != null){
                numActiveThreads += 1;
            }
            else {
                return;
            }
        }

        while(true){
            if(message instanceof RpcMessage){
                try{
                    RpcMessage newMessage = (RpcMessage) message;
                    endpoint.receiveAndReply((RpcCallContext) newMessage.context);
                }
                catch (Throwable e){
                }
            }

            if(message instanceof OneWayMessage){

            }

            if(message instanceof  OnStart){

            }

            if(message instanceof OnStop){

            }

            if(message instanceof RemoteProcessConnected){

            }

            if(message instanceof RemoteProcessDisconnected){

            }

            if(message instanceof RemoteProcessConnectionError){

            }

        }
    }

    public synchronized void stop(){

    }



    private void safelyCall(RpcEndpoint endpoint){

    }


    private Throwable messageMatch(InboxMessage message){
        try{

            RpcMessage newMessage = (RpcMessage) message;
            endpoint.receiveAndReply((RpcCallContext) newMessage.context);


        } catch (Throwable e){
            return e;
        }

        return null;
    }

}
