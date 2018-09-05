package com.qihoo.oxpecker.core.rpc.netty;

import com.qihoo.oxpecker.core.rpc.RpcConf;
import com.qihoo.oxpecker.core.rpc.rpcEnv.RpcEnv;
import com.qihoo.oxpecker.core.rpc.rpcEnv.RpcEnvConfig;
import com.qihoo.oxpecker.core.rpc.rpcEnv.RpcEnvFactory;
import com.qihoo.oxpecker.core.serializer.JavaSerializer;

/**
 * Created by chengguangqing on 2018/4/24.
 */
public class NettyRpcEnvFactory implements RpcEnvFactory{

    @Override
    public RpcEnv create(RpcEnvConfig conf) {
       RpcConf rpcConf = conf.getConf();

        //JavaSerializer javaSerializerInstance = new JavaSerializer()

        return null;
    }

}
