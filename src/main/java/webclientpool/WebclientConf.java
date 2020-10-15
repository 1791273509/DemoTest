package webclientpool;

import static io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS;
import static io.netty.channel.ChannelOption.TCP_NODELAY;

import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.util.function.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.resources.LoopResources;

/**
 * @Author wenbaoxie
 * @Date 2020/10/13
 */
@Configuration
public class WebclientConf {

    @Bean
    WebClient webClient() {
        Function<HttpClient, HttpClient> mapper = client ->
                client.tcpConfiguration(c ->
//                        连接超时时间
                        c.option(CONNECT_TIMEOUT_MILLIS,5)
                                .option(TCP_NODELAY, false)
                                .doOnConnected(conn -> {
                                    conn.addHandlerLast(new ReadTimeoutHandler(10));
                                    conn.addHandlerLast(new WriteTimeoutHandler(5));
                                }));

        ClientHttpConnector connector =
                new ReactorClientHttpConnector(resourceFactory(), mapper);

        return WebClient.builder().clientConnector(connector).build();
    }

    @Bean
    ReactorResourceFactory resourceFactory() {
        ReactorResourceFactory factory = new ReactorResourceFactory();
        factory.setUseGlobalResources(false);
        factory.setConnectionProvider(ConnectionProvider.fixed("httpClient", 20, 20));
        factory.setLoopResources(LoopResources.create("httpClient", 10, true));
        return factory;
    }

}