package com.qihoo.oxpecker.core.rpc.rpcEndpoint;

import com.qihoo.oxpecker.core.rpc.adddress.RpcAddress;
import com.qihoo.oxpecker.core.rpc.RpcConf;
import com.qihoo.oxpecker.core.rpc.rpcTimeout.RpcTimeout;
import com.qihoo.oxpecker.core.rpc.RpcUtils;

import java.io.Serializable;
import java.util.concurrent.Future;

/**
 * Created by chengguangqing on 2018/3/30.
 */
public abstract class RpcEndpointRef implements Serializable {

    private RpcConf conf;

    public RpcEndpointRef(RpcConf conf){
        this.conf = conf;
    }

    private int maxRetries = RpcUtils.numRetries(conf);
    private int retryWaitMs = RpcUtils.retryWaitMs(conf);
    private RpcTimeout defaultAskTimeout = RpcUtils.askRpcTimeout(conf);

    /**
     * return the address for the [[RpcEndpointRef]]
     */
    public abstract RpcAddress address();
    public abstract String name();

    /**
     * Sends a one-way asynchronous message. Fire-and-forget semantics.
     */
    public void send(Object message){

    }

    /**
     * Send a message to the corresponding [[RpcEndpoint.receiveAndReply)]] and return a [[Future]] to
     * receive the reply within the specified timeout.
     *
     * This method only sends the message once and never retries.
     */
    public abstract <V> Future<V> ask(Object message, RpcTimeout timeout);

    /**
     * Send a message to the corresponding [[RpcEndpoint.receiveAndReply)]] and return a [[Future]] to
     * receive the reply within a default timeout.
     *
     * This method only sends the message once and never retries.s
     */
    public  <V> Future<V> ask(Object message){
        return this.ask(message,defaultAskTimeout);
    };

    /**
     * Send a message to the corresponding [[RpcEndpoint.receiveAndReply]] and get its result within a
     * specified timeout, throw an exception if this fails.
     *
     * Note: this is a blocking action which may cost a lot of time, so don't call it in a message
     * loop of [[RpcEndpoint]].
     *
     * @param message the message to send
     * @param timeout the timeout duration
     * @tparam T type of the reply message
     * @return the reply message from the corresponding [[RpcEndpoint]]
     */
    public  abstract <T> T askSync(Object message,RpcTimeout timeout);

    /**
     * Send a message to the corresponding [[RpcEndpoint.receiveAndReply]] and get its result within a
     * default timeout, throw an exception if this fails.
     *
     * Note: this is a blocking action which may cost a lot of time,  so don't call it in a message
     * loop of [[RpcEndpoint]].

     * @param message the message to send
     * @tparam T type of the reply message
     * @return the reply message from the corresponding [[RpcEndpoint]]
     */
    public <T> T askSync(Object message){
        return this.askSync(message,defaultAskTimeout);
    }

}
