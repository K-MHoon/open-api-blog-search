package com.blog.search.service.search;

import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.enums.sort.SearchSort;

public interface BlogSearchService {

    OpenApiResponse getKakaoBlogSearch(String query, SearchSort sort, Integer page, Integer size);
}
