package com.jenson.service.common.convert;

import com.jenson.service.util.StringUtil;

import javax.persistence.AttributeConverter;
import java.util.*;
import java.util.stream.Collectors;

public class IntegerListConvert implements AttributeConverter<List<Integer>,String> {

    private static final String SPLIT_CHAR = ";";
    private static final String EMPTY = "";

    @Override
    public String convertToDatabaseColumn(List<Integer> strings) {
        return Objects.nonNull(strings) ?
                strings.stream().map(Objects::toString).collect(Collectors.joining(SPLIT_CHAR)) :
                EMPTY;
    }

    @Override
    public List<Integer> convertToEntityAttribute(String s) {
        List<Integer> stringList = new ArrayList<>(Collections.emptyList());
        if (StringUtil.isEmpty(s)){
            return stringList;
        }
        return Arrays.stream(s.split(SPLIT_CHAR))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}
