package com.blog.search.service.search;

import com.blog.search.config.OpenApiInfoLocator;
import com.blog.search.dto.request.openapi.OpenApiRequestParameter;
import com.blog.search.dto.request.openapi.kakao.KakaoBlogSearchParameter;
import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.dto.response.openapi.kakao.OpenApiResponseKakaoBlogSearch;
import com.blog.search.dto.response.search.BlogSearchControllerResponse;
import com.blog.search.entity.search.SearchHistory;
import com.blog.search.enums.CompanyType;
import com.blog.search.enums.openapi.KakaoApiType;
import com.blog.search.enums.sort.KakaoBlogSearchSort;
import com.blog.search.repository.search.SearchHistoryJpaRepository;
import com.blog.search.service.openapi.OpenApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.net.BindException;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Transactional
class BlogSearchServiceTest {

    @Autowired
    OpenApiService openApiService;

    @Autowired
    OpenApiInfoLocator openApiInfoLocator;

    @Autowired
    BlogSearchService blogSearchService;

    @Autowired
    SearchHistoryJpaRepository searchHistoryJpaRepository;

    @Test
    @DisplayName("카카오 블로그 검색에 성공한다.")
    @Timeout(1000)
    void getKakaoBlogSearchResultSuccess() {
        String query = "테스트";
        KakaoBlogSearchSort sort = null;
        Integer page = null;
        Integer size = null;
        OpenApiRequestParameter parameter = new KakaoBlogSearchParameter(query, sort, page, size);

        OpenApiResponse result = openApiService.call(CompanyType.KAKAO, KakaoApiType.BLOG_SEARCH, parameter);

        assertThat(result).isInstanceOf(OpenApiResponseKakaoBlogSearch.class);
    }

    @Test
    @DisplayName("카카오 블로그 검색에 성공하면, searchHistory가 저장된다.")
    @Timeout(1000)
    void saveKakaoSearchHistoryWhenSearchSuccess() {
        String query = "테스트";

        blogSearchService.saveSearchHistory(query, CompanyType.KAKAO);

        List<SearchHistory> historyList = searchHistoryJpaRepository.findAll();
        assertAll(() -> assertThat(historyList).hasSize(1)
        , () -> assertThat(historyList.get(0).getQuery()).isEqualTo(query));
    }

    @Test
    @DisplayName("검색어 값이 없으면 예외가 발생한다.")
    @Timeout(1000)
    void causeExceptionWhenNotInputQuery() {
        String query = null;
        KakaoBlogSearchSort sort = null;
        Integer page = null;
        Integer size = null;
        OpenApiRequestParameter parameter = new KakaoBlogSearchParameter(query, sort, page, size);

        assertThatThrownBy(() -> openApiService.call(CompanyType.KAKAO, KakaoApiType.BLOG_SEARCH, parameter))
                .isInstanceOf(HttpClientErrorException.class)
                .extracting("statusCode")
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("카카오 블로그 검색 조건 중 SearchSort가 null이면 정확도(Accuracy) 순서로 조회된다.")
    @Timeout(1000)
    void getKakaoBlogSearchResultAccurateIfSearchSortIsNull() {
        String query = "테스트";
        KakaoBlogSearchSort sort = null;
        Integer page = null;
        Integer size = null;
        OpenApiRequestParameter parameter = new KakaoBlogSearchParameter(query, sort, page, size);
        OpenApiRequestParameter parameter2 = new KakaoBlogSearchParameter(query, KakaoBlogSearchSort.ACCURACY, page, size);

        OpenApiResponse originalResult = openApiService.call(CompanyType.KAKAO, KakaoApiType.BLOG_SEARCH, parameter);
        OpenApiResponse targetResult = openApiService.call(CompanyType.KAKAO, KakaoApiType.BLOG_SEARCH, parameter2);

        assertThat(originalResult.getPagination().getData())
                .containsExactlyElementsOf(targetResult.getPagination().getData());
    }

    @Test
    @DisplayName("카카오 블로그 검색 조건 중 SearchSort가 RECENCY 이면 최신순(Accuracy) 순서로 조회된다.")
    @Timeout(1000)
    void getKakaoBlogSearchResulRecencyIfSearchSortIsRecency() {
        // given
        String query = "테스트";
        KakaoBlogSearchSort sort = KakaoBlogSearchSort.RECENCY;
        Integer page = null;
        Integer size = null;
        OpenApiRequestParameter parameter = new KakaoBlogSearchParameter(query, sort, page, size);

        // when
        OpenApiResponseKakaoBlogSearch result = (OpenApiResponseKakaoBlogSearch) openApiService.call(CompanyType.KAKAO, KakaoApiType.BLOG_SEARCH, parameter);

        // then
        List<ZonedDateTime> zonedDateTimeList = result.getDocuments().stream()
                .map(OpenApiResponseKakaoBlogSearch.Document::getDatetime)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
        assertThat(result.getDocuments().get(0).getDatetime()).isEqualTo(zonedDateTimeList.get(0));
    }

    @Test
    @DisplayName("카카오 블로그 검색 조건 중 page가 50을 초과하면 예외가 발생한다.")
    @Timeout(1000)
    void getKakaoBlogSearchResultAccurateIfPageHigherThan50() {
        String query = "테스트";
        KakaoBlogSearchSort sort = null;
        Integer page = 51;
        Integer size = null;
        OpenApiRequestParameter parameter = new KakaoBlogSearchParameter(query, sort, page, size);

        assertThatThrownBy(() -> openApiService.call(CompanyType.KAKAO, KakaoApiType.BLOG_SEARCH, parameter))
                .isInstanceOf(HttpClientErrorException.class)
                .extracting("statusCode")
                .isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    @DisplayName("카카오 블로그 검색 조건 중 size가 50을 초과하면 예외가 발생한다.")
    @Timeout(1000)
    void getKakaoBlogSearchResultAccurateIfSizeHigherThan50() {
        String query = "테스트";
        KakaoBlogSearchSort sort = null;
        Integer page = null;
        Integer size = 51;
        OpenApiRequestParameter parameter = new KakaoBlogSearchParameter(query, sort, page, size);

        assertThatThrownBy(() -> openApiService.call(CompanyType.KAKAO, KakaoApiType.BLOG_SEARCH, parameter))
                .isInstanceOf(HttpClientErrorException.class)
                .extracting("statusCode")
                .isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    @DisplayName("카카오 블로그 검색 조건 중 size가 null이면 10개가 조회된다.")
    @Timeout(1000)
    void getKakaoBlogSearchResultAccurateIfSizeIsNull() {
        String query = "테스트";
        KakaoBlogSearchSort sort = null;
        Integer page = null;
        Integer size = null;
        OpenApiRequestParameter parameter = new KakaoBlogSearchParameter(query, sort, page, size);

        OpenApiResponse result = openApiService.call(CompanyType.KAKAO, KakaoApiType.BLOG_SEARCH, parameter);

        assertThat(result.getPagination().getData()).hasSize(10);
    }

    @Test
    @DisplayName("인기 검색어 목록 조회가 성공한다.")
    @Timeout(1000)
    void getPopularKeywordSuccess() {
        createSimpleSearchHistory("테스트1");
        createSimpleSearchHistory("테스트2");
        createSimpleSearchHistory("테스트3");

        BlogSearchControllerResponse.GetPopularKeywordResponse popularKeyword = blogSearchService.getPopularKeyword(10);

        assertAll(() -> assertThat(popularKeyword.getPopularKeywordList()).hasSize(3),
                () -> assertThat(popularKeyword.getPopularKeywordList()).extracting("query").containsExactly("테스트1","테스트2","테스트3"));
    }

    private void createSimpleSearchHistory(String query) {
        SearchHistory searchHistory1 = new SearchHistory(query, CompanyType.KAKAO);
        searchHistoryJpaRepository.save(searchHistory1);
    }

    @Test
    @DisplayName("검색 조회가 많은 순서대로 검색어가 조회된다.")
    @Timeout(1000)
    void getPopularKeywordOrderByCountDesc() {
        createTestSearchHistory(5, "테스트2");
        createTestSearchHistory(10, "테스트1");
        createTestSearchHistory(7, "테스트4");
        createTestSearchHistory(6, "테스트3");

        BlogSearchControllerResponse.GetPopularKeywordResponse popularKeyword = blogSearchService.getPopularKeyword(10);

        assertAll(() -> assertThat(popularKeyword.getPopularKeywordList()).hasSize(4),
                () -> assertThat(popularKeyword.getPopularKeywordList()).extracting("query").containsExactlyInAnyOrder("테스트1","테스트2","테스트3","테스트4"),
                () -> assertThat(popularKeyword.getPopularKeywordList().get(0).getQuery()).isEqualTo("테스트1"),
                () -> assertThat(popularKeyword.getPopularKeywordList().get(0).getCount()).isEqualTo(10));
    }

    private void createTestSearchHistory(int endInclusive, String query) {
        IntStream.rangeClosed(1, endInclusive)
                .mapToObj(i -> new SearchHistory(query, CompanyType.KAKAO))
                .forEach(searchHistoryJpaRepository::save);
    }
}