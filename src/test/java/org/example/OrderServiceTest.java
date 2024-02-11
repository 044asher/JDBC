package org.example;

import org.example.Orders.JdbcOrderDao;
import org.example.Orders.Order;
import org.example.Products.JdbcProductsDAO;
import org.example.Users.JdbcUserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OrderServiceTest {
    private JdbcOrderDao orderDao;
    private JdbcProductsDAO productsDAO;
    private JdbcUserDao userDao;
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderDao = new JdbcOrderDao();
        productsDAO = new JdbcProductsDAO();
        userDao = new JdbcUserDao();
        orderService = new OrderService();
    }

    @Test
    public void testPlaceOrder() {

        String userName = "orderServiceTest";
        double totalAmount = productsDAO.getTotalPriceForUser(userName);


        orderService.placeOrder(userName);


        List<Order> orders = orderDao.getAllOrdersByUser(userDao.getUserId(userName));

        Assertions.assertEquals(1, orders.size());

        Order placedOrder = orders.getFirst();

        Assertions.assertEquals(userDao.getUserId(userName), placedOrder.getUserId());
        Assertions.assertEquals("Сигареты \"Marlboro Gold\", Пиво \"Bud\"", placedOrder.getProductNames());
        Assertions.assertEquals(totalAmount, placedOrder.getTotalAmount());
        Assertions.assertEquals(135.55, totalAmount);
    }
}

