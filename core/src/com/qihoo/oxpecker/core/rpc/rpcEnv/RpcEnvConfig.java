package com.qihoo.oxpecker.core.rpc.rpcEnv;

import com.qihoo.oxpecker.core.rpc.RpcConf;

/**
 * Created by chengguangqing on 2018/3/30.
 */
public class RpcEnvConfig {

    private RpcConf conf;
    private String name;
    private String bindAddress;
    private String advertiseAddress;
    private int port;
    //private String securityManager;
    private boolean clientMode;


    public RpcEnvConfig(
            RpcConf conf,
            String name,
            String bindAddress,
            String advertiseAddress,
            int port,
            String securityManager,
            boolean clientMode
    ){
        this.conf = conf;
        this.name = name;
        this.bindAddress = bindAddress;
        this.advertiseAddress = advertiseAddress;
        this.port = port;
        //this.securityManager
        this.clientMode = clientMode;
    }

    public RpcConf getConf() {
        return conf;
    }

    public void setConf(RpcConf conf) {
        this.conf = conf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBindAddress() {
        return bindAddress;
    }

    public void setBindAddress(String bindAddress) {
        this.bindAddress = bindAddress;
    }

    public String getAdvertiseAddress() {
        return advertiseAddress;
    }

    public void setAdvertiseAddress(String advertiseAddress) {
        this.advertiseAddress = advertiseAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isClientMode() {
        return clientMode;
    }

    public void setClientMode(boolean clientMode) {
        this.clientMode = clientMode;
    }
}
