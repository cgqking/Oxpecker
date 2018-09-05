package com.qihoo.oxpecker.core.rpc.netty.outbox;

/**
 * Created by chengguangqing on 2018/5/7.
 */
public interface OnFailureMethod {

    void onFailure(Throwable e);
}
