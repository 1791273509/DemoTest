package yaml;

import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author wenbaoxie
 * @Date 2020/10/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestList {
    @Value("${test.a}")
    private int set ;
    @Test
    public void tet(){
        System.out.println(set);
    }
}
