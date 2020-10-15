package reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author wenbaoxie
 * @Date 2020/9/16
 */
@Slf4j
public class Reactor {
    @Test
    public void testCreate() {
//        从数组中创建
        Integer[] tem = new Integer[5];
        for (int i = 0; i < tem.length; i++) {
            tem[i] = i;
        }
        print(Flux.fromArray(tem));
//        从流中创建
        print(Flux.fromStream(Stream.of(tem)));

        ArrayList<Integer> objects = new ArrayList<>();
        for (int i = 0; i < tem.length; i++) {
            objects.add(i);
        }
//        从迭代器中创建,这里使用集合，因为集合实现了iterable
        print(Flux.fromIterable(objects));

        System.out.println("+++++++++++++++++++++");
//        Flux.interval(Duration.of(10, TemporalUnit));
        Duration duration = Duration.ofDays(1);
        System.out.println(60 * 60 * 24);
        System.out.println(duration.getSeconds());
//        LocalDate.
//        使用just产生 Create a Flux that emits the provided elements and then completes.
        Flux.just(1, 2, 3, 4);
//        产生从1开始的，数量为10的序列 Flux序列
        Flux<Integer> range = Flux.range(1, 10);
        range.doOnNext(System.out::println).subscribe();
//        Programmatically create a Flux by generating signals one-by-one via a consumer callback.
//        动态产生 generate() 方法

        System.out.println("测试generate，最多只能调用一次next");
        Flux<Integer> generate = Flux.generate((sink) -> {
            sink.next(100);
//            必须要有这个complete方法,表示结束
            sink.complete();
        });
        generate.doOnNext(System.out::print).subscribe();
        System.out.println("+++++++++");
        System.out.println("测试create 可以多次调用next");
        Flux.create((sink) -> {
            for (int i = 0; i < 30; i++) {
                sink.next(i);
            }
            sink.complete();
        }).doOnNext(System.out::print).subscribe();
    }

    private void print(Flux<?> flux) {
        flux.subscribe(System.out::print);
    }

    @Test
    public void testinter() {
        Mono<Long> delay = Mono.delay(Duration.ofSeconds(1));
        delay.block();
        delay.doOnNext(System.out::print).retry().subscribe();
    }
    @Test
    public void testRetry() {

    }

        @Test
    public void midoperate() {
        Flux<Integer> objectFlux = Flux.create((sink) -> {
                    for (int i = 0; i < 5; i++) {
                        sink.next(i);
                    }
                }
        );
//        需要注意的是buffe中的cap一定要有，默认是Integer.MAX_VALUE，不够的话就不打印，总共5个数字，这里就打印了第一组 0,1,2其余的就丢失了 。。。
        Flux<List<Integer>> buffer = objectFlux.buffer(2);
        buffer.subscribe(System.out::println);
        System.out.println("\n++++++++++++++++++");
        print(objectFlux.map(x -> x + 100));
        System.out.println("\n++++++++++++++++++");
//        将其转成一个流，然后再合并
        print(objectFlux.flatMap(x -> Flux.just(x, 1, 2, 4)));
        System.out.println("\n+++++++++++++++++++++++ startWith");
//        以什么开头
        System.out.println("以什么开头");
        print(objectFlux.startWith(1,2,3));
        System.out.println("\n ++++++++++++++");
        Flux.just(1,2,3).concatWith(Flux.just(2,3,4)).subscribe(System.out::println);
        System.out.println("++++++++++++++");
        Mono.just(11).concatWith(Flux.just(12,3)).subscribe(System.out::println);
//concatWith好像对于create创建的不生效，这里只有just才生效,concatWith在末尾加入，这里封装了一层，这样就可以加入了
        System.out.println("++++++++++++");
        Flux.just(objectFlux).concatWith(Flux.just(Flux.just(1,2,33))).subscribe((x) -> {
            x.subscribe(System.out::println);
        });
        System.out.println("+++++++++++");
        objectFlux.concatWith(Flux.just(1,2,33)).subscribe(System.out::println);
    }
}
