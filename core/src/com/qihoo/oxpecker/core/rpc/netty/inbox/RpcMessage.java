package com.qihoo.oxpecker.core.rpc.netty.inbox;

import com.qihoo.oxpecker.core.rpc.adddress.RpcAddress;
import com.qihoo.oxpecker.core.rpc.netty.NettyRpcCallContext;

/**
 * Created by chengguangqing on 2018/4/30.
 */
public class RpcMessage implements InboxMessage {

    public RpcAddress senderAddress;
    public Object content;
    public NettyRpcCallContext context;

    public RpcMessage(RpcAddress senderAddress,
                      Object content,
                      NettyRpcCallContext context){
        this.senderAddress = senderAddress;
        this.content = content;
        this.context = context;
    }

}
