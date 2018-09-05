package com.qihoo.oxpecker.core.rpc;

import com.qihoo.oxpecker.core.rpc.rpcTimeout.RpcTimeout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengguangqing on 2018/4/23.
 */
public class RpcUtils {

    /** Returns the default Spark timeout to use for RPC remote endpoint lookup. */
    public static RpcTimeout lookupRpcTimeout(RpcConf conf){
        List<String> propList = new ArrayList<String>();
        propList.add("oxpecker.rpc.lookupTimeout");
        propList.add("oxpecker.network.timeout");
        return new RpcTimeout(conf,propList,"120s");
    }

    /** Returns the configured number of times to retry connecting */
    public static int numRetries(RpcConf conf){
    return 0;
    }

    /** Returns the configured number of milliseconds to wait on each retry */
    public static int retryWaitMs(RpcConf conf){
        return 0;
    }

    /** Returns the default oxpecker timeout to use for RPC ask operations. */
    public static RpcTimeout askRpcTimeout(RpcConf conf){
        List<String> args = new ArrayList<String>();
        args.add("spark.rpc.askTimeout");
        args.add("spark.network.timeout");
        return new RpcTimeout(conf,args,"120s");

    }
}
