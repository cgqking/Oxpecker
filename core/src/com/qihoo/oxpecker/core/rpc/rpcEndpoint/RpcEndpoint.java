package com.qihoo.oxpecker.core.rpc.rpcEndpoint;

import com.qihoo.oxpecker.core.rpc.adddress.RpcAddress;
import com.qihoo.oxpecker.core.rpc.RpcCallContext;
import com.qihoo.oxpecker.core.rpc.rpcEnv.RpcEnv;

/**
 * Created by chengguangqing on 2018/3/30.
 */
public class RpcEndpoint {

    /**
     * The [[RpcEnv]] that this [[RpcEndpoint]] is registered to.
     */
    public RpcEnv rpcEnv = null;

    public final RpcEndpointRef self(){
        if(rpcEnv != null){
            System.out.println("rpcEnv has not been initialized");
        }
      return rpcEnv.endpointRef(this);
    }

    /**
     *  处理来自“RpcEndpointRef.send”或“RpcCallContext.reply”。如果接收到未匹配的消息，将抛出“SparkException”并发送到“onError”。
     */
    public Exception receive(){
        return new Exception(this.self() + " does not implement 'receive'");
    }



    /**
     * 处理来自“RpcEndpointRef.ask”。如果接收到未匹配的消息，将抛出“SparkException”并发送到“onError”。
     */
    public void receiveAndReply(RpcCallContext context){
        context.snedFailure(new Exception("won't replay anything"));
    }

    /**
     * 在处理消息期间抛出任何异常时调用。
     */
    public void onError(Throwable cause) throws Throwable {
        throw cause;
    }

    /**
     * 当“remoteAddress”连接到当前节点时调用。
     */
    public void onConnected(RpcAddress remoteAddress){
        //默认情况下，不做任何处理
    }

    /**
     * 当“remoteAddress”连接丢失时调用。
     */
    public void onDisconnected(RpcAddress remoteAddress){
        //默认情况下，不做任何处理
    }


    /**
     * 当“remoteAddress”连接丢失时调用。
     */
    public void onNetworkError(Throwable cause, RpcAddress remoteAddress){
        //默认情况下，不做任何处理
    }

    /**
     *[[RpcEndpoint]]开始处理任何消息。
    */
    public void onStart(){
        //默认情况下，不做任何处理
    }

    /**
     * Invoked when [[RpcEndpoint]] is stopping. `self` will be `null` in this method and you cannot use it to send or ask messages.
     * 当[[RpcEndpoint]]停止时调用。“self”将被置为空，您不能使用它来发送或询问消息。
     */
    public void onStop(){
        // By default, do nothing.
    }

    /**
     *一种方便的方法停止[[RpcEndpoint]]。
     */
    public final void stop(){
        RpcEndpointRef _self = self();
        if(_self != null){
            rpcEnv.stop(_self);
        }
    }

}
