package ru.yandex.zhmyd.hotel.service.util.converter;

import org.dozer.DozerConverter;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.zhmyd.hotel.repository.dao.HotelAddressDao;
import ru.yandex.zhmyd.hotel.repository.entity.HotelAddressCityEntity;

public class AddressCityToStringConverter extends DozerConverter<HotelAddressCityEntity, String> {

    @Autowired
    private HotelAddressDao addressDao;

    public AddressCityToStringConverter() {
        super(HotelAddressCityEntity.class, String.class);
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
    public String convertTo(HotelAddressCityEntity source, String destination) {
        if(source==null){
            return "";
        }
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
    public HotelAddressCityEntity convertFrom(String source, HotelAddressCityEntity destination) {
        return addressDao.getByCriteria(Restrictions.eq("name",source)).get(0).getCity();
    }
}