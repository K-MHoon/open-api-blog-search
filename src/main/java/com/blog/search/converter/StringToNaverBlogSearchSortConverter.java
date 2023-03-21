package com.blog.search.converter;

import com.blog.search.enums.sort.NaverBlogSearchSort;
import org.springframework.core.convert.converter.Converter;

public class StringToNaverBlogSearchSortConverter implements Converter<String, NaverBlogSearchSort> {
    @Override
    public NaverBlogSearchSort convert(String source) {
        return NaverBlogSearchSort.getSearchSortByValue(source);
    }
}
