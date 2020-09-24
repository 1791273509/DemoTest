package json;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import com.qq.qidian.frm.util.id.SnowflakeIdGenerator;
import com.qq.qidian.frm.util.id.SnowflakeIdStrategy;
import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Ignore
public class Util {
    @Test
    public void get() {
        while (true) {
            System.out.println(GenerateSnow());
        }
    }

    public static long GenerateSnow() {
        long workerId = generateRandom();//为终端ID
        long datacenterId = 1;//数据中心ID
        Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        return snowflake.nextId();
    }

    private static long generateRandom() {
        double v = Math.random() * 128.0D;
        return (long) v;
    }
}
