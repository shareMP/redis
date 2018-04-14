package com.lwq.db.redis.connect;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTx
{
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.10.128",6379);
        
        Transaction transaction = jedis.multi();
        
        //入队
        transaction.set("k4", "v44");
        transaction.set("k5", "v55");
        
        //执行
//        transaction.exec();
        
        //放弃,回滚
        transaction.discard();
    }
}
