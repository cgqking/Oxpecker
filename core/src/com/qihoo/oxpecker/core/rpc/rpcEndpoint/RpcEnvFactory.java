package com.qihoo.oxpecker.core.rpc.rpcEndpoint;

import com.qihoo.oxpecker.core.rpc.rpcEnv.RpcEnv;
import com.qihoo.oxpecker.core.rpc.rpcEnv.RpcEnvConfig;

/**
 * Created by chengguangqing on 2018/5/21.
 */
public interface RpcEnvFactory {
    public RpcEnv create(RpcEnvConfig config);
}
