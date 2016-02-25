package ru.yandex.zhmyd.hotel.repository.entity;

import org.dozer.Mapping;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotels_motels")
@SuppressWarnings("unused")
public class HotelEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Mapping("id")
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "category")
    @Mapping("category")
    private HotelCategoryEntity category;

    @Column(name = "name")
    @Mapping("name")
    private String name;

    @OneToOne(targetEntity = HotelAddressEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
   @Mapping("hotelAddress")
    private HotelAddressEntity hotelAddress;

    @OneToOne(targetEntity = HotelLocationEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @Mapping("hotelLocation")
    private HotelLocationEntity hotelLocation;

    @OneToMany(targetEntity = RoomEntity.class, mappedBy = "hotel",fetch = FetchType.LAZY)
    private List<RoomEntity> rooms;

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HotelCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(HotelCategoryEntity category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HotelAddressEntity getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(HotelAddressEntity hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public HotelLocationEntity getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(HotelLocationEntity hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    @Override
    public String toString() {
        return "HotelEntity{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", hotelAddress=" + hotelAddress +
                ", hotelLocation=" + hotelLocation +
                '}';
    }
}
