package com.zyd.blog.core.config;

import org.apache.shiro.io.SerializationException;
import org.apache.shiro.io.Serializer;

import java.io.*;

public class MySecSerializer<T> implements Serializer<T> {
    public MySecSerializer() {
    }

    @Override
    public byte[] serialize(T o) throws SerializationException {
        if (o == null) {
            String msg = "argument cannot be null.";
            throw new IllegalArgumentException(msg);
        } else {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(baos);

            try {
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(o);
                oos.close();
                return baos.toByteArray();
            } catch (IOException var6) {
                String msg = "Unable to serialize object [" + o + "].  In order for the DefaultSerializer to serialize this object, the [" + o.getClass().getName() + "] class must implement java.io.Serializable.";
                throw new SerializationException(msg, var6);
            }
        }
    }

    @Override
    public T deserialize(byte[] serialized) throws SerializationException {
        if (serialized == null) {
            String msg = "argument cannot be null.";
            throw new IllegalArgumentException(msg);
        } else {
            ByteArrayInputStream bais = new ByteArrayInputStream(serialized);
            BufferedInputStream bis = new BufferedInputStream(bais);

            try {
                ObjectInputStream ois = new MyClassResolvingObjectInputStream(bis);
                T deserialized = (T) ois.readObject();
                ois.close();
                return deserialized;
            } catch (Exception var6) {
                String msg = "Unable to deserialize argument byte array.";
                throw new SerializationException(msg, var6);
            }
        }
    }
}
