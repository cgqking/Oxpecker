package com.qihoo.oxpecker.core.serializer;

import com.qihoo.oxpecker.core.common.TwoTuple;
import com.qihoo.oxpecker.core.util.NextIterator;

import java.util.Iterator;
import java.io.Closeable;


/**
 * Created by chengguangqing on 2018/9/5.
 */
public abstract class DeserializationStream implements Closeable {

    /** The most general-purpose method to read an object. */
    abstract <T> T readObject();

    /** Reads the object representing the key of a key-value pair. */
    <T> T readKey(){
        return readObject();
    }

    /** Reads the object representing the value of a key-value pair. */
    <T> T readValue(){
        return readObject();
    }

    @Override
    public abstract void close() ;


    /**
     * Read the elements of this stream through an iterator. This can only be called once, as
     * reading each element will consume data from the input source.
     */
    public <U> Iterator<U> asIterator(){

        Iterator<U> iter = new NextIterator<U>() {

                    @Override
                    protected U getNext() {
                        return readObject();
                    }

                    @Override
                    protected void close() {
                        DeserializationStream.this.close();
                    }

                    @Override
                    public void remove() {
                        super.remove();
                    }
                };

        return iter;
    }


    /**
     * Read the elements of this stream through an iterator over key-value pairs. This can only be
     * called once, as reading each element will consume data from the input source.
     */
    public <A,B> Iterator<TwoTuple<A,B>> asKeyValueIterator(){

        return new NextIterator<TwoTuple<A,B>>(){

                    @Override
                    protected TwoTuple<A, B> getNext() {

                        A a = readKey();
                        B b = readValue();

                        return new TwoTuple<>(a,b);
                    }

                    @Override
                    protected void close() {
                        DeserializationStream.this.close();
                    }

                    @Override
                    public void remove() {
                        super.remove();
                    }
                };
    }
}
