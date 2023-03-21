package com.blog.search.dto.info;

import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

public interface OpenApiInfo {
    HttpHeaders getHttpHeaders();
    String getRequestUrl(UriComponentsBuilder parameter, String url);
}
