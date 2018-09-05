package com.qihoo.oxpecker.core.serializer;

import com.qihoo.oxpecker.core.OxpeckerConf;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * Created by chengguangqing on 2018/5/21.
 */
public class JavaSerializer extends Serializer implements Externalizable {

    private OxpeckerConf conf;

    public JavaSerializer(OxpeckerConf conf){
        this.conf = conf;
    }

    private int counterReset = conf.getInt("oxpecker.serializer.objectStreamReset",100);
    private boolean extraDebugInfo = conf.getBoolean("oxpecker.serializer.extraDebugInfo",true);
    //protected def this() = this(new SparkConf())  // For deserialization only

    @Override
    public SerializerInstance newInstance() {
        ClassLoader classLoader =  defaultClassLoader == null ? Thread.currentThread().getContextClassLoader() : defaultClassLoader;
        return new JavaSerializerInstance(counterReset, extraDebugInfo, classLoader);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(counterReset);
        out.writeBoolean(extraDebugInfo);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        counterReset = in.readInt();
        extraDebugInfo = in.readBoolean();
    }


    private class JavaSerializerInstance extends SerializerInstance{

        private int counterReset;
        private boolean extraDebugInfo;
        private ClassLoader defaultClassLoader;

        public JavaSerializerInstance(int counterReset,boolean extraDebugInfo,ClassLoader defaultClassLoader){
            this.counterReset = counterReset;
            this.extraDebugInfo = extraDebugInfo;
            this.defaultClassLoader = defaultClassLoader;
        }


        @Override
        public <T> ByteBuffer serialize(T t) {
            return null;
        }

        @Override
        public <T> T deserialize(ByteBuffer bytes) {
            return null;
        }

        @Override
        public <T> T deserialize(ByteBuffer bytes, ClassLoader loader) {
            return null;
        }

        @Override
        SerializationStream serializeStream(OutputStream s) {
            return null;
        }

        @Override
        DeserializationStream deserializeStream(InputStream s) {
            return null;
        }
    }
}





