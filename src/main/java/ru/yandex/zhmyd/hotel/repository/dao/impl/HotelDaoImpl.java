package ru.yandex.zhmyd.hotel.repository.dao.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.yandex.zhmyd.hotel.repository.dao.AbstractHibernateDao;
import ru.yandex.zhmyd.hotel.repository.dao.HotelDao;
import ru.yandex.zhmyd.hotel.repository.entity.HotelEntity;

import java.util.List;

import static ru.yandex.zhmyd.hotel.repository.dao.util.SearchParameter.Associations;
import static ru.yandex.zhmyd.hotel.repository.dao.util.SearchParameter.NAME;
import static ru.yandex.zhmyd.hotel.repository.dao.util.SearchParameter.ADDRESS;

@Repository
public class HotelDaoImpl extends AbstractHibernateDao<HotelEntity, Integer> implements HotelDao {

    @Override
    public List<HotelEntity> searchAddressAssociation(String association, String name) {
        List<HotelEntity> cr = getSession().createCriteria(HotelEntity.class).createCriteria(Associations.HOTEL_ADDRESS)
                .createCriteria(association).add(Restrictions.eq(NAME, name)).list();
        return cr;
    }

    @Override
    public List<HotelEntity> searchLikeAddressAssociation(String association, String name) {
        return getSession().createCriteria(HotelEntity.class).createCriteria(Associations.HOTEL_ADDRESS)
                .createCriteria(association).add(getCriterionLikeParameter(NAME,name)).list();
    }

    @Override
    public Integer lengthSearchAddressAssociation(String association, String name) {
        return getSession().createCriteria(HotelEntity.class).createCriteria(Associations.HOTEL_ADDRESS)
                .createCriteria(association).add(Restrictions.eq(NAME, name)).list().size();
    }

    @Override
    public Integer lengthSearchLikeAddressAssociation(String association, String name) {
        return getSession().createCriteria(HotelEntity.class).createCriteria(Associations.HOTEL_ADDRESS)
                .createCriteria(association).add(getCriterionLikeParameter(NAME,name)).list().size();
    }

    @Override
    public List<HotelEntity> searchAddressAssociation(String association, String name, int begin, int count) {
        List<HotelEntity> cr = getSession().createCriteria(HotelEntity.class).createCriteria(Associations.HOTEL_ADDRESS)
                .createCriteria(association).add(Restrictions.eq(NAME, name))
                .setFirstResult(begin).setMaxResults(count).list();
        return cr;
    }

    @Override
    public List<HotelEntity> searchLikeAddressAssociation(String association, String name, int begin, int count) {
        return getSession().createCriteria(HotelEntity.class).createCriteria(Associations.HOTEL_ADDRESS)
                .createCriteria(association).add(getCriterionLikeParameter(NAME, name)).setFirstResult(begin)
                .setMaxResults(count).list();
    }

    @Override
    public List<HotelEntity> searchLikeAddress(String name) {
        return getSession().createCriteria(HotelEntity.class).createCriteria(Associations.HOTEL_ADDRESS)
                .add(getCriterionLikeParameter(ADDRESS,name)).list();
    }

    @Override
    public List<HotelEntity> searchLikeAddress(String name, int begin, int count) {
        return getSession().createCriteria(HotelEntity.class).createCriteria(Associations.HOTEL_ADDRESS)
                .add(getCriterionLikeParameter(ADDRESS,name))
                .setFirstResult(begin).setMaxResults(count).list();
    }

    @Override
    public Integer lengthSearchLikeAddress(String name) {
        return getSession().createCriteria(HotelEntity.class).createCriteria(Associations.HOTEL_ADDRESS)
                .add(getCriterionLikeParameter(ADDRESS,name)).list().size();
    }
    /*
    * method support search by begin word or fraze
    * parameter - reflection name of variable, value - value of variable
     */
    private Criterion getCriterionLikeParameter(String parameter, String value){
        return Restrictions.or(Restrictions.like(parameter, "% " + value + "%"), Restrictions.like(parameter, value + "%"));
    }
}
