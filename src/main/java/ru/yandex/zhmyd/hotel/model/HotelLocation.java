package ru.yandex.zhmyd.hotel.model;

public class HotelLocation {

    private Integer id;

    private String locTimeZone;

    private Double locLat;

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
        return "HotelLocation{" +
                "id=" + id +
                ", locTimeZone='" + locTimeZone + '\'' +
                ", locLat=" + locLat +
                ", locLong=" + locLong +
                '}';
    }
}
