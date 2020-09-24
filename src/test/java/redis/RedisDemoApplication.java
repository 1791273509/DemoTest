package redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author wenbaoxie
 * @Date 2020/9/24
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemoApplication {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void get(){
        System.out.println(redisTemplate.opsForValue().get("111"));
    }
}