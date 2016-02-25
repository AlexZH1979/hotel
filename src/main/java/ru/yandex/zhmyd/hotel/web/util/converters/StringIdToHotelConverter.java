package ru.yandex.zhmyd.hotel.web.util.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.yandex.zhmyd.hotel.model.Hotel;
import ru.yandex.zhmyd.hotel.service.HotelService;

@Component
public class StringIdToHotelConverter implements Converter<String, Hotel>{
    private static final Logger LOG=Logger.getLogger(StringIdToHotelConverter.class);

    @Autowired
    private HotelService hotelService;

    @Override
    public Hotel convert(String source) {
        Hotel hotel=null;
        try{
           hotel= hotelService.getById(Integer.valueOf(source));
        }catch(Exception e){
            LOG.error("Don't convert id="+source+"; to hotel "+e.getMessage());
            throw new NullPointerException("Hotel by id="+source+" not found");
        }
        return hotel;
    }
}
