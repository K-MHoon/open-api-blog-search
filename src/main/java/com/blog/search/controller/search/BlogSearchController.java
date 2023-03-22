package com.blog.search.controller.search;

import com.blog.search.dto.Pagination;
import com.blog.search.dto.request.openapi.kakao.KakaoBlogSearchParameter;
import com.blog.search.dto.request.openapi.naver.NaverBlogSearchParameter;
import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.dto.response.search.BlogSearchControllerResponse;
import com.blog.search.enums.CompanyType;
import com.blog.search.enums.openapi.KakaoApiType;
import com.blog.search.enums.openapi.NaverApiType;
import com.blog.search.enums.sort.NaverBlogSearchSort;
import com.blog.search.service.openapi.OpenApiService;
import com.blog.search.service.search.BlogSearchService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * OpenApi 블로그 검색을 조회하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class BlogSearchController {

    private final BlogSearchService blogSearchService;
    private final OpenApiService openApiService;

    /**
     * 카카오 API 블로그 검색 조회
     *
     * @param parameter
     * @return
     */
    @GetMapping("/api/kakao/search")
    @ResponseStatus(HttpStatus.OK)
    public Pagination getKakaoBlogSearchResult(@ModelAttribute @Validated KakaoBlogSearchParameter parameter) {
        log.info("[getKakaoBlogSearchResult] call parameter = {}", parameter);

        blogSearchService.saveSearchHistory(parameter.getQuery(), CompanyType.KAKAO);
        OpenApiResponse response = openApiService.call(CompanyType.KAKAO, KakaoApiType.BLOG_SEARCH, parameter);

        return response.getPagination();
    }

    /**
     * 네이버 API 블로그 검색 조회
     *
     * @param parameter
     * @return
     */
    @GetMapping("/api/naver/search")
    @ResponseStatus(HttpStatus.OK)
    public Pagination getNaverBlogSearchResult(@ModelAttribute @Validated NaverBlogSearchParameter parameter) {
        log.info("[getNaverBlogSearchResult] call parameter = {}", parameter);

        blogSearchService.saveSearchHistory(parameter.getQuery(), CompanyType.NAVER);
        OpenApiResponse response = openApiService.call(CompanyType.NAVER, NaverApiType.BLOG_SEARCH, parameter);

        return response.getPagination();
    }

    /**
     * 인기 검색어 목록 조회
     *
     * @param size
     * @return
     */
    @GetMapping("/api/search/keyword")
    @ResponseStatus(HttpStatus.OK)
    public BlogSearchControllerResponse.GetPopularKeywordResponse getPopularKeyword(@RequestParam(value = "size", required = false, defaultValue = "1") @Min(1) @Max(10) Integer size) {
        log.info("[getPopularKeyword] call size = {}", size);

        return blogSearchService.getPopularKeyword(size);
    }
}
