package ru.yandex.zhmyd.hotel.service.util.converter;

import org.dozer.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.zhmyd.hotel.repository.dao.HotelDao;
import ru.yandex.zhmyd.hotel.repository.entity.HotelEntity;

public class HotelEntityToIntegerConverter extends DozerConverter<HotelEntity,Integer> {

    @Autowired
    private HotelDao hotelDao;

    public HotelEntityToIntegerConverter() {
        super(HotelEntity.class, Integer.class);
    }

    @Override
    public Integer convertTo(HotelEntity source, Integer destination) {
        if(source!=null) {
            return source.getId();
        }else{
            return null;
        }
    }

    @Override
    public HotelEntity convertFrom(Integer source, HotelEntity destination) {
        if(source!=null) {
            return hotelDao.getById(source);
        }else{
            return null;
        }
    }
}
