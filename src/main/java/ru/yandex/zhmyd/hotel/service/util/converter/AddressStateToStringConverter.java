package ru.yandex.zhmyd.hotel.service.util.converter;

import org.dozer.DozerConverter;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.zhmyd.hotel.repository.dao.HotelAddressDao;
import ru.yandex.zhmyd.hotel.repository.entity.HotelAddressStateEntity;

public class AddressStateToStringConverter extends DozerConverter<HotelAddressStateEntity, String> {

    @Autowired
    private HotelAddressDao addressDao;

    public AddressStateToStringConverter() {
        super(HotelAddressStateEntity.class, String.class);
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
    public String convertTo(HotelAddressStateEntity source, String destination) {
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
    public HotelAddressStateEntity convertFrom(String source, HotelAddressStateEntity destination) {
        return addressDao.getByCriteria(Restrictions.eq("name",source)).get(0).getState();
    }
}
