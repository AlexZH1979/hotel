package ru.yandex.zhmyd.hotel.repository.dao.impl;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.yandex.zhmyd.hotel.repository.dao.AbstractHibernateDao;
import ru.yandex.zhmyd.hotel.repository.dao.UserDao;
import ru.yandex.zhmyd.hotel.repository.entity.UserEntity;

import static ru.yandex.zhmyd.hotel.repository.dao.util.SearchParameter.Associations;
import static ru.yandex.zhmyd.hotel.repository.dao.util.SearchParameter.LOGIN;
import static ru.yandex.zhmyd.hotel.repository.dao.util.SearchParameter.PASSWORD_HASH_CODE;

@Repository
public class UserDaoImpl extends AbstractHibernateDao<UserEntity, Integer> implements UserDao {

    @Override
    public UserEntity getByCredentials(final String login, final String passwordHasCode) {
        Criteria cr = getSession().createCriteria(UserEntity.class, Associations.USERS)
                .add(Restrictions.eq(LOGIN, login))
                .add(Restrictions.eq(PASSWORD_HASH_CODE, passwordHasCode));

        return (UserEntity) cr.uniqueResult();
    }

    @Override
    public UserEntity getById(final Integer id) {
        return (UserEntity)  getSession().get(UserEntity.class, id);
    }
}
