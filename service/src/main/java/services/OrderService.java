package services;

import entity.Order;

import java.util.List;

public interface OrderService {
    boolean orderRoom(int userId, int roomId);
    boolean unorderRoom(int roomId);
    List<Order> getUserOrders(int userId);
    boolean deleteOrder(int orderId);
}
