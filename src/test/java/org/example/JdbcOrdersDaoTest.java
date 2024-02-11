package org.example;

import org.example.Orders.JdbcOrderDao;
import org.example.Orders.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JdbcOrdersDaoTest {
    private JdbcOrderDao orderDao;
    private Order testOrder;

    @BeforeEach
    public void setUp() {
        orderDao = new JdbcOrderDao();
        testOrder = new Order();
        testOrder.setOrderId(13);
        testOrder.setUserId(4);
        testOrder.setProductNames("Product1, Product2");
        testOrder.setTotalAmount(100.0);
    }

    @Test
    public void testSaveOrder() {
        orderDao.saveOrder(testOrder);
        Order retrievedOrder = orderDao.getAllOrdersByUser(testOrder.getUserId()).getFirst();

        Assertions.assertEquals(retrievedOrder.getOrderId(), testOrder.getOrderId());
        Assertions.assertEquals(testOrder.getUserId(), retrievedOrder.getUserId());
        Assertions.assertEquals(testOrder.getProductNames(), retrievedOrder.getProductNames());
        Assertions.assertEquals(testOrder.getTotalAmount(), retrievedOrder.getTotalAmount());
    }

    @Test
    public void testGetAllOrdersByUser() {
        List<Order> ordersList = orderDao.getAllOrdersByUser(testOrder.getUserId());
        Assertions.assertFalse(ordersList.isEmpty());

        Order retrievedOrder = ordersList.getFirst();
        Assertions.assertEquals(testOrder.getUserId(), retrievedOrder.getUserId());
    }

    @Test
    public void testGetAllOrders() {

        List<Order> orderList = orderDao.getAllOrders();
        Assertions.assertFalse(orderList.isEmpty());

        Order retrievedOrder = orderDao.getAllOrdersByUser(testOrder.getUserId()).getFirst();
        Assertions.assertNotNull(retrievedOrder);

        Order retrievedOrder1 = orderDao.getAllOrdersByUser(2).getFirst();
        Assertions.assertNotNull(retrievedOrder1);
    }

}
