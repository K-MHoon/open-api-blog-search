package com.blog.search.dto.response.openapi;

import com.blog.search.dto.Pagination;

/**
 * 각 Open Api 별로 Response 정보를 담은 인터페이스
 */
public interface OpenApiResponse {

    /**
     * Pagination 형태로 캡슐화 해서 내보낸다.
     *
     * @return
     */
    Pagination getPagination();
}
