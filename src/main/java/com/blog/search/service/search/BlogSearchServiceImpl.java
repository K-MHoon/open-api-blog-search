package com.blog.search.service.search;

import com.blog.search.dto.request.openapi.kakao.OpenApiRequestKakao;
import com.blog.search.dto.request.openapi.kakao.OpenApiRequestParameterKakaoBlogSearch;
import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.dto.response.openapi.kakao.OpenApiResponseKakaoBlogSearch;
import com.blog.search.enums.sort.SearchSort;
import com.blog.search.service.openapi.OpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BlogSearchServiceImpl implements BlogSearchService {

    @Value("${api.kakao.key}")
    private String key;

    @Value("${api.kakao.host}")
    private String host;

    @Value("${api.kakao.url}")
    private String url;

    @Override
    public OpenApiResponse getKakaoBlogSearch(String query, SearchSort sort, Integer page, Integer size) {
        OpenApiRequestParameterKakaoBlogSearch parameter = OpenApiRequestParameterKakaoBlogSearch.builder()
                .query(query)
                .sort(sort)
                .page(page)
                .size(size)
                .build();
        OpenApiRequestKakao kakaoRequest = new OpenApiRequestKakao(key, host, url, parameter);
        OpenApi openApi = new OpenApi(new OpenApiResponseKakaoBlogSearch(), kakaoRequest);

        return openApi.call();
    }
}
