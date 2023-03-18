package com.blog.search.converter;

import com.blog.search.enums.sort.SearchSort;
import org.springframework.core.convert.converter.Converter;

public class StringToSearchSortConverter implements Converter<String, SearchSort> {

    @Override
    public SearchSort convert(String source) {
        return SearchSort.getSearchSortByValue(source);
    }
}
