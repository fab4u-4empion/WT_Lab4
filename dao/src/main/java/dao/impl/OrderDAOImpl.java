package dao.impl;

import dao.OrderDAO;
import dao.impl.tasks.OrderDAOImplTasks;
import entity.Order;

import java.util.List;
import java.util.concurrent.Callable;

public class OrderDAOImpl implements Callable<List<Order>> {

    private String command;
    private int userId;
    private int roomId;
    private int orderId;

    public OrderDAOImpl(String command, int userId, int roomId, int orderId) {
        this.command = command;
        this.userId = userId;
        this.roomId = roomId;
        this.orderId = orderId;
    }

    public OrderDAOImpl(String command, int userId, int roomId) {
        this.command = command;
        this.userId = userId;
        this.roomId = roomId;
    }

    public OrderDAOImpl(String command, int orderId) {
        this.command = command;
        this.orderId = orderId;
    }

    @Override
    public List<Order> call() {
        switch (command) {
            case "orderRoom": {
                OrderDAO dao = new OrderDAOImplTasks();
                dao.orderRoom(userId, roomId);
                break;
            }
            case "unorderRoom": {
                OrderDAO dao = new OrderDAOImplTasks();
                dao.unorderRoom(roomId);
                break;
            }
            case "getUserOrders": {
                OrderDAO dao = new OrderDAOImplTasks();
                return dao.getUserOrders(userId);
            }
            case "deleteOrder": {
                OrderDAO dao = new OrderDAOImplTasks();
                dao.deleteOrder(orderId);
                break;
            }
        }
        return null;
    }
}
