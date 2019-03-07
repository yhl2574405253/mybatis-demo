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

    /**
     * 操作redis对象
     */
    Jedis jedis =new Jedis("localhost",6379);

// 缓存的id
    private String cacheId;

//  需要一个构造方法
    public RedisCache(String cacheId){
        this.cacheId=cacheId;
    }


    public String getId() {
        return cacheId;
    }

    /**
     * mybatis读取数据时 将数据库中读取的数据 通过putObject设置到缓存中
     */
    public void putObject(Object key, Object value) {
        try {
            //写入到redis 通过用byte数组的方式写入到redis
            jedis.set(objectToByteArray(key),objectToByteArray(value));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    /**
     * mybatis缓存策略 自动判断内存大小，内存不够时，调用该方法，然后来决定删除一些过期或者久远的数据
     * @param key
     * @return
     */
    public Object removeObject(Object key) {
        Object object = getObject(key);
        try {
            jedis.del(objectToByteArray(key));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

//  清空缓存 session被关闭的时候会调用这个方法 一般不要去清理他
    public void clear() {
//        jedis.flushDB();
    }

//  这个方法不会被调用
    public int getSize() {
        return 0;
    }

//  二级缓存中的锁
    public ReadWriteLock getReadWriteLock() {
        return new ReentrantReadWriteLock();
    }
}
