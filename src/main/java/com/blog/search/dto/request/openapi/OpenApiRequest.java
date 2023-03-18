package com.blog.search.dto.request.openapi;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class OpenApiRequest {

    private final String key;
    private final String host;
    private final String url;
    private final OpenApiRequestParameter parameter;

    public abstract String getAuthorization();
    public abstract String getRequestUrl();
}
