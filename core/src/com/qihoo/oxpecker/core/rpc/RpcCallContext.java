package com.qihoo.oxpecker.core.rpc;

/**
 * Created by chengguangqing on 2018/4/18.
 */

import com.qihoo.oxpecker.core.rpc.adddress.RpcAddress;

/**
 * A callback that [[RpcEndpoint]] can use to send back a message or failure. It's thread-safe
 * and can be called in any thread.
 */
public interface RpcCallContext {

    //返回一个消息到发送者，如果发送者is
    public <T> void reply(T response);

    public void snedFailure(Throwable e);

    public RpcAddress senderAddress();

}
