package com.qihoo.oxpecker.core.rpc.netty;

import com.qihoo.oxpecker.core.rpc.*;
import com.qihoo.oxpecker.core.rpc.adddress.RpcAddress;
import com.qihoo.oxpecker.core.rpc.rpcEndpoint.RpcEndpointRef;
import com.qihoo.oxpecker.core.rpc.rpcTimeout.RpcTimeout;
import org.apache.spark.network.client.TransportClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.Future;

/**
 * Created by chengguangqing on 2018/4/23.
 */
public class NettyRpcEndpointRef extends RpcEndpointRef {


    private RpcEndpointAddress endpointAddress;
    private NettyRpcEnv nettyEnv;
    public TransportClient client = null;

    public NettyRpcEndpointRef(RpcConf conf,
                               RpcEndpointAddress endpointAddress,
                               NettyRpcEnv nettyEnv) {
        super(conf);
        this.endpointAddress = endpointAddress;
        this.nettyEnv = nettyEnv;
    }

    @Override
    public RpcAddress address() {
        if (endpointAddress.rpcAddress != null){
            return endpointAddress.rpcAddress;
        }
        else {
            return null;
        }
    }

    //或许有问题
    private void readObject(ObjectInputStream in){
        try {
            in.defaultReadObject();
            nettyEnv = NettyRpcEnv.currentEnv.get();
            client = NettyRpcEnv.currentClient.get();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void writeObject(ObjectOutputStream out) {
        try {
            out.defaultWriteObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String name() {
        return endpointAddress.name;
    }

    @Override
    public <V> Future<V> ask(Object message, RpcTimeout timeout) {
        return null;
    }

    @Override
    public <T> T askSync(Object message, RpcTimeout timeout) {
        return null;
    }
}
