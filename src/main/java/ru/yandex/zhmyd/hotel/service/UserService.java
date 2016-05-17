package ru.yandex.zhmyd.hotel.service;

import ru.yandex.zhmyd.hotel.model.User;
import ru.yandex.zhmyd.hotel.repository.dao.util.SearchParameter;
import ru.yandex.zhmyd.hotel.security.ApplicationUserDetails;

import javax.transaction.Transactional;

@Transactional
public interface UserService extends BasicService<User, Integer>,SearchParameter {

    User getUserByCredits(String login, String password);
    User getUserByPrincipal(ApplicationUserDetails details);

    void registrationCustomer(User customer);
}
