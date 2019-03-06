package cn.et.demo05.mapper;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.ibatis.cache.Cache;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RedisCache implements Cache {
    /**
     * 操作redis对象
     */
    Jedis jedis =new Jedis("localhost",6379);
    /**
     * 缓存的id
     */
    private String cacheId;
    public RedisCache(String cacheId){
        this.cacheId=cacheId;
    }
    public void clear() {
        jedis.flushDB();
    }

    public String getId() {
        return cacheId;
    }

    /**
     * mybatis自动调用getObject检查是否缓存中存在
     */
    public Object getObject(Object key) {
        try {
            byte[] bs = jedis.get(objectToByteArray(key));
            if (bs==null) {
                return null;
            }
            return byteArrayToObject(bs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ReadWriteLock getReadWriteLock() {
        return new ReentrantReadWriteLock();
    }

    public int getSize() {
        return 0;
    }

    /**
     * mybatis读取数据时 将数据库中读取的数据 通过
     * putObject设置到缓存中
     */
    public void putObject(Object key, Object value) {
        //写入redis
        try {
            jedis.set(objectToByteArray(key), objectToByteArray(value));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * mybatis自动调用getObject检测是否缓存中存在
     *
     */
    public Object removeObject(Object key) {
        Object obj =getObject(key);
        try {
            jedis.del(objectToByteArray(obj));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }


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

}
