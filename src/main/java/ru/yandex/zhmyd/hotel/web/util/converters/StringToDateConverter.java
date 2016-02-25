package ru.yandex.zhmyd.hotel.web.util.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        Long numDate=Long.valueOf(source);

        return new Date(numDate);
    }
}
