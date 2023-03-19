package com.blog.search.service.search;

import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.dto.response.search.BlogSearchControllerResponse;
import com.blog.search.enums.sort.SearchSort;

public interface BlogSearchService {

    OpenApiResponse getKakaoBlogSearchResult(String query, SearchSort sort, Integer page, Integer size);

    BlogSearchControllerResponse.GetPopularKeywordResponse getPopularKeyword(Integer size);

}
