package ru.yandex.zhmyd.hotel.model;


import java.text.SimpleDateFormat;

/*
*This class present order in view
* have all displayed parameter, and not modified order
*Using one way transform Order->DisplayedOrder
 */
public final class DisplayedOrder {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT=new SimpleDateFormat("dd.MM.yyyy");

    private Order order;
    private String userFullName;
    private String hotelName;

    public DisplayedOrder(Order order){
        this.order=order;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Long getId() {
        return order.getId();
    }

    public Double getPrice() {
        return order.getPrice();
    }

    public Integer getRoomId() {
        return order.getRoomId();
    }

    public void setRoomId(Integer id){
        order.setRoomId(id);
    }

    public Integer getCustomerId() {
        return order.getCustomerId();
    }

    public String getStartDate() {
        return SIMPLE_DATE_FORMAT.format(order.getStartDate());
    }

    public String getEndDate() {
        return SIMPLE_DATE_FORMAT.format(order.getEndDate());
    }

    public Boolean getConfirmed() {
        return order.getConfirmed();
    }

    public Integer getHotelId() {
        return order.getHotelId();
    }

    public RoomCategory getRoomCategory(){
        return  order.getRoomCategory();
    }

    public Byte getPlaces(){
        return order.getPlaces();
    }
}
