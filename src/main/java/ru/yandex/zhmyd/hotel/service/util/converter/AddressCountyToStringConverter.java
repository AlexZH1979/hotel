package ru.yandex.zhmyd.hotel.service.util.converter;

import org.dozer.DozerConverter;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.zhmyd.hotel.repository.dao.HotelAddressDao;
import ru.yandex.zhmyd.hotel.repository.entity.HotelAddressCountyEntity;

public class AddressCountyToStringConverter extends DozerConverter<HotelAddressCountyEntity, String> {

    @Autowired
    private HotelAddressDao addressDao;

    public AddressCountyToStringConverter() {
        super(HotelAddressCountyEntity.class, String.class);
    }

    /**
     * Converts the source field to the destination field and return the resulting destination
     * value.
     *
     * @param source      the value of the source field
     * @param destination the current value of the desitinatino field (or null)
     * @return the resulting value for the destinatino field
     */
    @Override
    public String convertTo(HotelAddressCountyEntity source, String destination) {
        return source.getName();
    }

    /**
     * Converts the source field to the destination field and return the resulting destination
     * value
     *
     * @param source      the value of the source field
     * @param destination the current value of the desitinatino field (or null)
     * @return the resulting value for the destinatino field
     */
    @Override
    public HotelAddressCountyEntity convertFrom(String source, HotelAddressCountyEntity destination) {
        return addressDao.getByCriteria(Restrictions.eq("name",source)).get(0).getCounty();
    }
}
