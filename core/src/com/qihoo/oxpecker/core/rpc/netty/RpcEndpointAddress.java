package com.qihoo.oxpecker.core.rpc.netty;

import com.qihoo.oxpecker.core.rpc.adddress.RpcAddress;

import java.net.URI;

/**
 * Created by chengguangqing on 2018/3/30.
 */
public class RpcEndpointAddress {

    public RpcAddress rpcAddress;
    public String name;

    public RpcEndpointAddress(RpcAddress rpcAddress, String name){
        this.rpcAddress = rpcAddress;
        this.name = name;
    }

    public RpcEndpointAddress(String host, int port, String name){

        this.rpcAddress = new RpcAddress(host,port);
        this.name = name;
    }

    public String getString(){
        if(rpcAddress == null){
            return "oxpecker://"+name;
        }
        else{
            return "oxpecker://"+name+"@"+rpcAddress.getHost() +":"+rpcAddress.getPort();
        }
    }

    public static RpcEndpointAddress getRpcEndpointAddress(String url) {
        try {
            URI uri = new URI(url);
            String host = uri.getHost();
            int port = uri.getPort();
            String name = uri.getUserInfo();

            if(uri.getScheme() != "oxpecker"
                    || host == null
                    || port < 0
                    || name == null
                    ||(uri.getPath() != null)
                    ||uri.getFragment() != null
                    ||uri.getQuery() != null){

                throw new Exception("Invalid Spark URL:");
            }
            else{
                return new RpcEndpointAddress(host,port,name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
