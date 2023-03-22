package com.blog.search.dto.request.openapi;

import org.springframework.web.util.UriComponentsBuilder;

/**
 * 각 Api 별로 Request 파라미터 정보를 담은 인터페이스
 */
public interface OpenApiRequestParameter {

    /**
     * 파라미터 정보를 UriComponentsBuilder 형식으로 반환한다.
     *
     */
    UriComponentsBuilder getParameters();
}
