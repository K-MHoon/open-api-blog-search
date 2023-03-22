package com.blog.search.dto.info;

import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 각 Api 별 요청에 필요한 정보를 담고 있는 인터페이스
 */
public interface OpenApiInfo {

    /**
     * Open Api를 호출하기 위한 헤더를 반환한다.
     *
     * @return
     */
    HttpHeaders getHttpHeaders();

    /**
     * Open Api에 전달할 Url을 생성한다.
     *
     * @param parameter 파라미터 정보
     * @param url 요청 Url
     * @return
     */
    String getRequestUrl(UriComponentsBuilder parameter, String url);
}
