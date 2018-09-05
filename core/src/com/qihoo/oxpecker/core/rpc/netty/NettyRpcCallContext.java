package com.qihoo.oxpecker.core.rpc.netty;

import com.qihoo.oxpecker.core.rpc.adddress.RpcAddress;
import com.qihoo.oxpecker.core.rpc.RpcCallContext;

/**
 * Created by chengguangqing on 2018/5/8.
 */
public abstract class NettyRpcCallContext implements RpcCallContext {


    private RpcAddress senderAddress;


    public NettyRpcCallContext(RpcAddress senderAddress){
        this.senderAddress = senderAddress;
    }


    protected abstract void send(Object message);

    @Override
    public abstract void reply(Object response);

    @Override
    public abstract void snedFailure(Throwable e);

    @Override
    public RpcAddress senderAddress() {
        return senderAddress;
    }

}
