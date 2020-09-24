package monodefer;

import org.junit.Test;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Optional;

/**
 * @Author wenbaoxie
 * @Date 2020/9/24
 */
public class MonoDeferTest {
    @Test
    public void defer(){
        //声明阶段创建DeferClass对象
//        defer就是一种延迟床架你的东西，属于懒汉式，而just是饿汉模式
        Mono<Date> m1 = Mono.just(new Date());
        Mono<Date> m2 = Mono.defer(()->Mono.just(new Date()));
        m1.subscribe(System.out::println);
        m2.subscribe(System.out::println);
        //延迟5秒钟
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m1.subscribe(System.out::println);
        m2.subscribe(System.out::println);
    }
    @Test
    public void testMonoNull(){
        Mono<String> mon = Mono.justOrEmpty(null);
        mon.doOnNext(System.out::print).subscribe();
    }
    }
