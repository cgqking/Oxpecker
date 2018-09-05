package com.qihoo.oxpecker.core.serializer;

/**
 * Created by chengguangqing on 2018/5/21.
 */
public abstract class Serializer {


    /**
     * Default ClassLoader to use in deserialization. Implementations of [[Serializer]] should
     * make sure it is using this when set.
     */
    protected ClassLoader defaultClassLoader = null;

    /**
     * Sets a class loader for the serializer to use in deserialization.
     *
     * @return this Serializer object
     */
    public Serializer setDefaultClassLoader(ClassLoader classLoader){
        defaultClassLoader = classLoader;
        return this;
    }

    /** Creates a new [[SerializerInstance]]. */
    public abstract SerializerInstance newInstance();


    private Boolean supportsRelocationOfSerializedObjects(){
        return false;
    }

}
