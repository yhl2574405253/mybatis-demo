package cn.et.demo05.mapper;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class javaRedis {

    /**
     * 序列化
     * 封装一个方法 用来给对象写到redis里面
     * 	jedis.set(主键,objectTobyteArray(student对象))
     * @throws IOException
     */
    public static byte[] objectToByteArray(Object object) throws IOException {
        ByteOutputStream boss =new ByteOutputStream();
        ObjectOutputStream oos =new ObjectOutputStream(boss);
        oos.writeObject(object);
        return boss.getBytes();
    }

    /**
     * 反序列化
     * byte [] bt=get("主键")
     * student s =byteArrayToObject(bt)
     * 反序列化字节数组为对象
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object byteArrayToObject(byte[] bt) throws Exception{
        ByteInputStream bis =new ByteInputStream(bt,bt.length);
        ObjectInputStream ois =new ObjectInputStream(bis);
        return ois.readObject();

    }

    public static void main(String[] args) {
        Jedis jedis =new Jedis("127.0.0.1", 6379);

        //键值对
        jedis.set("test", "hello");
        System.out.println(jedis.get("test"));

    }

}
