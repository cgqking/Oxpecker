package com.qihoo.oxpecker.core.rpc.netty.outbox;

import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.network.client.TransportClient;

import java.nio.ByteBuffer;

/**
 * Created by chengguangqing on 2018/5/7.
 */
public class RpcOutboxMessage implements OutboxMessage, RpcResponseCallback{

    private ByteBuffer content;

    private OnFailureMethod onFailureMethod;

    private OnSuccessMethod onSuccessMethod;

    private TransportClient client = null;
    private Long requestId = null;

    public RpcOutboxMessage(ByteBuffer content, OnFailureMethod onFailure,OnSuccessMethod onSuccess){
        this.content = content;
        this.onFailureMethod = onFailure;
        this.onSuccessMethod = onSuccess;
    }

    public void onTimeout(){
        if(client != null){
            client.removeRpcRequest(requestId);
        }
        else{
            System.out.println("Ask timeout before connecting successfully");
        }
    }

    @Override
    public void sendWith(TransportClient client) {
        this.client = client;
        this.requestId = client.sendRpc(content,this);
    }

    @Override
    public void onSuccess(ByteBuffer response) {
        this.onSuccessMethod.onSuccess(client,response);
    }

    @Override
    public void onFailure(Throwable e) {
        this.onFailureMethod.onFailure(e);
    }

    public ByteBuffer getContent() {
        return content;
    }
}
