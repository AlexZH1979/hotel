package ru.yandex.zhmyd.hotel.service.impl;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.zhmyd.hotel.repository.dao.GenericDao;
import ru.yandex.zhmyd.hotel.service.BasicService;
import ru.yandex.zhmyd.hotel.service.util.mapper.Util;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * T -target DTO type
 * E- entity type
 * DAO - DaoImpl class
 * ID - primary key value (id)
 */
public abstract class AbstractServiceImpl<T, E, DAO extends GenericDao, ID extends Serializable> implements BasicService<T, ID> {

    private static final Logger LOG = Logger.getLogger(AbstractServiceImpl.class);

    @Autowired
    @SuppressWarnings("all")
    protected DAO dao;


    @Autowired
    protected Mapper mapper;


    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true,propagation = Propagation.REQUIRED)
    @Override
    public T getById(ID id) {
        LOG.debug("GET to getById id=" + id);
        E entity = (E) dao.getById(id);
        LOG.debug("FIND entity=" + entity);
        T dto = null;
        if (entity != null) {
            dto = mapper.map(entity, this.getGenericTargetClass());
        }
        LOG.debug("RETURN from ru.yandex.zhmyd.hotel.ru.yandex.zhmyd.hotel.service getById dto=" + dto);
        return dto;
    }

    @SuppressWarnings("unchecked")
    @Deprecated
    @Transactional(readOnly = true)
    public List<T> getAll() {
        LOG.debug("GET to getAll");
        return (List<T>) Util.map(mapper, dao.getAll(), this.getGenericTargetClass());
    }

    @Transactional(readOnly = true)
    @Override
    public Long getSizeList(){
        return dao.getLength();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getInterval(Integer begin, Integer count) {
        LOG.debug("GET to getInterval values: begin=" + begin + ", count=" + count);
        return getInterval(new HashMap(), begin, count);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    @SuppressWarnings("unchecked")
    public List<T> getInterval(Map param,Integer begin, Integer count) {
        LOG.debug("GET to getInterval values: begin=" + begin + ", count=" + count);
        return (List<T>) Util.map(mapper, dao.getByCriteria(Restrictions.allEq(param), begin, count), this.getGenericTargetClass());
    }

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    @Override
    public void save(T dto) {
        LOG.debug("GET to save dto=" + dto);
        E entity = mapper.map(dto, this.getGenericEntityClass());
        LOG.debug("MAPPED entity=" + entity);
        dao.save(entity);
        LOG.debug("SAVED entity to db");
    }

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    @Override
    public void update(T dto) {
        LOG.debug("GET to save dto=" + dto);
        E entity = mapper.map(dto, this.getGenericEntityClass());
        LOG.debug("MAPPED entity=" + entity);
        dao.update(entity);
        LOG.debug("SAVED entity to db");
    }

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    @Override
    public void delete(T t) {
        LOG.info("GET to delete dto=" + t);
        E entity = mapper.map(t, this.getGenericEntityClass());
        LOG.info("FIND entity="+entity);
        dao.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(ID id) {
        LOG.debug("GET to delete id=" + id);
        dao.delete(id);
    }

    @SuppressWarnings("unchecked")
    private Class<T> getGenericTargetClass() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    private Class<E> getGenericEntityClass() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<E>) parameterizedType.getActualTypeArguments()[1];
    }
}
