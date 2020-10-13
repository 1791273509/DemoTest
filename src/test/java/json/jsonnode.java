package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @Author wenbaoxie
 * @Date 2020/9/25
 */
public class jsonnode {
    @Test
    public void te() throws JsonProcessingException {
        String json = "{ \"f1\" : \"v1\" } ";

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(json);

        System.out.println(jsonNode.get("f1").asText());

        JsonNode jsonNode1 = objectMapper.readTree("{\"a\":1,\"b\":2,\"c\":{\"d\":4}}");
        System.out.println(jsonNode1.at("/c/d"));
        System.out.println(jsonNode1.get("c").asText());


        System.out.println("+++++++++++++++++++++++++++");
        Mono.just("ddd").subscribeOn(Schedulers.parallel()).doOnNext((x) -> System.out.println(x)).subscribe(System.out::print);

    }
}
