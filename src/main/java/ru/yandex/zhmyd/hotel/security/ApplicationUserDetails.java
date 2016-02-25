package ru.yandex.zhmyd.hotel.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ru.yandex.zhmyd.hotel.repository.entity.UserEntity;

import java.util.Collection;

public class ApplicationUserDetails extends User {
	
	private static final long serialVersionUID = 98078L;
	private UserEntity userEntity;

	public ApplicationUserDetails(
            String username, String password,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
		
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
	}

	public ApplicationUserDetails(
            UserEntity user,
            Collection<GrantedAuthority> authorities) {
		super(user.getLogin(), user.getPasswordHashCode(), authorities);

		this.userEntity = user;
	}

	public UserEntity getAccount() {
		return userEntity;
	}

	public void setAccount(UserEntity userPrincipal) {
		this.userEntity = userPrincipal;
	}
}
