package com.blog.search.controller.search;

import com.blog.search.dto.Pagination;
import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.enums.sort.SearchSort;
import com.blog.search.service.search.BlogSearchService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
public class BlogSearchController {

    private final BlogSearchService blogSearchService;

    @GetMapping("/api/search/kakao")
    @ResponseStatus(HttpStatus.OK)
    public Pagination getKakaoBlogSearchResult(@RequestParam("query") String query,
                                               @RequestParam(value = "sort", required = false) SearchSort sort,
                                               @RequestParam(value = "page", required = false) @Min(1) @Max(50) Integer page,
                                               @RequestParam(value = "size", required = false) @Min(1) @Max(50) Integer size) {
        log.info("[getKakaoBlogSearchResult] called query = {}, sort = {}, page = {}, size = {}", query, sort, page, size);

        OpenApiResponse response = blogSearchService.getKakaoBlogSearch(query, sort, page, size);

        return response.getPagination();
    }
}
