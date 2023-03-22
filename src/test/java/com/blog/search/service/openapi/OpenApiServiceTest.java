package com.blog.search.service.openapi;

import com.blog.search.config.OpenApiInfoLocator;
import com.blog.search.dto.info.OpenApiInfo;
import com.blog.search.dto.request.openapi.OpenApiRequestParameter;
import com.blog.search.dto.request.openapi.kakao.KakaoBlogSearchParameter;
import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.dto.response.openapi.kakao.OpenApiResponseKakaoBlogSearch;
import com.blog.search.enums.CompanyType;
import com.blog.search.enums.openapi.KakaoApiType;
import com.blog.search.service.search.BlogSearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
@AutoConfigureMockRestServiceServer
class OpenApiServiceTest {

    @Autowired
    MockRestServiceServer mockServer;

    @Autowired
    OpenApiInfoLocator openApiInfoLocator;

    @Autowired
    BlogSearchService blogSearchService;

    @Autowired
    OpenApiService openApi;

    @Test
    @DisplayName("openApi 외부 API 통신이 정상적으로 동작하는지 테스트 한다.")
    @Timeout(1000)
    void openApiCallTest() throws JsonProcessingException {
        // given
        OpenApiRequestParameter parameter = KakaoBlogSearchParameter.builder()
                .query("hello")
                .build();
        OpenApiInfo openApiInfo = openApiInfoLocator.getOpenApiInfo(CompanyType.KAKAO);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OpenApiResponseKakaoBlogSearch.Meta meta = new OpenApiResponseKakaoBlogSearch.Meta(1000, 100, false);
        OpenApiResponseKakaoBlogSearch.Document document = new OpenApiResponseKakaoBlogSearch.Document("제목", "내용", "url", "이름", "썸네일", ZonedDateTime.now());
        String response = objectMapper.writeValueAsString(new OpenApiResponseKakaoBlogSearch(meta, Arrays.asList(document)));

        mockServer.expect(ExpectedCount.once(), requestTo(openApiInfo.getRequestUrl(parameter.getParameters(), KakaoApiType.BLOG_SEARCH.getUrl())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(response, MediaType.APPLICATION_JSON));

        // when
        OpenApiResponse result = openApi.call(CompanyType.KAKAO, KakaoApiType.BLOG_SEARCH, parameter);

        // then
        mockServer.verify();
        assertAll(() -> assertThat(result.getPagination().getData()).hasSize(1)
                , () -> assertThat(result.getPagination().getData().get(0)).isInstanceOf(OpenApiResponseKakaoBlogSearch.Document.class)
                , () -> assertThat(((OpenApiResponseKakaoBlogSearch.Document) result.getPagination().getData().get(0)).getTitle()).isEqualTo("제목")
        );
    }

}