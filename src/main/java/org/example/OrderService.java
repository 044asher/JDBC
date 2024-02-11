package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Orders.JdbcOrderDao;
import org.example.Orders.Order;
import org.example.Orders.OrdersDAO;
import org.example.Products.JdbcProductsDAO;
import org.example.ShoppingCart.JdbcCartDAO;
import org.example.ShoppingCart.ShoppingCartDAO;
import org.example.Users.JdbcUserDao;
import org.example.Users.UsersDAO;

import java.util.List;

public class OrderService {
        private JdbcOrderDao orderDao = new JdbcOrderDao();
        private JdbcCartDAO cartDAO = new JdbcCartDAO();
        private JdbcProductsDAO productsDAO = new JdbcProductsDAO();
        private JdbcUserDao userDao = new JdbcUserDao();
        private static final Logger logger = LogManager.getLogger(OrderService.class);


        public void placeOrder(String userName) {
            logger.info("Placing order for user {}", userName);

            List<String> products = cartDAO.getAllProductsFromCart(userName);
            logger.debug("Received products from cart: {}", products);

            double totalAmount = productsDAO.getTotalPriceForUser(userName);
            logger.debug("Total price for user's products: {}", totalAmount);

            Order order = new Order();

            order.setOrderId(20);
            order.setUserId(userDao.getUserId(userName));
            order.setProductNames(String.join(", ", products));
            order.setTotalAmount(totalAmount);

            orderDao.saveOrder(order);
            logger.info("Order saved successfully");

            cartDAO.removeAllProductsFromCart(userName);
            logger.info("All products removed from shopping cart");


    }
}