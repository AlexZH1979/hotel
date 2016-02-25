package ru.yandex.zhmyd.hotel.repository.dao;


import ru.yandex.zhmyd.hotel.repository.entity.HotelEntity;

import java.util.List;

public interface HotelDao extends GenericDao<HotelEntity, Integer>{
    List<HotelEntity> searchAddressAssociation(String association, String name);

    List<HotelEntity> searchLikeAddressAssociation(String association, String name);

    Integer lengthSearchAddressAssociation(String association, String name);

    Integer lengthSearchLikeAddressAssociation(String association, String name);

    List<HotelEntity> searchAddressAssociation(String association, String name, int begin, int count);

    List<HotelEntity> searchLikeAddressAssociation(String association, String name, int begin, int count);

    List<HotelEntity> searchLikeAddress(String name);

    List<HotelEntity> searchLikeAddress(String name, int begin, int count);

    Integer lengthSearchLikeAddress(String name);
}
