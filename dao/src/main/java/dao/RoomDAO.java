package dao;

import entity.Room;

import java.util.List;

public interface RoomDAO {
    List<Room> getRooms();
    boolean updateRoomStatus(int roomId, boolean status);
    Room getRoom(int roomId);
    boolean addNewRoom(int roomNumber);
}
