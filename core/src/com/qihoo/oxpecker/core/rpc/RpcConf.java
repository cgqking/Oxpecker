package com.qihoo.oxpecker.core.rpc;

import com.qihoo.oxpecker.core.common.Utils;

/**
 * Created by chengguangqing on 2018/4/23.
 */
public class RpcConf {

    public Long getTimeAsSeconds(String key){
       return  Utils.timeStringAsSec(key);
    }
}
