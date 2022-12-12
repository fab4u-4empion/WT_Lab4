package entity;

public class Room {
    private int id;
    private int number;
    private boolean isReserved;

    public Room(int id, int number, boolean isReserved) {
        this.id = id;
        this.number = number;
        this.isReserved = isReserved;
    }

    public Room(int number, boolean isReserved) {
        this.number = number;
        this.isReserved = isReserved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", isReserved=" + isReserved +
                '}';
    }
}
