package com.qihoo.oxpecker.core.rpc.rpcEnv;

/**
 * Created by chengguangqing on 2018/3/30.
 */
public interface RpcEnvFactory {

    public RpcEnv create(RpcEnvConfig conf);

}
