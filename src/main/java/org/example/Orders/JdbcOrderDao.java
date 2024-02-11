package org.example.Orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcOrderDao implements OrdersDAO{
    private static final String URL = "jdbc:mysql://localhost:3306/shop";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final Logger logger = LogManager.getLogger(JdbcOrderDao.class);
    @Override
    public void saveOrder(Order order) {
        logger.info("Saving order with order id {}", order.getOrderId());
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO orders(order_id, user_id, product_names, total_amount) VALUES (?, ?, ?, ?)")){
            statement.setInt(1, order.getOrderId());
            statement.setInt(2, order.getUserId());
            statement.setString(3, order.getProductNames());
            statement.setDouble(4, order.getTotalAmount());
            statement.executeUpdate();
        }catch (SQLException e){
            logger.error("Error saving order with id {}: {}", order.getOrderId(), e.getMessage());
        }
    }

    @Override
    public List<Order> getAllOrdersByUser(int userId) {
        logger.info("Getting all orders from user with user id {}", userId);
        List<Order> ordersList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE user_id = ?")){
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                order.setOrderId(resultSet.getInt("order_id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setProductNames(resultSet.getString("product_names"));
                order.setTotalAmount(resultSet.getDouble("total_amount"));
                ordersList.add(order);
            }
        }catch (SQLException e) {
            logger.error("Error getting all orders from user with user id {}: {}", userId, e.getMessage());
        }
        return ordersList;
    }

    @Override
    public List<Order> getAllOrders() {
        logger.info("Getting all orders");
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("order_id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setProductNames(resultSet.getString("product_names"));
                order.setTotalAmount(resultSet.getFloat("total_amount"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            logger.error("Error getting all orders: {}", e.getMessage());
        }
        return orderList;
    }

}

