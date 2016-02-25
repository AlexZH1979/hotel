package ru.yandex.zhmyd.hotel.repository.entity;

import org.dozer.Mapping;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room")
@SuppressWarnings("unused")
public class RoomEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Mapping("id")
    private Integer id;

    @Column(name = "name")
    @Mapping("roomName")
    private String roomName;

    @Column(name = "size")
    @Mapping("size")
    private Byte size;

    @Column(name = "price")
    @Mapping("pricePerDay")
    private Double price;

    @Column(name = "description")
    @Mapping("description")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "category")
    @Mapping("category")
    private RoomCategoryEntity category;

    @OneToMany(targetEntity = OrderEntity.class, mappedBy = "room",fetch = FetchType.LAZY)
    private List<OrderEntity> roomOrders;

    @ManyToOne(targetEntity = HotelEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;

    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Byte getSize() {
        return size;
    }

    public void setSize(Byte size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoomCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(RoomCategoryEntity category) {
        this.category = category;
    }

    public List<OrderEntity> getRoomOrders() {
        return roomOrders;
    }

    public void setRoomOrders(List<OrderEntity> roomOrders) {
        this.roomOrders = roomOrders;
    }

    @Override
    public String toString(){
        return "id="+id;
    }
}
