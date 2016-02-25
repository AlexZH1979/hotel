package ru.yandex.zhmyd.hotel.repository.dao;


import ru.yandex.zhmyd.hotel.repository.entity.UserEntity;

public interface UserDao extends GenericDao<UserEntity, Integer>{
	
	UserEntity getByCredentials(final String login, final String passwordHasCode);

}
