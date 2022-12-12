package dao.impl.tasks;

import dao.RoomDAO;
import databseConnection.DatabaseConfig;
import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImplTasks implements RoomDAO {

    private static final String GET_ALL_ROOMS_QUERY = "SELECT * FROM rooms";
    private static final String UPDATE_ROOM_STATUS_QUERY = "UPDATE rooms SET room_reserv = ? WHERE room_id = ?";
    private static final String GET_ROOM_QUERY = "SELECT * FROM rooms WHERE room_id = ?";
    private static final String ADD_NEW_ROOM = "INSERT INTO rooms (room_number, room_reserv) VALUES (?, false)";

    @Override
    public List<Room> getRooms() {
        try {
            Connection connection = DatabaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ROOMS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Room> rooms = new ArrayList();
            while(resultSet.next()){
                Room room = new Room(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getBoolean(3)
                );
                rooms.add(room);
            }
            return rooms;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateRoomStatus(int roomId, boolean status) {
        try {
            Connection connection = DatabaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROOM_STATUS_QUERY);
            preparedStatement.setBoolean(1, status);
            preparedStatement.setInt(2, roomId);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Room getRoom(int roomId) {
        try {
            Connection connection = DatabaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ROOM_QUERY);
            preparedStatement.setInt(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Room room = new Room(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getBoolean(3)
            );
            return room;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addNewRoom(int roomNumber) {
        try {
            Connection connection = DatabaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_ROOM);
            preparedStatement.setInt(1, roomNumber);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
