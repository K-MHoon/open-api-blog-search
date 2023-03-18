package com.blog.search.dto.request.openapi;

import lombok.Getter;

@Getter
public abstract class OpenApiRequest {

    private final String key;
    private final String host;
    private final String url;

    public OpenApiRequest(String key, String host, String url) {
        this.key = key;
        this.host = host;
        this.url = url;
    }

    public abstract String getAuthorization();
}
