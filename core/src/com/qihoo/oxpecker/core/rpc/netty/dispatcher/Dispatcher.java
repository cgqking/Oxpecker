package com.qihoo.oxpecker.core.rpc.netty.dispatcher;

import com.qihoo.oxpecker.core.rpc.rpcEndpoint.RpcEndpoint;
import com.qihoo.oxpecker.core.rpc.netty.RpcEndpointAddress;
import com.qihoo.oxpecker.core.rpc.rpcEndpoint.RpcEndpointRef;
import com.qihoo.oxpecker.core.rpc.netty.NettyRpcEndpointRef;
import com.qihoo.oxpecker.core.rpc.netty.NettyRpcEnv;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by chengguangqing on 2018/4/30.
 */
public class Dispatcher {

    private NettyRpcEnv nettyEnv;

    public Dispatcher(NettyRpcEnv nettyEnv){
        this.nettyEnv = nettyEnv;
    }

    private ConcurrentMap<String,EndpointData> endpoints = new ConcurrentHashMap<String,EndpointData>();
    private ConcurrentMap<RpcEndpoint,RpcEndpointRef> endpointRefs = new ConcurrentHashMap<RpcEndpoint,RpcEndpointRef>();

    // Track the receivers whose inboxes may contain messages.
    private LinkedBlockingQueue<EndpointData> receivers = new LinkedBlockingQueue<EndpointData>();

    /**
     * True if the dispatcher has been stopped. Once stopped, all messages posted will be bounced
     * immediately.
     */
    private Boolean stopped = false;

    public NettyRpcEndpointRef registerRpcEndpoint(String name,
                                                   RpcEndpoint endpoint){
        RpcEndpointAddress addr = new RpcEndpointAddress(nettyEnv.address(),name);
        NettyRpcEndpointRef endpointRef = new NettyRpcEndpointRef(nettyEnv.conf,addr,nettyEnv);

        synchronized(this) {
            if(stopped){
                throw new IllegalStateException("RpcEnv has been stopped");
            }

            if(endpoints.putIfAbsent(name,new EndpointData(name, endpoint, endpointRef)) != null){
                throw new IllegalArgumentException("There is already an RpcEndpoint called "+ name);
            }

            EndpointData data = endpoints.get(name);
            endpointRefs.put(data.getEndpoint(),data.getRef());
            receivers.offer(data); // for the OnStart message
        }
        return endpointRef;
    }

    public RpcEndpointRef getRpcEndpointRef(RpcEndpoint endpoint){
        return endpointRefs.get(endpoint);
    }

    public void removeRpcEndpointRef(RpcEndpoint endpoint){
        endpointRefs.remove(endpoint);
    }

    public void unregisterRpcEndpoint(String name){
        EndpointData data = endpoints.remove(name);
        if(data != null){
            data.getInbox().stop();
            receivers.offer(data);// for the OnStop message
        }
    }

    public void stop(RpcEndpointRef rpcEndpointRef){
        synchronized (this){
            if(stopped){
                return;
            }
            unregisterRpcEndpoint(rpcEndpointRef.name());
        }
    }

}
