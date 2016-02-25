package ru.yandex.zhmyd.hotel.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "hotel_address_city")
public class HotelAddressCityEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "city_name")
    private String name;

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

    @Override
    public String toString() {
        return "HotelAddressCityEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
