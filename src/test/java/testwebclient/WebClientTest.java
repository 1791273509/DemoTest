package testwebclient;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;
import static org.springframework.http.HttpStatus.OK;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @Author wenbaoxie
 * @Date 2020/10/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebClientTest {
    @Autowired
    private WebClient client;

    private static String blog = "https://blog.csdn.net/weixin_43364172";
    private static String weibo = "http://www.weibo.com";

    @Test
    public void test() {
        single(blog);
    }

    private void single(String url) {
        Mono<ClientResponse> mono = client.get().uri(url)
                .accept(MediaType.TEXT_HTML)
                .exchange();

        StepVerifier.create(mono)
                .assertNext(clientResponse -> {
                    Assert.assertThat(clientResponse.statusCode(),
                            anyOf(equalTo(OK), equalTo(MOVED_PERMANENTLY)));
                    if (clientResponse.rawStatusCode() == OK.value()) {
/*                        Mono<String> res = clientResponse.bodyToMono(String.class);
                        StepVerifier.create(res)
                                .assertNext(s -> Assert.assertThat(s.length(), greaterThan(0)))
                                .verifyComplete();*/
                        clientResponse.bodyToMono(String.class).subscribe(s -> System.out.println(s.length()));
                    }
                })
                .verifyComplete();
    }


    @Test(timeout = 10000)
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

