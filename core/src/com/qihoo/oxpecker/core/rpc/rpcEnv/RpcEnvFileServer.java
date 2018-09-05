package com.qihoo.oxpecker.core.rpc.rpcEnv;

import java.io.File;

/**
 * Created by chengguangqing on 2018/4/23.
 */
public abstract class RpcEnvFileServer {

    public abstract  String addFile(File file);

    public abstract  String addJar(File file);

    public abstract  String addDirectory(String baseUri, File path);


    /** Validates and normalizes the base URI for directories. */
    public String validateDirectoryUri(String baseUri){
        String fixedBaseUri = "/" + baseUri.replace("/","");
        return fixedBaseUri;
    }
}
