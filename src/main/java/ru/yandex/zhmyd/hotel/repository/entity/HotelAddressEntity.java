package ru.yandex.zhmyd.hotel.repository.entity;

import org.dozer.Mapping;

import javax.persistence.*;

@Entity
@Table(name = "hotel_geo_address")
@SuppressWarnings("unused")
public class HotelAddressEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Mapping("id")
    private Integer id;

    @Column(name = "address")
    @Mapping("address")
    private String address;

    @ManyToOne(targetEntity = HotelAddressCityEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    @Mapping("city")
    private HotelAddressCityEntity city;

    @ManyToOne(targetEntity = HotelAddressStateEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "state_id")
    @Mapping("state")
    private HotelAddressStateEntity state;

    @Column(name = "zip")
    @Mapping("zip")
    private Integer zip;

    @ManyToOne(targetEntity = HotelAddressCountyEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "county_id")
    @Mapping("county")
    private HotelAddressCountyEntity county;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HotelAddressCityEntity getCity() {
        return city;
    }

    public void setCity(HotelAddressCityEntity city) {
        this.city = city;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public HotelAddressCountyEntity getCounty() {
        return county;
    }

    public void setCounty(HotelAddressCountyEntity county) {
        this.county = county;
    }

    public HotelAddressStateEntity getState() {
        return state;
    }

    public void setState(HotelAddressStateEntity state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "HotelAddressEntity{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state=" + state +
                ", zip=" + zip +
                ", county=" + county +
                '}';
    }
}
