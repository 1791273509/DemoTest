package random;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author wenbaoxie
 * @Date 2020/9/25
 */
public class TestNumRandom {
    @Test
    public void get() {
        StringBuilder nonce = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            int ele = ThreadLocalRandom.current().nextInt(1, 9);
            nonce.append(ele);
        }

        System.out.println(nonce.toString());
    }
    public void get1() throws InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<Integer>> callables = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            callables.add(random::nextInt);
        }

        Instant t1 = Instant.now();
        executor.invokeAll(callables);
        System.out.println(t1.getNano());
    }

    @Test
    public void testRandom() throws InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<Integer>> callables = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            callables.add(random::nextInt);
        }

        Instant t1 = Instant.now();
        executor.invokeAll(callables);
        System.out.println(t1.getNano());
    }
}
