package ru.yandex.zhmyd.hotel.repository.entity;

import org.dozer.Mapping;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "room_order")
@SuppressWarnings("unused")
public class OrderEntity {

    @Id
    @Mapping("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;

    @Mapping("price")
    @Column(name = "price")
	private Double price;

    @Mapping("places")
    @Column(name = "places")
    private  Byte places;

    @Mapping("roomId")
    @ManyToOne(targetEntity = RoomEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
	private RoomEntity room;

    @Mapping("hotelId")
    @ManyToOne(targetEntity = HotelEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
	private HotelEntity hotel;

    @Mapping("roomCategory")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "room_category",  unique = false)
    private RoomCategoryEntity roomCategory;

    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	private UserEntity customer;

    @Mapping("startDate")
    @Column(name = "start_date")
	private Date startDate;

    @Mapping("endDate")
    @Column(name = "end_date")
	private Date endDate;

    @Mapping("confirmed")
    @Column(name = "confirmed")
	private Boolean confirmed;

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final Double getPrice() {
		return price;
	}

	public final void setPrice(Double price) {
		this.price = price;
	}

	public final RoomEntity getRoom() {
		return room;
	}

	public final void setRoom(RoomEntity room) {
		this.room = room;
	}

	public final UserEntity getCustomer() {
		return customer;
	}

	public final void setCustomer(UserEntity customer) {
		this.customer = customer;
	}

	public final Date getStartDate() {
		return startDate;
	}

	public final void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public final Date getEndDate() {
		return endDate;
	}

	public final void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public final Boolean getConfirmed() {
		return confirmed;
	}

	public final void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

    public RoomCategoryEntity getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategoryEntity roomCategory) {
        this.roomCategory = roomCategory;
    }

    public Byte getPlaces() {
        return places;
    }

    public void setPlaces(Byte places) {
        this.places = places;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", price=" + price +
                ", places=" + places +
                ", hotelId=" + hotel.getId() +
                ", roomCategory=" + roomCategory +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", confirmed=" + confirmed +
                '}';
    }
}
