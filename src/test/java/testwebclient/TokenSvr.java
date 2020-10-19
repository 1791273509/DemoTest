package testwebclient;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;
import static org.springframework.http.HttpStatus.OK;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @Author wenbaoxie
 * @Date 2020/10/16
 */
@Slf4j
public class TokenSvr {

    @Test
    public void te1 (){
        System.out.println(Arrays.toString("{}".getBytes()));
    }
    @Test
    public void te (){

        log.info("Token HttpClient getResult Method http send tokenRequest:{}");
     /*   String url = "http://" + tokenRequest.getHost() + ":"
                + tokenRequest.getPort() + PATH + "?access_token" + tokenRequest.getToken();    */
        String url = "http://127.0.0.1:8101/token/check";
        ClientResponse block = WebClient.builder()
                .baseUrl(url)
                .defaultHeaders((httpHeaders) -> {
                    addHeader(httpHeaders);
                    httpHeaders.add("X-Original-URI", "tokenRequest.getUri().toString()");
                    httpHeaders.add("X-Real-IP", "tokenRequest.getIp()");
                })
                .build()
//         使用head方法拿取head中的信息即可，只是head方法获取不到响应体，token-svr 中的响应体可以在head中获取，因此不需要响应体
//         注：HEAD方法跟GET方法相同，只不过服务器响应时不会返回消息体。
                .head()
                .exchange().block();
        System.out.println(block.headers());

    }
    private void addHeader(HttpHeaders httpHeaders) {
        httpHeaders.add("X-Original-REQUEST-METHOD", "HEAD");
        httpHeaders.add("X-Request-Id", "traceidInner");
        httpHeaders.add("X-B3-Traceid", "traceidInner");
        httpHeaders.add("X-B3-SpanId", "traceidInner");
        httpHeaders.add("X-B3-ParentSpanId", "traceidInner");
        httpHeaders.add("Content-Length","0");
        httpHeaders.add("X-Forwarded-For","forward");

    }


}
