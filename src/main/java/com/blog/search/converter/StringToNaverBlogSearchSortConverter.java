package com.blog.search.converter;

import com.blog.search.enums.sort.NaverBlogSearchSort;
import org.springframework.core.convert.converter.Converter;

/**
 * Request 받은 String을 NaverBlogSearchSort Type으로 변환해주는 Converter Class
 */
public class StringToNaverBlogSearchSortConverter implements Converter<String, NaverBlogSearchSort> {
    @Override
    public NaverBlogSearchSort convert(String source) {
        return NaverBlogSearchSort.getSearchSortByValue(source);
    }
}
