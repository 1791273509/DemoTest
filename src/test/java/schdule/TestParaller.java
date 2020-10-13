package schdule;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @Author wenbaoxie
 * @Date 2020/9/25
 */
@Slf4j
public class TestParaller {
//    参照链接  https://blog.csdn.net/tonydz0523/article/details/107861620
//    https://blog.csdn.net/qq_33797928/article/details/105005629

    @Test
    public void streamParallel() {

        Stream.of(1, 2, 3, 4, 5, 6, 7, 8).parallel().map(String::valueOf).forEach(log::info);
        int a = 1;
        int b = 2;
        int finalA = a;
        Mono.just(111).subscribeOn(Schedulers.parallel());

    }

    @Test
    public void publishOnTest() {
        Flux.range(1, 2)
                .map(i -> {
                    log.info("Map 1, the value map to: {}", i * i);
                    return i * i;
                })
                .publishOn(Schedulers.single())
                .map(i -> {
                    log.info("Map 2, the value map to: {}", -i);
                    return -i;
                })
                .publishOn(Schedulers.newParallel("parallel", 4))
                .map(i -> {
                    log.info("Map 3, the value map to: {}", i + 2);
                    return (i + 2) + "";
                })
                .subscribe();
    }

    @Test
    public void subscribeOnTest() throws InterruptedException {
        Flux.range(1, 2)
                .map(i -> {
                    log.info("Map 1, the value map to: {}", i * i);
                    return i * i;
                })
                .subscribeOn(Schedulers.parallel())
                .map(i -> {
                    log.info("Map 2, the value map to: {}", -i);
                    return -i;
                })
                .subscribeOn(Schedulers.newParallel("parallel", 4))
                .map(i -> {
                    log.info("Map 3, the value map to  {}", i + 2);
                    return (i + 2) + "";
                })
                .subscribe();

        Thread.sleep(100);
    }

    @Test
    public void subscribeOnTest2() throws InterruptedException {
        Flux.range(1, 99)
                .map(i -> {
                    log.info("Map 1, the value map to: {}", i * i);
                    return i * i;
                })
                .publishOn(Schedulers.single())
                .map(i -> {
                    log.info("Map 2, the value map to: {}", -i);
                    return -i;
                })
                .subscribeOn(Schedulers.newParallel("parallel", 4))
                .map(i -> {
                    log.info("Map 3, the value map to  {}", i + 2);
                    return (i + 2) + "";
                })
                .subscribe();

        Thread.sleep(100);
    }

    @Test
    public void test() throws InterruptedException {
        Flux.just("tom", "dd")
                .map(s -> {
                    System.out.println("[map] Thread name: " + Thread.currentThread().getName());
                    return s.concat("@mail.com");
                })
                .publishOn(Schedulers.newElastic("thread-publishOn"))
                .filter(s -> {
                    System.out.println("[filter] Thread name: " + Thread.currentThread().getName());
                    return s.startsWith("t");
                })
                .subscribeOn(Schedulers.newElastic("thread-subscribeOn"))
                .subscribe(s -> {
                    System.out.println("[subscribe] Thread name: " + Thread.currentThread().getName());
                    System.out.println(s);
                });
        TimeUnit.SECONDS.sleep(1);
    }
}
