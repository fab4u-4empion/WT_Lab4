package services;

import entity.Room;

import java.util.List;

public interface RoomService {
    List<Room> getRooms();
    boolean updateRoomStatus(int roomId, boolean status);
    boolean addNewRoom(int roomNumber);
}
