package entity;

public class Order {

    private int orderId;
    private int userId;
    private int roomId;
    private int roomNumber;

    public Order(int orderId, int userId, int roomId) {
        this.orderId = orderId;
        this.userId = userId;
        this.roomId = roomId;
    }

    public Order(int userId, int roomId) {
        this.userId = userId;
        this.roomId = roomId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
