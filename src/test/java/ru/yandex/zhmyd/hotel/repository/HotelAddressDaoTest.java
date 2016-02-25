package ru.yandex.zhmyd.hotel.repository;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.zhmyd.hotel.repository.dao.HotelAddressDao;

import javax.transaction.Transactional;

import static ru.yandex.zhmyd.hotel.model.parameters.SearchParameter.Associations.CITY;

@Transactional
public class HotelAddressDaoTest extends AbstractDaoTest{

    private static final Logger LOG=Logger.getLogger(HotelAddressDaoTest.class);

    @Autowired
    private HotelAddressDao addressDao;

/*    @Ignore
    @Test
    public void getCountyList(){
        LOG.info(addressDao.getStates());
        LOG.info(addressDao.getCounties("MI"));
        LOG.info(addressDao.getCounties("WA"));
    }

    @Ignore
    @Test
    public void getCityList(){
        LOG.info(addressDao.getCities("Columbia"));
        LOG.info(addressDao.getCities("Douglas"));
    }*/

    @Test
    public void getListSubParameter(){
        LOG.info(addressDao.getCounties("WA"));
        LOG.info(addressDao.getNameSubParameters(CITY,"Alexandria"));
        LOG.info(addressDao.getNameSubParameters(CITY,"Alexandria"));
    }
}
