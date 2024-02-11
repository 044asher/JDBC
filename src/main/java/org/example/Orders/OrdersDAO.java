package org.example.Orders;

import java.util.List;

public interface OrdersDAO {
    void saveOrder(Order order);
    List<Order> getAllOrdersByUser(int userId);
    List<Order> getAllOrders();
}
