import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ClientResponse.Headers;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @Author wenbaoxie
 * @Date 2020/9/30
 */
@Slf4j
public class TestConverty {

    @Test
    public void tes() {
        String[] split = "1=2&2=4".split("=|&");
        System.out.println(Arrays.toString(split));
    }

    @Test
    public void test() {
        System.out.println("d2");
        Mono<String> x = WebClient.builder().defaultHeaders(h -> addHeader(h)).build().get()
                .uri("http://127.0.0.1:8089/")
                .retrieve().bodyToMono(String.class);
        String block1 = x.block();
        System.out.println("xxxxxxxxxx"+block1);

        String block = WebClient.builder().build().post().uri("http://127.0.0.1:8089/").headers(h -> addHeader(h))
                .retrieve().onStatus(HttpStatus::is4xxClientError, res -> Mono.error(new RuntimeException("dddd")))
                .bodyToMono(String.class).block();
        System.out.println("ddd" + block);
        WebClient webClient = WebClient.builder().build();
        Mono<ClientResponse> mono = webClient.post().uri("http://127.0.0.1:8089/").exchange();
        ClientResponse response = mono.block();
        Headers headers = response.headers();
        System.out.println(headers);
        System.out.println("d");
    }

    private void addHeader(HttpHeaders httpHeaders) {
        httpHeaders.add("X-Original-REQUEST-METHOD", "HEAD");
        httpHeaders.add("X-Request-Id", "traceidInner");
        httpHeaders.add("X-B3-Traceid", "traceidInner");
        httpHeaders.add("X-B3-SpanId", "traceidInner");
        httpHeaders.add("X-B3-ParentSpanId", "traceidInner");
        httpHeaders.add("Content-Length", "0");
        httpHeaders.add("X-Forwarded-For", "forward");

    }

    @Test
    public void test1() {
        Set set = new HashSet();
        set.add(new ArrayList<>());
        set.add(new ArrayList<>());
        System.out.println(set.size());
    }

    @Test
    public void test12() {
        ArrayList<String> aList = new ArrayList<String>();
        aList.add("bbc");
        aList.add("abc");
        aList.add("ysc");
        aList.add("saa");
        System.out.println("移除前：" + aList);
        ListIterator<String> listIt = aList.listIterator();
        while (listIt.hasNext()) {
            if ("abc".equals(listIt.next())) {
                listIt.add("haha");
            }
        }
        System.out.println("移除后：" + aList);
    }

    @Test
    public void tests12() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add(1);
        objects.add(1);
        Iterator<Object> iterator = objects.iterator();
        Iterator<Object> iterator2 = objects.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
            iterator2.next();
            iterator2.remove();
            break;
        }
        System.out.println(objects);
    }

    @Test
    public void tests122() {
        ArrayList<Object> objects1 = new ArrayList<>();
        try {
            System.out.println(objects1.get(1));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        int[] original = new int[2];
        original[0] = 100;
        original[1] = 100;
        int[] ints = Arrays.copyOf(original, 1);
        System.out.println(Arrays.toString(ints));
    }

    public static void main(String[] args) {
     /*   String block = WebClient.builder().build().post().uri("localhost:8101/token/check?access_token=213121")
                .retrieve().onStatus(HttpStatus::is4xxClientError, res -> Mono.error(new RuntimeException("dddd")))
                .bodyToMono(String.class).block();
        System.out.println(block);*/
        WebClient webClient = WebClient.builder().build();
        Mono<ClientResponse> mono = webClient.head().uri("http://10.25.92.17:8101/token/check?access_token=213121")
                .exchange().map((clientResponse -> {
                    Headers headers = clientResponse.headers();
                    HttpHeaders httpHeaders = headers.asHttpHeaders();
                    String body = httpHeaders.getFirst("WWW-Authenticate");
                    String traceId = httpHeaders.getFirst("X-B3-TraceId");
                    HttpStatus status = clientResponse.statusCode();
//          打印响应头
                    log.info("body:{},traceId:{},status:{}", body, traceId, status);

                    if (HttpStatus.UNAUTHORIZED.equals(status)) {
                        System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                    }
                    return clientResponse;
                }));
        ClientResponse response = mono.block();
        Headers headers = response.headers();
        log.info("++++{}", response);
        log.info("++++{}", headers.asHttpHeaders());
        log.info("++++{}", headers.header("WWW-Authenticate"));
        Mono<String> resultMono = response.bodyToMono(String.class);
    }
}
