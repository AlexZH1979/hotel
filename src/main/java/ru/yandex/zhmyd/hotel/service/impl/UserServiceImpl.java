package ru.yandex.zhmyd.hotel.service.impl;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.zhmyd.hotel.model.User;
import ru.yandex.zhmyd.hotel.model.UserRole;
import ru.yandex.zhmyd.hotel.repository.dao.UserDao;
import ru.yandex.zhmyd.hotel.repository.entity.UserEntity;
import ru.yandex.zhmyd.hotel.security.ApplicationUserDetails;
import ru.yandex.zhmyd.hotel.service.UserService;
import ru.yandex.zhmyd.hotel.service.exceptions.ServiceException;

/*
* @Transactional
* interface UserService
 */
@Service
public class UserServiceImpl extends AbstractServiceImpl<User, UserEntity, UserDao, Integer> implements UserService {

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public User getUserByCredits(String login, String password) {

        LOG.debug("GET to getUser login=" + login + ", password=" + password);
        UserEntity userEntity = userDao.getByCredentials(login, password);
        User user = null;
        if (userEntity != null) {
            LOG.debug("FIND: " + userEntity);
            user = mapper.map(userEntity, User.class);
        } else {
            LOG.warn("NO FOUND USER BY CREDENTIALS: login=" + login + ", password=" + password);
        }
        LOG.debug("SEND from ru.yandex.zhmyd.hotel.ru.yandex.zhmyd.hotel.service getUser: " + user);
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public User getUserByPrincipal(ApplicationUserDetails details) {
        return mapper.map(details.getAccount(),User.class);
    }

    @Override
    public void registrationCustomer(User customer) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole(UserRole.CUSTOMER);
        save(customer);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public void save(User user) {
        Criterion cr = Restrictions.or(Restrictions.eq(LOGIN, user.getLogin()),
                Restrictions.eq(EMAIL, user.getEmail()));
        /*
         *   verify before saved, if user which this criteria already exist throws exception
         */
        if (userDao.getByCriteria(cr).isEmpty()) {
            super.save(user);
        } else {
            throw new ServiceException("user with login: "+user.getLogin()+", or email: "+user.getEmail()+" already exist");
        }

    }
}
