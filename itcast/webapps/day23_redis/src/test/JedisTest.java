package test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import util.JedisPoolUtil;

public class JedisTest {
    @Test
    public void test() {
        //1.建立连接
        Jedis jedis = new Jedis("localhost", 6379);
        //2.操作
        jedis.set("age", "14");
        //3.关闭连接
        jedis.close();
    }

    @Test
    public void poolTest() {
        Jedis jedis = JedisPoolUtil.getJedis();
        //2.操作
        jedis.set("testName", "poolTest");
        System.out.println(jedis.get("testName"));
        //3.关闭连接
        jedis.close();
    }
}
