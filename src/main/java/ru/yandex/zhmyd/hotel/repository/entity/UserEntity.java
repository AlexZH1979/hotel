package ru.yandex.zhmyd.hotel.repository.entity;

import org.dozer.Mapping;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity implements Serializable{

    @Id
    @Mapping("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Mapping("firstName")
    @Column(name = "first_name", nullable = false, unique = false)
    private String firstName;

    @Mapping("lastName")
    @Column(name = "last_name", nullable = false, unique = false)
    private String lastName;

    @Mapping("login")
    @Column(name = "user_login", nullable = false, unique = true)
    private String login;

    @Mapping("email")
    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Mapping("password")
    @Column(name = "password_hash_code", nullable = false, unique = false)
    private String passwordHashCode;

    @Mapping("gender")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_gender", nullable = false, unique = false)
    private GenderEntity gender;

    @Mapping("role")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_role", nullable = false, unique = false)
    private UserRoleEntity role;

    @OneToMany(targetEntity = OrderEntity.class, mappedBy = "customer", fetch = FetchType.LAZY)
    private List<OrderEntity> roomOrders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPasswordHashCode() {
        return passwordHashCode;
    }

    public void setPasswordHashCode(String passwordHashCode) {
        this.passwordHashCode = passwordHashCode;
    }

    public GenderEntity getGender() {
        return gender;
    }

    public void setGender(GenderEntity gender) {
        this.gender = gender;
    }

    public UserRoleEntity getRole() {
        return role;
    }

    public void setRole(UserRoleEntity role) {
        this.role = role;
    }

    public List<OrderEntity> getRoomOrders() {
        return roomOrders;
    }

    public void setRoomOrders(List<OrderEntity> roomOrders) {
        this.roomOrders = roomOrders;
    }

    @Override
    public String toString() {
        return "User: " + firstName + ", " + lastName + ", " + login + ", " + email;
    }
}
