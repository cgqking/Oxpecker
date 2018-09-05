package com.qihoo.oxpecker.core.rpc.netty.outbox;

import org.apache.spark.network.client.TransportClient;

import java.nio.ByteBuffer;

/**
 * Created by chengguangqing on 2018/5/7.
 */
public interface OnSuccessMethod {
    void onSuccess(TransportClient client, ByteBuffer response);
}
