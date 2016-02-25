package ru.yandex.zhmyd.hotel.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.zhmyd.hotel.model.HotelAddress;
import ru.yandex.zhmyd.hotel.repository.dao.HotelAddressDao;
import ru.yandex.zhmyd.hotel.repository.dao.util.SearchParameter;
import ru.yandex.zhmyd.hotel.repository.entity.HotelAddressCityEntity;
import ru.yandex.zhmyd.hotel.repository.entity.HotelAddressCountyEntity;
import ru.yandex.zhmyd.hotel.repository.entity.HotelAddressEntity;
import ru.yandex.zhmyd.hotel.repository.entity.HotelAddressStateEntity;
import ru.yandex.zhmyd.hotel.service.AddressService;

import java.util.*;

import static ru.yandex.zhmyd.hotel.repository.dao.util.SearchParameter.Associations.*;


@Service
public class AddressServiceImpl extends AbstractServiceImpl<HotelAddress, HotelAddressEntity, HotelAddressDao, Integer> implements AddressService {

    private static final Logger LOG = Logger.getLogger(AddressServiceImpl.class);

    @Autowired
    private HotelAddressDao addressDao;

    @Transactional(readOnly = true)
    @Override
    public List<String> getCounties(String state) {
        return addressDao.getCounties(state);
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getStates() {
        return addressDao.getStates();
    }

    //param->return type

    // ""->"state"->"county"->"city"->"zip"
    @Transactional(readOnly = true)
    @Override
    public List<String> getNameSubParameters(String param, String value){
        return addressDao.getNameSubParameters(param, value);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public void updateHotelAddressFromMap(Integer hotelId, Map<String, String> map) {

        LOG.info("map address: " + map);
        String[] strings = {STATE, COUNTY, CITY, ZIP, ADDRESS};

        Set<String> sample = new HashSet<>();
        Collections.addAll(sample, strings);

        if (!map.keySet().containsAll(sample)) {
            throw new IllegalArgumentException("Not full map for build address");
        }

        String state = map.get(STATE);
        String county = map.get(COUNTY);
        String city = map.get(CITY);
        String zip = map.get(ZIP);
        String address = map.get(ADDRESS);
        if (!getStates().contains(state)) {
            throw new IllegalArgumentException("Not valid state for build address");
        }
        if (!getCounties(state).contains(county)) {
            throw new IllegalArgumentException("Not valid county for build address");
        }
        if(!getNameSubParameters(COUNTY, county).contains(city) ){
            throw new IllegalArgumentException("Not valid city for build address");
        }
        if(!getNameSubParameters(CITY, city).contains(Integer.valueOf(zip)) ||
                address == null){
            throw new IllegalArgumentException("Not valid zip for build address");
        }else{
            HotelAddressEntity hotelAddress = addressDao.getById(hotelId);
            hotelAddress.setState((HotelAddressStateEntity) addressDao.getSubParameters(STATE, state));
            hotelAddress.setCounty((HotelAddressCountyEntity) addressDao.getSubParameters(COUNTY, county));
            hotelAddress.setCity((HotelAddressCityEntity) addressDao.getSubParameters(CITY, city));
            hotelAddress.setZip(Integer.valueOf(zip));
            hotelAddress.setAddress(address);
            LOG.info("HotelAddressEntity " + hotelAddress);

            addressDao.update(hotelAddress);
        }

    }
}
