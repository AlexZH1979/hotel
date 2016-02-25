package ru.yandex.zhmyd.hotel.repository.dao.impl;

import static ru.yandex.zhmyd.hotel.repository.dao.util.SearchParameter.Associations;
import static ru.yandex.zhmyd.hotel.repository.dao.util.SearchParameter.ID;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.yandex.zhmyd.hotel.repository.dao.AbstractHibernateDao;
import ru.yandex.zhmyd.hotel.repository.dao.RoomDao;
import ru.yandex.zhmyd.hotel.repository.entity.OrderEntity;
import ru.yandex.zhmyd.hotel.repository.entity.RoomEntity;

import java.util.List;

@Repository
@SuppressWarnings("unused,unchecked")
public class RoomDaoImpl extends AbstractHibernateDao<RoomEntity, Integer> implements RoomDao {

    private static final Logger LOG=Logger.getLogger(RoomDaoImpl.class);

    @Override
    public List<OrderEntity> getOrdersByRoomId(final Integer id) {
        Criteria criteria=getSession().createCriteria(OrderEntity.class);
        Criteria roomCriteria=criteria.createCriteria(Associations.ROOM);
        roomCriteria.add(Restrictions.eq(ID, id));
        return criteria.list();
    }

    /*
     * sample SQL <- this is begin
     * select * from (select * from room where room.size=SIZE and room.hotel_id=HOTEL_ID) as r left join (SELECT * FROM room_order o where hotel_id=HOTEL_ID and
     * not((o.start_date<START_DATE and o.end_date<START_DATE)
     * or (o.start_date>END_DATE and o.end_date>END_DATE))) as t on r.id=t.room_id where t.id is null;
     */
    @SuppressWarnings("all")
    @Override
    public List<RoomEntity> getFreeRooms(final OrderEntity order) {

        String hql ="FROM RoomEntity R WHERE R.hotel=:hotel AND R.category=:category AND R.size=:sizze AND R NOT IN " +
                "(SELECT O.room  FROM OrderEntity O WHERE O.hotel=:hotel AND NOT " +
                "((O.startDate<:startDate AND O.endDate<:startDate) OR (O.startDate>:endDate AND O.endDate>:endDate)))";

        Query query=getSession().createQuery(hql);
        query.setParameter("startDate", order.getStartDate());
        query.setParameter("endDate", order.getEndDate());
        query.setParameter("sizze", order.getPlaces());
        query.setParameter("category", order.getRoomCategory());
        query.setParameter("hotel", order.getHotel());
        List<RoomEntity> rooms=query.list();

        return rooms;
    }

    @Override
    @Deprecated
    public List<RoomEntity> getAll(){
        throw new UnsupportedOperationException("Don't support for "+RoomEntity.class+
                ", cause - very bad performance. Use List<T> getByCriteria(Criterion criterion, int begin, int count);");
    }
}
