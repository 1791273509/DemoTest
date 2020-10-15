package juctest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.Test;

/**
 * @Author wenbaoxie
 * @Date 2020/10/15
 */
public class Test1 {

    @org.junit.Test
    public void testSemphoere() throws Exception {
        Semaphore semaphore = new Semaphore(3);
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "获取");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "释放");
                    semaphore.release();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
        countDownLatch.await();
    }

    @org.junit.Test
    public void testReentranLock() throws Exception {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
    }

    @Test
    public void testCycl() {
        CyclicBarrier c = new CyclicBarrier(2, () -> {
            System.out.println("结束~~~");
        });
        for (int i = 0; i < 2; i++) {
            new Thread(
                    () -> {
                        try {
                            System.out.println("dd");
                            c.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }

            ).start();
        }
    }

    @Test
    public void testExchanger() throws InterruptedException {
        /*
        Exchanger类源于java.util.concurrent包，它可以在两个线程之间传输数据，
        Exchanger中的public V exchange(V x)方法被调用后等待另一个线程到达交换点（如果当前线程没有被中断），
        然后将已知的对象传给它，返回接收的对象。
        如果另外一个线程已经在交换点等待，那么恢复线程计划并接收通过当前线程传给的对象：
        */
        Exchanger<String> exchanger = new Exchanger<>();
        Car car = new Car(exchanger);
        Bike bike = new Bike(exchanger);
        car.start();
        bike.start();
        System.out.println("Main end!");
        TimeUnit.SECONDS.sleep(10);
        System.out.println(car.name);
        System.out.println(bike.name);
    }


}

class Car extends Thread {

    private Exchanger<String> exchanger;
    String name = "d";

    public Car(Exchanger<String> exchanger) {
        super();
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            name = exchanger.exchange("Car");
            System.out.println(Thread.currentThread().getName() + ": " + name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Bike extends Thread {

    private Exchanger<String> exchanger;

    String name = "d";
    public Bike(Exchanger<String> exchanger) {
        super();
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            name = exchanger.exchange("Bike");
            System.out.println(Thread.currentThread().getName() + ": " + name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}