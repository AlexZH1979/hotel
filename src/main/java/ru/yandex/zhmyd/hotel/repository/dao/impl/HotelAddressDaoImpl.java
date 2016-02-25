package ru.yandex.zhmyd.hotel.repository.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import ru.yandex.zhmyd.hotel.repository.dao.AbstractHibernateDao;
import ru.yandex.zhmyd.hotel.repository.dao.HotelAddressDao;
import ru.yandex.zhmyd.hotel.repository.entity.HotelAddressEntity;

import java.util.List;

import static ru.yandex.zhmyd.hotel.model.parameters.SearchParameter.Associations.*;

@Repository
public class HotelAddressDaoImpl extends AbstractHibernateDao<HotelAddressEntity, Integer> implements HotelAddressDao{

    //""->"state"
    @Override
    public List<String> getStates(){
        return getNameSubParameters("","");
    }
    
    //""->state"->"county"
    @Override
    public List<String> getCounties(String value){
        return getNameSubParameters(STATE, value);
    }

    //""->"state"->"county"->"city"->"zip"
    @SuppressWarnings("all")
    @Override
     public List<String> getNameSubParameters(String param, String value){
        String hql;
        Query query;
        switch (param){
            case "":
                hql="SELECT name FROM HotelAddressStateEntity GROUP BY name";
                query=getSession().createQuery(hql);
                break;
            case STATE:
                hql="SELECT C.name FROM HotelAddressCountyEntity C, HotelAddressEntity A " +
                        "WHERE (FROM HotelAddressStateEntity S  WHERE S.name=:state_name)=A.state " +
                        "AND C=A.county  GROUP BY C.name";
                query=getSession().createQuery(hql);
                query.setParameter("state_name",value);
                break;
            case COUNTY:
                hql="SELECT CI.name FROM HotelAddressCityEntity CI, HotelAddressEntity A " +
                        "WHERE (FROM HotelAddressCountyEntity CO  WHERE CO.name=:county_name)=A.county " +
                        "AND CI=A.city  GROUP BY CI.name";
                query=getSession().createQuery(hql);
                query.setParameter("county_name",value);
                break;
            case CITY:
                hql="SELECT A.zip FROM HotelAddressEntity A, HotelAddressCityEntity CI " +
                        " WHERE CI.name=:city_name AND CI=A.city GROUP BY A.zip";
                query=getSession().createQuery(hql);
                query.setParameter("city_name",value);
                break;
            default:
                return null;
        }
        return query.list();
    }

    @SuppressWarnings("all")
    @Override
    public Object getSubParameters(String param, String value){
        String hql;
        Query query;
        switch (param){
            case STATE:
                hql="FROM HotelAddressStateEntity S  WHERE S.name=:state_name";
                query=getSession().createQuery(hql);
                query.setParameter("state_name",value);
                break;
            case COUNTY:
                hql="FROM HotelAddressCountyEntity CO  WHERE CO.name=:county_name";
                query=getSession().createQuery(hql);
                query.setParameter("county_name",value);
                break;
            case CITY:
                hql="FROM HotelAddressCityEntity CI " +
                        " WHERE CI.name=:city_name";
                query=getSession().createQuery(hql);
                query.setParameter("city_name",value);
                break;
            default:
                return null;
        }
        return query.uniqueResult();
    }
}
