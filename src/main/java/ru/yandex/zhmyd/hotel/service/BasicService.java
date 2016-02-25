package ru.yandex.zhmyd.hotel.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BasicService<T, ID extends Serializable> {

    /**
     * <b>WARNING</b> this method may shows bad performance for big collections returning from db
     *
     * @return List of all values type T
     */
    public List<T> getAll();

    public Long getSizeList();
    /**
     * Return subsequence of present values
     *
     * @param begin begin of interval
     * @param count length of interval returning values
     * @return List of values type T in depended interval,
     * if presents <b>values < count</b> return only available values,
     * if begin beyond the available values or count contains incorrect value
     * this method return null
     */
    public List<T> getInterval(Integer begin, Integer count);
    public List<T> getInterval(Map param, Integer begin, Integer count);
    /**
     * @param Id object id
     * @return Object or null if object doesn't exist
     */
    public T getById(ID Id);

    public void save(T obj);

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    void update(T dto);

    public void delete(T obj);

    public void delete(ID id);
}
