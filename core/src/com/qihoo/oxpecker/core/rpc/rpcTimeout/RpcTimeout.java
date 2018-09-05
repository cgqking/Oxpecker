package com.qihoo.oxpecker.core.rpc.rpcTimeout;

import com.qihoo.oxpecker.core.common.TwoTuple;
import com.qihoo.oxpecker.core.rpc.RpcConf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by chengguangqing on 2018/3/30.
 */
public class RpcTimeout implements Serializable{

    public Long timeout;
    public String timeoutProp;

    public RpcTimeout(Long timeout,String timeoutProp ){
        this.timeout = timeout;
        this.timeoutProp = timeoutProp;
    }

    public RpcTimeout(RpcConf conf, List<String> timeoutPropList, String defaultValue){

        if(timeoutPropList.size() != 0){
            System.out.println("timeoutPropList.size() => "+timeoutPropList.size());
        }

        Iterator<String> items = timeoutPropList.iterator();
        List<TwoTuple<String,String>> foundProp = new ArrayList<TwoTuple<String,String>>();

        Long timeout = 0L;

        //通过解析配置获取值，目前直接写死
        while(items.hasNext() && foundProp.isEmpty()){
            String item = items.next();
            timeout = 2000L;
            break;
        }

        this.timeout = timeout;
        this.timeoutProp = defaultValue;
    }

    private Exception createRpcTimeout(TimeoutException te){
           return  new Exception("");
    }

    public RpcTimeout addMessageIfTimeout(RpcConf conf,String timeoutProp){
       Long timeout = conf.getTimeAsSeconds(timeoutProp);
       return new RpcTimeout(timeout,timeoutProp);
    }

    public String awaitResult(){
        return null;
    }
}
