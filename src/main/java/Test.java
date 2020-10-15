import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;
import static org.springframework.http.HttpStatus.OK;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @Author wenbaoxie
 * @Date 2020/9/24
 */
@SpringBootApplication
public class Test implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Test.class);
    }


    @Autowired
    WebClient client;
    private static String blog = "http://localhost:8089";
    private static String weibo = blog;
    @Override public void run(String... args) throws Exception {
         test();
        System.out.println("ddddd");
        System.out.println("mult");
        tests();
    }
    public void test() {
        single(blog);

    }

    private void single(String url) {
        System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddddddd");
        Mono<ClientResponse> mono = client.get().uri(url)
                .accept(MediaType.TEXT_HTML)
                .exchange();
        Mono<String> block = mono.map(
                clientResponse -> {
                    System.out.println(clientResponse.headers());
                    return clientResponse.bodyToMono(String.class);
                }
        ).block();
       block.retry(2).doOnNext((s)-> System.out.println(s +"重试的")).subscribe();
    }


    public void tests() {
        concurrency(3, weibo);
        concurrency(10, blog);
        concurrency(2, blog);
        concurrency(2, weibo);
        concurrency(3, weibo);
        concurrency(2, blog);
    }

    private void concurrency(int num, String url) {
        ExecutorService es = Executors.newFixedThreadPool(num);
        for (int i = 0; i < num; i++) {
            es.execute(() -> single(url));
        }
        es.shutdown();
        try {
            es.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println(num + " completes");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
