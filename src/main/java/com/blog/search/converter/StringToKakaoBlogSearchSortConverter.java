package com.blog.search.converter;

import com.blog.search.enums.sort.KakaoBlogSearchSort;
import org.springframework.core.convert.converter.Converter;

public class StringToKakaoBlogSearchSortConverter implements Converter<String, KakaoBlogSearchSort> {

    @Override
    public KakaoBlogSearchSort convert(String source) {
        return KakaoBlogSearchSort.getSearchSortByValue(source);
    }
}
