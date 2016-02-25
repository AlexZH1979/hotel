package ru.yandex.zhmyd.hotel.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotel_address_state")
public class HotelAddressStateEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "state_name")
    private String name;

    @OneToMany(targetEntity = HotelAddressEntity.class, mappedBy = "state", fetch = FetchType.LAZY)
    private List<HotelAddressEntity> addresses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HotelAddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<HotelAddressEntity> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "HotelAddressStateEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
