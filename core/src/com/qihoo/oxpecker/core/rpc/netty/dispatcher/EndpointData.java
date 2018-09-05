package com.qihoo.oxpecker.core.rpc.netty.dispatcher;

import com.qihoo.oxpecker.core.rpc.rpcEndpoint.RpcEndpoint;
import com.qihoo.oxpecker.core.rpc.netty.NettyRpcEndpointRef;
import com.qihoo.oxpecker.core.rpc.netty.inbox.Inbox;

/**
 * Created by chengguangqing on 2018/4/30.
 */
public class EndpointData {

    private String name;
    private RpcEndpoint endpoint;
    private NettyRpcEndpointRef ref;
    private Inbox inbox;


    public EndpointData(String name,
                        RpcEndpoint endpoint,
                        NettyRpcEndpointRef ref){

        this.name = name;
        this.endpoint = endpoint;
        this.ref = ref;
        this.inbox = new Inbox(ref,endpoint);

    }

    public String getName() {
        return name;
    }

    public RpcEndpoint getEndpoint() {
        return endpoint;
    }

    public NettyRpcEndpointRef getRef() {
        return ref;
    }

    public Inbox getInbox() {
        return inbox;
    }
}
