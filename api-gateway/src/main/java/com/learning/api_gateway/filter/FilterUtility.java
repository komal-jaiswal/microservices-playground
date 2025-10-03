package com.learning.api_gateway.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class FilterUtility {
    public static final String CORRELATION_ID = "mylearning-correlation-id";

    public String getCorrelationId(HttpHeaders headers) {
        if (headers.get(CORRELATION_ID) != null) {
            List<String> headerValues = headers.get(CORRELATION_ID);
            return headerValues.stream().findFirst().get();
        }
        return null;
    }
    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId){
       return exchange.mutate().request(exchange.getRequest().mutate().header(CORRELATION_ID,correlationId).build()).build();
    }

}
