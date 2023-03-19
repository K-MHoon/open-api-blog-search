package com.blog.search.service.openapi;

import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.enums.sort.SearchSort;
import com.blog.search.service.search.BlogSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class OpenApiTest {

    @Autowired
    BlogSearchService blogSearchService;

    @Test
    void openApiCallTest() {
        String query = "링딩동";
        SearchSort sort = null;
        Integer page = null;
        Integer size = null;

        OpenApiResponse result = blogSearchService.getKakaoBlogSearchResult(query, sort, page, size);

        System.out.println(result);
    }

    @Test
    void dateParse() {
        String date = "2023-02-14T19:44:22.000+09:00";
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        LocalDateTime parse = LocalDateTime.parse(date, pattern);
        System.out.println(parse);
    }

}