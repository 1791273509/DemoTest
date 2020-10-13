package yamltest;

import com.sun.org.apache.xalan.internal.xsltc.compiler.SourceLoader;
import org.junit.Test;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author wenbaoxie
 * @Date 2020/9/29
 */
public class YamlUT {
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("dddd");
        Yaml yaml = new Yaml();
        URL url = Test.class.getClassLoader().getResource("test.yaml");
        if (url != null) {
            //获取test.yaml文件中的配置数据，然后转换为obj，
            Object obj = yaml.load(new FileInputStream(url.getFile()));
            System.out.println(obj);
            //也可以将值转换为Map
            Map map = (Map) yaml.load(new FileInputStream(url.getFile()));
            System.out.println(map);
            //通过map我们取值就可以了.
            System.out.println("+++++++++");

        }
    }
    @Test
    public void testYamlReader() {
        YamlReader.getInstance();
    }
    @Test
    public void get() throws IOException {

        Yaml yaml = new Yaml();
        yaml.load(new FileInputStream("D:\\code\\DemoTest\\src\\main\\resources\\application.yaml"));
        Map<Object, Object> document = yaml.load("3.0: 2018-07-22");

//        测试文件下的.yaml测试
// 第一种：获取类加载的根路径   D:\code\DemoTest\target\test-classes
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println(f);
        // 第三种：  file:/D:/git/daotie/daotie/target/classes/
        URL xmlpath = this.getClass().getClassLoader().getResource("");

        System.out.println(xmlpath.getFile());
    }

    @Test
    public void testDefaultResource() throws IOException {
//        这个获取的是test下的

        Resource resource = new DefaultResourceLoader().getResource("classpath:");
        System.out.println(resource.getFile().getParent());

        System.out.println(resource.getFile().getAbsolutePath());
    }
    @Test
    public void tests() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("application.yaml");
        System.out.println("dddddddddd"+classPathResource.getFile().getAbsolutePath());
        InputStream inputStream = classPathResource.getInputStream();

    }

    @Test
    public void buildArray() {
        int [] target = new int[]{1,3};
        int n = 3;
        List<String> res = new LinkedList<>();
        String push = "Push";
        String pop = "Pop";
        int i = 1;
        for (Integer re : target) {
            if (i != re) {
                while (i < re) {
                    res.add(push);
                    i++;
                    res.add(pop);
                }
            }
            i ++;
            res.add(push);
        }
        while (i++ < n){
            res.add(push);
            res.add(pop);
        }
    }
}
