package com.example.monolithic.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapperUtil {

    public static <S, T> T map(S source, Class<T> targetClass) {

        ModelMapper subModelMapper = new ModelMapper();
        subModelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        return subModelMapper.map(source, targetClass);
    }

    public static <S, T> T skipMap(S source, Class<T> targetClass) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setSkipNullEnabled(true);

        return modelMapper.map(source, targetClass);
    }

    public static ModelMapper getMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setSkipNullEnabled(true);

        return modelMapper;
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {

        ModelMapper subModelMapper = new ModelMapper();
        subModelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        return source
                .stream()
                .map(element -> subModelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public static <S, T> List<T> mapList(Stream<S> source, Class<T> targetClass) {

        ModelMapper subModelMapper = new ModelMapper();
        subModelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        return source
                .map(element -> subModelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
