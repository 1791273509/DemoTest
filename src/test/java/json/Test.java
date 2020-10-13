package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qq.qidian.frm.util.id.SnowflakeIdGenerator;
import com.qq.qidian.frm.util.id.SnowflakeIdStrategy;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;

/**
 * @Author wenbaoxie
 * @Date 2020/9/22
 */
public class Test {
    @org.junit.Test
    public void get() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Entity[] o = objectMapper.readValue(new File("D:\\code\\activity\\activity\\src\\main\\resources\\permissionItem\\permissionItemUriMapping-seller.json"), Entity[].class);

        for (Entity entity : o) {
            entity.setId(ids());
        }
        objectMapper.writeValue(new File("D:\\code\\activity\\activity\\src\\main\\resources\\permissionItem\\permissionItemUriMapping-seller1.json"), o);
    }

    @org.junit.Test
    public void getItem() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ItemEntity[] o = objectMapper.readValue(new File("D:\\code\\activity\\activity\\src\\main\\resources\\permissionItem\\permissionItemUriMapping-seller.json"), ItemEntity[].class);

        for (ItemEntity itemEntity : o) {
            itemEntity.setId(ids());
        }
        objectMapper.writeValue(new File("D:\\code\\activity\\activity\\src\\main\\resources\\permissionItem\\permissionItemUriMapping-seller1.json"), o);
    }

    public Long ids() throws IOException {
        Long workerId = generateRandom();
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator();
        ReflectionTestUtils.setField(idGenerator, "workerId", workerId);
        ReflectionTestUtils.setField(idGenerator, "landscapeId", 0);

        return idGenerator.nextId(SnowflakeIdStrategy.of(


        ));
    }

    public Long idss() throws IOException {
        Long workerId = generateRandom();
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator();
        ReflectionTestUtils.setField(idGenerator, "workerId", workerId);
        ReflectionTestUtils.setField(idGenerator, "landscapeId", 0);

        return idGenerator.nextId(SnowflakeIdStrategy.of(


        ));
    }

    private long generateRandom() {
        double v = Math.random() * 128.0D;
        return (long) v;
    }
}
