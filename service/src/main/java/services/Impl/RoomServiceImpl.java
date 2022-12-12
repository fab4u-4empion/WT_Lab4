package services.Impl;

import dao.impl.RoomDAOImpl;
import entity.Room;
import services.RoomService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoomServiceImpl implements RoomService {

    ExecutorService pool = Executors.newFixedThreadPool(3);

    @Override
    public List<Room> getRooms() {
        try {
            Callable<List<Room>> getRoomsCallable = new RoomDAOImpl("getRooms");
            List<Room> res = pool.submit(getRoomsCallable).get();
            pool.shutdown();
            Collections.sort(res, new Comparator<Room>() {
                @Override
                public int compare(Room lhs, Room rhs) {
                    return Integer.compare(lhs.getNumber(), rhs.getNumber());
                }
            });
            return res;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateRoomStatus(int roomId, boolean status) {
        Callable<List<Room>> updateRoomStatusCallable = new RoomDAOImpl("updateRoomStatus", roomId, status);
        boolean res =pool.submit(updateRoomStatusCallable) != null;
        pool.shutdown();
        return res;
    }

    @Override
    public boolean addNewRoom(int roomNumber) {
        Callable<List<Room>> addNewRoomCallable = new RoomDAOImpl("addNewRoom", 0, roomNumber);
        boolean res = pool.submit(addNewRoomCallable) != null;
        pool.shutdown();
        return res;
    }
}
