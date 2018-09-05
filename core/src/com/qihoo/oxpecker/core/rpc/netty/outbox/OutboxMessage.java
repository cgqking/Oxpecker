package com.qihoo.oxpecker.core.rpc.netty.outbox;

import org.apache.spark.network.client.TransportClient;

/**
 * Created by chengguangqing on 2018/5/7.
 */
public interface OutboxMessage {

    void sendWith(TransportClient client);

    void onFailure(Throwable e);
}
