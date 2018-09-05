package com.qihoo.oxpecker.core.rpc.rpcEnv;

import com.qihoo.oxpecker.core.rpc.*;
import com.qihoo.oxpecker.core.rpc.adddress.RpcAddress;
import com.qihoo.oxpecker.core.rpc.rpcEndpoint.RpcEndpoint;
import com.qihoo.oxpecker.core.rpc.rpcEndpoint.RpcEndpointRef;
import com.qihoo.oxpecker.core.rpc.rpcTimeout.RpcTimeout;

import java.nio.channels.ReadableByteChannel;

/**
 * Created by chengguangqing on 2018/3/30.
 */
public abstract class RpcEnv {

    private RpcConf conf;

    public RpcEnv(RpcConf conf){
        this.conf = conf;
    }

    private RpcTimeout defaultLookupTimeout = RpcUtils.lookupRpcTimeout(conf);

    /**
     * Return RpcEndpointRef of the registered [[RpcEndpoint]]. Will be used to implement
     * [[RpcEndpoint.self]]. Return `null` if the corresponding [[RpcEndpointRef]] does not exist.
     */
    public abstract RpcEndpointRef endpointRef(RpcEndpoint endpoint);


    /**
     * Return the address that [[RpcEnv]] is listening to.
     */
    public abstract RpcAddress address();

    /**
     * Register a [[RpcEndpoint]] with a name and return its [[RpcEndpointRef]]. [[RpcEnv]] does not
     * guarantee thread-safety.
     */
    public abstract RpcEndpointRef setupEndpoint(String name, RpcEndpoint endpoint);


    /**
     * Retrieve the [[RpcEndpointRef]] represented by `uri`. This is a blocking action.
     */
    public RpcEndpointRef setupEndpointRefByURI(String uri){
        //defaultLookupTimeout.
        return null;
    }

    /**
     * Retrieve the [[RpcEndpointRef]] represented by `address` and `endpointName`.
     * This is a blocking action.
     */
    public RpcEndpointRef setupEndpointRef(){

        return null;
    }

    /**
     * Stop [[RpcEndpoint]] specified by `endpoint`.
     */
    public abstract void stop(RpcEndpointRef endpoint);


    /**
     * Shutdown this [[RpcEnv]] asynchronously. If need to make sure [[RpcEnv]] exits successfully,
     * call [[awaitTermination()]] straight after [[shutdown()]].
     */
    public abstract  void shutdown();

    public abstract void awaitTermination();

    public abstract void deserialize();

    /**
     * Return the instance of the file server used to serve files. This may be `null` if the
     * RpcEnv is not operating in server mode.
     */
    public abstract RpcEnvFileServer fileServer();


    /**
     * Open a channel to download a file from the given URI. If the URIs returned by the
     * RpcEnvFileServer use the "spark" scheme, this method will be called by the Utils class to
     * retrieve the files.
     *
     * @param uri URI with location of the file.
     */
    public abstract ReadableByteChannel openChannel(String uri);


    public RpcEnv create(String name,
                         String host,
                         int port,
                         RpcConf conf,
                         String securityManager,
                         boolean clientMode){

        return create(name, host, host, port, conf, securityManager, clientMode);
    }

    public RpcEnv create(String name,
                         String bindAddress,
                         String advertiseAddress,
                         int port,
                         RpcConf conf,
                         String securityManager,
                         boolean clientMode){

        RpcEnvConfig config = new RpcEnvConfig(conf, name, bindAddress, advertiseAddress, port, securityManager,
                clientMode);

        return null;
    }

}

