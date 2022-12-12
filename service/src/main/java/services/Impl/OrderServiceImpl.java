package services.Impl;

import dao.impl.OrderDAOImpl;
import entity.Order;
import services.OrderService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class OrderServiceImpl implements OrderService {

    ExecutorService pool = Executors.newFixedThreadPool(3);

    @Override
    public boolean orderRoom(int userId, int roomId) {
        try {
            Callable<List<Order>> orderRoomCallable = new OrderDAOImpl("orderRoom", userId, roomId);
            boolean res = pool.submit(orderRoomCallable) != null;
            pool.shutdown();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean unorderRoom(int roomId) {
        Callable<List<Order>> unorderRoomCollable = new OrderDAOImpl("unorderRoom", 0, roomId);
        boolean res = pool.submit(unorderRoomCollable) != null;
        pool.shutdown();
        return res;
    }

    @Override
    public List<Order> getUserOrders(int userId) {
        try {
            Callable<List<Order>> getUserOrders = new OrderDAOImpl("getUserOrders", userId, 0);
            List<Order> res = pool.submit(getUserOrders).get();
            pool.shutdown();
            Collections.sort(res, new Comparator<Order>() {
                @Override
                public int compare(Order lhs, Order rhs) {
                    return Integer.compare(lhs.getRoomNumber(), rhs.getRoomNumber());
                }
            });
            return res;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        Callable<List<Order>> deleteOrderCallable = new OrderDAOImpl("deleteOrder", orderId);
        boolean res = pool.submit(deleteOrderCallable) != null;
        pool.shutdown();
        return res;
    }
}
