package com.qihoo.oxpecker.core.rpc.adddress;

import com.qihoo.oxpecker.core.Utils;
import com.qihoo.oxpecker.core.common.TwoTuple;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by chengguangqing on 2018/3/30.
 */

/**
 * Address for an RPC environment, with hostname and port.
 */
public class RpcAddress {

    private String host;
    private int port;
    private String hostPort;

    public RpcAddress(String host,int port){
        this.host = host;
        this.port = port;
        this.hostPort = host+":"+port;
    }

    public String hostPort(){
        return this.hostPort;
    }

    public String toOxpeckerURL(){
        return "oxpecker://" + hostPort;
    }
    /** Return the [[RpcAddress]] represented by `uri`. */
    public static RpcAddress fromURIString(String uri) throws URISyntaxException {
        URI uriObj = new URI(uri);
        return new RpcAddress(uriObj.getHost(),uriObj.getPort());
    }

    /** Returns the [[RpcAddress]] encoded in the form of "oxpecker://host:port" */
    public static RpcAddress fromOxpeckerURL(String oxpeckerURL){
        TwoTuple<String,Integer> hp = Utils.extractHostPortFromOxpeckerUrl(oxpeckerURL);
        return new RpcAddress(hp._1,hp._2);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
