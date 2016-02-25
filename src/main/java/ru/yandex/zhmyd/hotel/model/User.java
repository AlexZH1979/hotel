package ru.yandex.zhmyd.hotel.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

	private Integer id;

    @Pattern(regexp = "[а-яА-ЯёЁa-zA-Z]*", message = "First name must contain only letters")
    @Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters long")
    private String firstName;

    @Pattern(regexp = "[а-яА-ЯёЁa-zA-Z]*", message = "Last name must contain only letters")
    @Size(min = 3, max = 32, message = "Last name must be between 3 and 32 characters long")
    private String lastName;

    @Pattern(regexp = "[a-zA-Z0-9]*", message = "Login must contain only letters")
    @Size(min = 4, max = 10, message = "Login must be between 4 and 10 characters long")
    private String login;

    @Pattern(regexp = "[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})",
            message = "Email must be  like example@domen.com or my.example@domen.com")
    @Size(min = 6, max = 20, message = "Email must be between 6 and 20 characters long")
    private String email;

    @NotNull(message = "Password don\'t maybe empty")
    private String password;

    private Gender gender;

    private UserRole role;

    public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                ", gender=" + gender +
                ", role=" + role +
                '}';
    }
}
