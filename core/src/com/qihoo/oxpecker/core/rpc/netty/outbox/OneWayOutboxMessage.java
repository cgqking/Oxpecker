package com.qihoo.oxpecker.core.rpc.netty.outbox;


import org.apache.spark.network.client.TransportClient;

import java.nio.ByteBuffer;

/**
 * Created by chengguangqing on 2018/5/7.
 */
public class OneWayOutboxMessage implements OutboxMessage {

    private ByteBuffer content;

    public OneWayOutboxMessage(ByteBuffer content){
        this.content = content;
    }

    @Override
    public void sendWith(TransportClient client) {
        client.send(content);
    }

    @Override
    public void onFailure(Throwable e) {

        //if(e instanceof RpcEnvStoppedException){
        if(e instanceof OutboxMessage){
            System.out.println(e.getMessage());
        }
        else if (e instanceof Throwable){
            System.out.println(e.getMessage());
        }
    }

    public ByteBuffer getContent() {
        return content;
    }
}
