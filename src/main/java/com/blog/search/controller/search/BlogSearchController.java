package com.blog.search.controller.search;

import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.enums.sort.SearchSort;
import com.blog.search.service.search.BlogSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BlogSearchController {

    private final BlogSearchService blogSearchService;

    @GetMapping("/api/search/kakao")
    @ResponseStatus(HttpStatus.OK)
    public OpenApiResponse getKakaoBlogSearchResult(@RequestParam("query") String query,
                                                    @RequestParam(value = "sort", required = false) SearchSort sort,
                                                    @RequestParam(value = "page", required = false) Integer page,
                                                    @RequestParam(value = "size", required = false) Integer size) {
        log.info("[getKakaoBlogSearchResult] called query = {}, sort = {}, page = {}, size = {}", query, sort, page, size);

        return blogSearchService.getKakaoBlogSearch(query, sort, page, size);
    }
}