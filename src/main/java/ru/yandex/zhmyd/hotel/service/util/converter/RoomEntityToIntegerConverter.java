package ru.yandex.zhmyd.hotel.service.util.converter;

import org.dozer.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.zhmyd.hotel.repository.dao.RoomDao;
import ru.yandex.zhmyd.hotel.repository.entity.RoomEntity;


public class RoomEntityToIntegerConverter extends DozerConverter<RoomEntity, Integer> {

    @Autowired
    private RoomDao roomDao;

    public RoomEntityToIntegerConverter() {
        super(RoomEntity.class, Integer.class);
    }

    @Override
    public Integer convertTo(RoomEntity source, Integer destination) {
        if(source!=null) {
            return source.getId();
        }else{
            return null;
        }
    }

    @Override
    public RoomEntity convertFrom(Integer source, RoomEntity destination) {
        if(source!=null) {
            return roomDao.getById(source);
        }else{
            return null;
        }
    }
}
