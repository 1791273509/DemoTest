package webclientTest;

import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @Author wenbaoxie
 * @Date 2020/9/24
 */
public class TestWebClient {
    @Test
    public void test_client_string(){
        WebClient webClient = WebClient.create();
        WebClient webClient1 = WebClient.builder().baseUrl("baseUrl").defaultCookie("cookieName", "cookieValue").build();
        Mono<String> mono = webClient.get().uri("https://www.baidu.com").retrieve().bodyToMono(String.class);
        mono.subscribe(System.out::println);
    }
    @Test
    public void test_return_Object(){
        WebClient webClient = WebClient.create();
        Mono<Object> mono = webClient.get().uri("https://www.baidu.com").retrieve().bodyToMono(Object.class);
        mono.subscribe(System.out::println);
    }
    @Test
    public void test_web_client(){
//        设置基本的属性在builder中，然后build产生WebClient
        WebClient web = WebClient.builder().baseUrl("http://localhost:8089").build();
        web.get().uri(uriBuilder -> uriBuilder.path("/echo").queryParam("d","d").build()).retrieve().bodyToMono(String.class).block();
    }
}
