package ru.yandex.zhmyd.hotel.repository.entity;

import org.dozer.Mapping;

import javax.persistence.*;

@Entity
@Table(name = "hotel_geo_location")
@SuppressWarnings("unused")
public class HotelLocationEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Mapping("id")
    private Integer id;

    @Column(name = "loc_TimeZone")
    @Mapping("locTimeZone")
    private String locTimeZone;

    @Column(name = "loc_Lat")
    @Mapping("locLat")
    private Double locLat;

    @Column(name = "loc_Long")
    @Mapping("locLong")
    private Double locLong;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocTimeZone() {
        return locTimeZone;
    }

    public void setLocTimeZone(String locTimeZone) {
        this.locTimeZone = locTimeZone;
    }

    public Double getLocLat() {
        return locLat;
    }

    public void setLocLat(Double locLat) {
        this.locLat = locLat;
    }

    public Double getLocLong() {
        return locLong;
    }

    public void setLocLong(Double locLong) {
        this.locLong = locLong;
    }

    @Override
    public String toString() {
        return "HotelLocationEntity{" +
                "id=" + id +
                ", locTimeZone='" + locTimeZone + '\'' +
                ", locLat=" + locLat +
                ", locLong=" + locLong +
                '}';
    }
}
