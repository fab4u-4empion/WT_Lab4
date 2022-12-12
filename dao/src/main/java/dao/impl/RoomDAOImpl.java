package dao.impl;

import dao.RoomDAO;
import dao.impl.tasks.RoomDAOImplTasks;
import entity.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class RoomDAOImpl implements Callable<List<Room>> {

    private String command;
    private int roomId;
    private boolean status;
    private int roomNumber;

    public RoomDAOImpl(String command, int roomId, boolean status, int roomNumber) {
        this.command = command;
        this.roomId = roomId;
        this.status = status;
        this.roomNumber = roomNumber;
    }

    public RoomDAOImpl(String command, int roomId, boolean status) {
        this.command = command;
        this.roomId = roomId;
        this.status = status;
    }

    public RoomDAOImpl(String command, int roomId, int roomNumber) {
        this.command = command;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
    }

    public RoomDAOImpl(String command) {
        this.command = command;
    }

    @Override
    public List<Room> call() throws Exception {
        switch (command) {
            case "getRooms": {
                RoomDAO dao = new RoomDAOImplTasks();
                return dao.getRooms();
            }
            case "updateRoomStatus": {
                RoomDAO dao = new RoomDAOImplTasks();
                dao.updateRoomStatus(roomId, status);
                return null;

            }
            case "getRoom": {
                RoomDAO dao = new RoomDAOImplTasks();
                ArrayList<Room> rooms = new ArrayList<Room>();
                rooms.add(dao.getRoom(roomId));
                return rooms;

            }
            case "addNewRoom": {
                RoomDAO dao = new RoomDAOImplTasks();
                dao.addNewRoom(roomNumber);
                return null;

            }
        }
        return null;
    }
}
