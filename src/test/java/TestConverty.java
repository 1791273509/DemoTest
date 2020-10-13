import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
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
        System.out.println("d");
        String block = WebClient.builder().build().post().uri("http://127.0.0.1:8089/d")
                .retrieve().onStatus(HttpStatus::is4xxClientError, res -> Mono.error(new RuntimeException("dddd")))
                .bodyToMono(String.class).block();
        System.out.println(block);
        WebClient webClient = WebClient.builder().build();
        Mono<ClientResponse> mono = webClient.post().uri("http://127.0.0.1:8089/d").exchange();
        ClientResponse response = mono.block();
        Headers headers = response.headers();
     /*   LOG.info("++++{}",response);
        LOG.info("++++{}",headers.header("d"));
        LOG.info("++++{}",headers.header("x"));*/
        if (response.statusCode() == HttpStatus.OK) {
            Mono<String> resultMono = response.bodyToMono(String.class);
            resultMono.subscribe(result -> {

            });
        }
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
        String block = WebClient.builder().build().post().uri("http://127.0.0.1:8089/d")
                .retrieve().onStatus(HttpStatus::is4xxClientError, res -> Mono.error(new RuntimeException("dddd")))
                .bodyToMono(String.class).block();
        System.out.println(block);
        WebClient webClient = WebClient.builder().build();
        Mono<ClientResponse> mono = webClient.post().uri("http://127.0.0.1:8089/d").exchange();
        ClientResponse response = mono.block();
        Headers headers = response.headers();
        log.info("++++{}", response);
        log.info("++++{}", headers.header("d"));
        log.info("++++{}", headers.header("x"));
        if (response.statusCode() == HttpStatus.OK) {
            Mono<String> resultMono = response.bodyToMono(String.class);
            resultMono.subscribe(result -> {
            });
        }
    }
}
