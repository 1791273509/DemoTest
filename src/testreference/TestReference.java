package testreference;

import java.io.IOException;
import java.lang.ref.SoftReference;

/**
 * @Author wenbaoxie
 * @Date 2020/9/15
 */
public class TestReference {

    public static void main(String[] args) throws IOException {
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
