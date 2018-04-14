package com.lwq.db.redis.connect;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 *  测试连接redis
 *  [功能详细描述]
 * @作者 lwq
 * @version [版本号, 2018年4月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本] 
 */
public class TestConnect
{
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.10.128",6379);
        System.out.println(jedis.ping());
        
        
        
//        jedis.set("name", "张三","NX","EX", 60);
        System.out.println( jedis.ttl("name"));
        System.out.println(jedis.get("name"));
        jedis.append("k1","myreids");
        System.out.println(jedis.get("k1"));
        
        jedis.mset("aa","111","bb","222");
        jedis.mget("aa","bb").forEach(c->{
            System.out.println(c);
        });
        
        //list
        jedis.lpush("myList", "1","2","3","4");
        List<String> lrange = jedis.lrange("myList", 0, -1);
        System.out.println(lrange);
        jedis.rpush("myList1", "a","b","c","d");
        jedis.lrange("myList1", 0, -1).forEach(c->{
            System.out.println(c);
        });;
        
        //set
        jedis.sadd("orders", "001");
        jedis.sadd("orders", "002");
        jedis.sadd("orders", "003");
        Set<String> smembers = jedis.smembers("orders");
        smembers.forEach(m->{
            System.out.println(m);
        });
        jedis.srem("orders","001");
        System.out.println(jedis.smembers("orders").size());
//        System.out.println(jedis.mget("aa","bb"));
//        testAPI(jedis);
        
        
        //hash
        jedis.hset("user", "username", "zahsngasn");
        jedis.hset("user", "age", "20");
        jedis.hset("user", "email", "zahsngasn@163.com");
        
        jedis.hgetAll("user").forEach((k,v)->{
            System.out.println("k="+k+"---v="+v);
        });;
        
        //zset
        jedis.zadd("zset", 60,"v1");
        jedis.zadd("zset", 70,"v2");
        jedis.zadd("zset", 80,"v3");
        jedis.zadd("zset", 90,"v4");
        jedis.zadd("zset", 100,"v5");
        
        System.out.println(11);
        
        jedis.zrange("zset",0, -1).forEach(c->{
            System.out.println(c);
        });;
 
    }
    
    public static void testAPI(Jedis jedis){
        jedis.set("k1", "v1");
        jedis.set("k2", "v2");
        jedis.set("k3", "v3");
        
        
        System.out.println(jedis.get("k1"));
        System.out.println(jedis.get("k2"));
        System.out.println(jedis.get("k3"));
        
        Set<String> keys = jedis.keys("*");
        
        System.out.println(keys.size());
    }
}
