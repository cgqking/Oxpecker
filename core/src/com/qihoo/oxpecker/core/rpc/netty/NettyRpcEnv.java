package com.qihoo.oxpecker.core.rpc.netty;

import com.qihoo.oxpecker.core.rpc.*;
import com.qihoo.oxpecker.core.rpc.adddress.RpcAddress;
import com.qihoo.oxpecker.core.rpc.rpcEndpoint.RpcEndpoint;
import com.qihoo.oxpecker.core.rpc.rpcEndpoint.RpcEndpointRef;
import com.qihoo.oxpecker.core.rpc.rpcEnv.RpcEnv;
import com.qihoo.oxpecker.core.rpc.rpcEnv.RpcEnvFileServer;
import org.apache.spark.network.client.TransportClient;

import java.nio.channels.ReadableByteChannel;

/**
 * Created by chengguangqing on 2018/4/23.
 */
public class NettyRpcEnv extends RpcEnv {

    public static ThreadLocal<NettyRpcEnv> currentEnv = new ThreadLocal<NettyRpcEnv>();
    public static ThreadLocal<TransportClient> currentClient = new ThreadLocal<TransportClient>();

    public NettyRpcEnv(RpcConf conf) {
        super(conf);
    }

    @Override
    public RpcEndpointRef endpointRef(RpcEndpoint endpoint) {
        return null;
    }

    @Override
    public RpcAddress address() {
        return null;
    }

    @Override
    public RpcEndpointRef setupEndpoint(String name, RpcEndpoint endpoint) {
        return null;
    }

    @Override
    public void stop(RpcEndpointRef endpoint) {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public void awaitTermination() {

    }

    @Override
    public void deserialize() {

    }

    @Override
    public RpcEnvFileServer fileServer() {
        return null;
    }

    @Override
    public ReadableByteChannel openChannel(String uri) {
        return null;
    }
}
