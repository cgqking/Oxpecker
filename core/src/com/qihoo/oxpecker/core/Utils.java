package com.qihoo.oxpecker.core;

import com.qihoo.oxpecker.core.common.TwoTuple;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by chengguangqing on 2018/4/23.
 */
public class Utils {


    public static TwoTuple<String,Integer> extractHostPortFromOxpeckerUrl(String oxpeckerUrl){

        TwoTuple<String,Integer> res = null;
        try {
            URI uri = new URI(oxpeckerUrl);
            String host = uri.getHost();
            int port = uri.getPort();

            if (uri.getScheme() != "oxpecker" ||
                    host == null ||
                    port < 0 ||
                    (uri.getPath() != null && !uri.getPath().equals("")) || // uri.getPath returns "" instead of null
                    uri.getFragment() != null ||
                    uri.getQuery() != null ||
                    uri.getUserInfo() != null) {
                throw new Exception("Invalid master URL: " + oxpeckerUrl);
            }
            res = new TwoTuple<String,Integer>(host,port);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
