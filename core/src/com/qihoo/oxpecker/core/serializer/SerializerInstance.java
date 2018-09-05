package com.qihoo.oxpecker.core.serializer;


import scala.reflect.ClassTag;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * Created by chengguangqing on 2018/5/21.
 */
public abstract class SerializerInstance {


    abstract <T> ByteBuffer serialize( T t);

    abstract <T> T deserialize( ByteBuffer bytes);

    abstract <T> T deserialize(ByteBuffer bytes, ClassLoader loader);

    abstract  SerializationStream serializeStream(OutputStream s);

    abstract DeserializationStream deserializeStream(InputStream s);

}
