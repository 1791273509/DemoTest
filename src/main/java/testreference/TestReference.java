package testreference;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.ref.SoftReference;

/**
 * @Author wenbaoxie
 * @Date 2020/9/15
 * @TODO 强软弱虚测试
 */
@Slf4j
public class TestReference {

    public static void main(String[] args) throws IOException, InterruptedException {
//        强引用
//        强引用是最普遍的一种引用，我们写的代码，99.9999%都是强引用：
        Object object = new Object();
/*        这种就是强引用了，是不是在代码中随处可见，最亲切。
        只要某个对象有强引用与之关联，这个对象永远不会被回收，即使内存不足，JVM宁愿抛出OOM，也不会去回收。
        那么什么时候才可以被回收呢？当强引用和对象之间的关联被中断了，就可以被回收了。
        我们可以手动把关联给中断了，方法也特别简单：
        */
        object = null;
        Student_Fin fin = new Student_Fin();
        fin = null;
        System.gc();
        Thread.sleep(1000);
        log.info("GC完成............");

        SoftReference<byte[]> softReference = new SoftReference<byte[]>(new byte[1024 * 1024 * 10]);
        System.out.println(softReference.get());
        System.out.println(softReference.get());
//        10mb
        byte[] tem = new byte[1024 * 1024 * 10];
        System.out.println(softReference.get());
//        阻塞住
        System.in.read();
    }
}
