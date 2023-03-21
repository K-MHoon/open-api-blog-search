package com.blog.search.controller.search;

import com.blog.search.config.OpenApiInfoLocator;
import com.blog.search.dto.info.OpenApiInfo;
import com.blog.search.entity.search.SearchHistory;
import com.blog.search.enums.CompanyType;
import com.blog.search.repository.search.SearchHistoryJpaRepository;
import com.blog.search.service.search.BlogSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogSearchControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BlogSearchService blogSearchService;

    @Autowired
    SearchHistoryJpaRepository searchHistoryJpaRepository;

    @Autowired
    OpenApiInfoLocator locator;

    @Test
    @DisplayName("Get 카카오 블로그 검색어 조회 - /api/kakao/search")
    @Timeout(1000)
    void getKakaoBlogSearchResultSuccess() throws Exception {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", "테스트");

        OpenApiInfo openApiInfo = locator.getOpenApiInfo(CompanyType.KAKAO);

        ResultActions result = mockMvc.perform(get("http://localhost:8080/api/kakao/search")
                .params(params)
                .headers(openApiInfo.getHttpHeaders())
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.size()").value(10))
                .andDo(print());
    }

    @Test
    @DisplayName("GET 인기 검색어 조회 API - /api/search/keyword")
    @Transactional
    @Timeout(1000)
    void getPopularKeywordSuccess() throws Exception {
        // given
        SearchHistory searchHistory = new SearchHistory("테스트", CompanyType.KAKAO);
        searchHistoryJpaRepository.save(searchHistory);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("size", "10");

        // when
        ResultActions result = mockMvc.perform(get("/api/search/keyword")
                .params(params)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.popularKeywordList.size()").value(1))
                .andExpect(jsonPath("$.popularKeywordList[0].query").value("테스트"))
                .andDo(print());
    }
}