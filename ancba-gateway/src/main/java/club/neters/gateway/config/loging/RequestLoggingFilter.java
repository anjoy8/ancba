package club.neters.gateway.config.loging;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
public class RequestLoggingFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        ServerWebExchangeDecorator decorator =
                new ServerWebExchangeDecorator(serverWebExchange) {
                    @Override
                    public ServerHttpRequest getRequest() {
                        return new RequestLoggingDecorator(serverWebExchange.getRequest());
                    }
                };

        return webFilterChain.filter(decorator);
    }
}