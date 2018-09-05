package com.qihoo.oxpecker.core.common;

/**
 * Created by chengguangqing on 2018/4/23.
 */
public class TwoTuple<A,B> {

    public final A _1;
    public final B _2;

    public TwoTuple(A a, B b){
        _1 = a;
        _2 = b;
    }

    public String toString(){
        return "(" + _2 + ", " + _2 + ")";
    }

}
