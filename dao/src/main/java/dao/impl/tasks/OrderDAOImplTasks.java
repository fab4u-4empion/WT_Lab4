package dao.impl.tasks;

import dao.OrderDAO;
import dao.RoomDAO;
import databseConnection.DatabaseConfig;
import entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImplTasks implements OrderDAO{
    private static final String RESERVE_ROOM_QUERY = "INSERT INTO orders (user_id, room_id) VALUES (?, ?)";
    private static final String UNRESERVE_ROOM_QUERY = "DELETE FROM orders WHERE room_id = ?";
    private static final String GET_USER_RESERVES_QUERY = "SELECT * FROM orders WHERE user_id = ?";
    private static final String DELETE_ORDER_QUERY = "DELETE FROM orders WHERE order_id = ?";

    @Override
    public boolean orderRoom(int userId, int roomId) {
        try {
            Connection connection = DatabaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(RESERVE_ROOM_QUERY);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, roomId);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean unorderRoom(int roomId) {
        try {
            Connection connection = DatabaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UNRESERVE_ROOM_QUERY);
            preparedStatement.setInt(1, roomId);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Order> getUserOrders(int userId) {
        try {
            Connection connection = DatabaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_RESERVES_QUERY);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            RoomDAO roomDAO = new RoomDAOImplTasks();
            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3)
                );
                order.setRoomNumber(roomDAO.getRoom(order.getRoomId()).getNumber());
                orders.add(order);
            }
            return orders;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        try {
            Connection connection = DatabaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_QUERY);
            preparedStatement.setInt(1, orderId);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
