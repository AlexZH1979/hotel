package ru.yandex.zhmyd.hotel.model;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Hotel {

    private Integer id;

    private HotelCategory category;

    @Pattern(regexp = "[a-zA-Z0-9_ ]*", message = "Invalid name")
    @Size(min = 4, max = 30, message = "Name must be between 4 and 30 characters long")
    private String name;

    private HotelAddress hotelAddress;

    private HotelLocation hotelLocation;

    public Integer getId() {
        return id;
    }


    public HotelAddress getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(HotelAddress hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public HotelLocation getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(HotelLocation hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HotelCategory getCategory() {
        return category;
    }

    public void setCategory(HotelCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", hotelAddress=" + hotelAddress +
                ", hotelLocation=" + hotelLocation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;

        Hotel hotel = (Hotel) o;

        if (category != hotel.category) return false;
        if (!id.equals(hotel.id)) return false;
        if (!name.equals(hotel.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
