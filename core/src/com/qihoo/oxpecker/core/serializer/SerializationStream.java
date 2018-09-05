package com.qihoo.oxpecker.core.serializer;

import java.io.Closeable;
import java.util.Iterator;

/**
 * Created by chengguangqing on 2018/9/5.
 */
public abstract class SerializationStream implements Closeable {
    /** The most general-purpose method to write an object. */
    abstract<T> SerializationStream writeObject(T t);

    /** Writes the object representing the key of a key-value pair. */
    <T> SerializationStream writeKey(T key){
        return writeObject(key);
    }

    /** Writes the object representing the value of a key-value pair. */
    <T> SerializationStream writeValue(T value){
        return writeObject(value);
    }

    abstract void flush();

    <T> SerializationStream writeAll(Iterator<T> iter){
        while(iter.hasNext()){
            writeObject(iter.next());
        }
        return this;
    }
}
