package testgroovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import java.io.IOException;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * @Author wenbaoxie
 * @Date 2020/10/16
 */
public class TestGroovy {

    @Test
    public void test() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

//classpath: 表示加载当前类路径中所有匹配的资源
        Resource[] resources = resourcePatternResolver.getResources("classpath:");
        for(Resource r : resources){
            System.out.println(r.getFilename()); //文件名
            System.out.println(r.getURL().getPath()); //文件绝对路径
            System.out.println(r.getFile()); //File对象
            System.out.println(r.getInputStream()); //InputStream对象
        }

        System.out.println("+++++++++++++++++++++++++++");
//classpath*: 表示加载类路径中所有匹配的资源
        resources = resourcePatternResolver.getResources("classpath*:");
        for(Resource r : resources){
            System.out.println(r.getURL().getPath()); //文件绝对路径
        }

//加载文件系统中的资源
        Resource r = resourcePatternResolver.getResource("file:D:/ReqBody_input.desc.proto");
        System.out.println(org.apache.commons.io.IOUtils.toString(r.getInputStream())); //读取文件内容

        //
        }
        @Test
    public void test1() throws IllegalAccessException, InstantiationException {
            GroovyClassLoader classLoader = new GroovyClassLoader();
            Class aClass = classLoader.parseClass("def cal(int a,int b){ return a -b ;}");
            GroovyObject o = (GroovyObject)aClass.newInstance();
            Object[] param = { 8,7 };
            System.out.println(o.invokeMethod("cal", param));
            System.out.println("++++++++");
            Class groovyClass = classLoader.parseClass("def cal(int a, int b){\n" +
                    "    return a+b\n" +
                    "}");
            try {
                GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
                int result = (int)groovyObject.invokeMethod("cal",param);
                System.out.println(result);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
}
