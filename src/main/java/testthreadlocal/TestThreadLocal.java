package testthreadlocal;

import testreference.Student_Fin;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

/**
 * @Author wenbaoxie
 * @Date 2020/9/16
 */
public class TestThreadLocal {
    static ThreadLocal<Student_Fin> t1 = new ThreadLocal<Student_Fin>();
// ThreadLocal key是当前的t1，value是你设置的那个值，对应的map的是该线程的map
    public static void main(String[] args) throws InterruptedException {
        new Thread(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(t1.get());
                }
        ).start();

        new Thread(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    t1.set(new Student_Fin());
                    System.out.println(t1.get());
                    t1.remove();
                }
        ).start();

    }
}
