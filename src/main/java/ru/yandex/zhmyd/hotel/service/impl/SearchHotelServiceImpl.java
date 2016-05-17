package ru.yandex.zhmyd.hotel.service.impl;

import org.dozer.Mapper;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.zhmyd.hotel.model.Hotel;
import ru.yandex.zhmyd.hotel.repository.dao.HotelDao;
import ru.yandex.zhmyd.hotel.service.SearchHotelService;
import ru.yandex.zhmyd.hotel.service.util.mapper.Util;

import java.util.List;

@Service
public class SearchHotelServiceImpl implements SearchHotelService {

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private Mapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<Hotel> searchByAddress(String association, String name,Integer begin, Integer count) {
        switch (association) {
            case ADDRESS:
                return Util.map(mapper, hotelDao.searchLikeAddress(name,begin, count), Hotel.class);
            case Associations.STATE:
                return Util.map(mapper, hotelDao.searchAddressAssociation(association, name, begin, count), Hotel.class);
            case Associations.CITY:
            case Associations.COUNTY:
                return Util.map(mapper, hotelDao.searchLikeAddressAssociation(association, name, begin, count), Hotel.class);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Hotel> searchByName(String name, Integer begin, Integer count) {
        return Util.map(mapper, hotelDao.getByCriteria(getCriterionForLikeName(name), begin, count), Hotel.class);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
    @Override
    public Long lengthSearchByName(String name) {
        return hotelDao.getLength(getCriterionForLikeName(name));
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
    @Override
    public Long lengthSearchByAddress(String association, String name) {
        switch (association) {
            case ADDRESS:
                return Long.valueOf(hotelDao.lengthSearchLikeAddress(name));
            case Associations.STATE:
                return Long.valueOf(hotelDao.lengthSearchAddressAssociation(association, name));
            case Associations.CITY:
            case Associations.COUNTY:
                return Long.valueOf(hotelDao.lengthSearchLikeAddressAssociation(association, name));
            default:
                return 0L;
        }
    }

    private Criterion getCriterionForLikeName(String name){
        return Restrictions.or(Restrictions.like(NAME, "% " + name + "%"),
                Restrictions.like(NAME, name + "%"));
    }
}
