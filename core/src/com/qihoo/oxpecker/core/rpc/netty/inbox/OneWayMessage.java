package com.qihoo.oxpecker.core.rpc.netty.inbox;

import com.qihoo.oxpecker.core.rpc.adddress.RpcAddress;

/**
 * Created by chengguangqing on 2018/4/30.
 */
public class OneWayMessage implements InboxMessage {
    private RpcAddress senderAddress;
    private Object content;

    public OneWayMessage(RpcAddress senderAddress,
                         Object content){
        this.senderAddress = senderAddress;
        this.content = content;
    }


}
