package com.capitole.technical_interview.infrastructure.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@ControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    private static final List<String> EXCLUDED_PATHS = List.of(
            "/swagger-ui", "/swagger-ui.html", "/v3/api-docs", "/v3/api-docs.yaml", "/swagger-resources"
    );

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request, ServerHttpResponse response) {

        String requestPath = request.getURI().getPath();

        if (EXCLUDED_PATHS.stream().anyMatch(requestPath::contains)) {
            return body;
        }

        if (body instanceof ErrorResponse apiResponse) {
            return apiResponse;
        }

        return SuccessResponse.of(HttpStatus.OK, body);
    }
}
