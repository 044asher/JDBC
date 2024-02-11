package org.example.ShoppingCart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JdbcCartDAO implements ShoppingCartDAO{
    private static final String URL = "jdbc:mysql://localhost:3306/shop";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final Logger logger = LogManager.getLogger(JdbcCartDAO.class);

    public void addProductToCart(String userName, String product) {
        logger.info("Adding product {} to shopping cart with user name {}", product, userName);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("UPDATE shopping_cart SET products = CONCAT(products, ', ', ?), quantity = quantity + 1 WHERE user_name = ?")) {
            statement.setString(1, product);
            statement.setString(2, userName);
            statement.executeUpdate();
        } catch (SQLException e) {
           logger.error("Error adding product {} to shopping cart with user name {}: {}", product, userName, e.getMessage());
        }
    }

    //    public void removeProductFromCart(String userName, String product) {
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            PreparedStatement statement = connection.prepareStatement("UPDATE shopping_cart SET products = REPLACE(products, ?, ''), quantity = quantity - 1 WHERE user_name = ?")) {
//            statement.setString(1, product);
//            statement.setString(2, userName);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
public void removeProductFromCart(String userName, String product) {
        logger.info("Removing product {} from shopping cart with user name {}", product, userName);
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement("SELECT products FROM shopping_cart WHERE user_name = ?")) {
        statement.setString(1, userName);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            List<String> products = new ArrayList<>(Arrays.asList(resultSet.getString("products").split(", ")));
            products.remove(product);
            try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE shopping_cart SET products = ?, quantity = quantity - 1 WHERE user_name = ?")) {
                updateStatement.setString(1, String.join(", ", products)); //["product1", "product2", "product3"] ---> "product1, product2, product3"
                updateStatement.setString(2, userName);
                updateStatement.executeUpdate();
            }
        }
    } catch (SQLException e) {
       logger.error("Error removing product {} with user name {}: {}", product, userName, e.getMessage());
       }
    }

    public List<String> getAllProductsFromCart(String userName) {
        logger.info("Getting all products from shopping cart with user name {}", userName);
        List<String> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("SELECT products FROM shopping_cart WHERE user_name = ?")) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                products.add(resultSet.getString("products"));
            }
        } catch (SQLException e) {
            logger.error("Error getting all products from shopping cart with user name {}: {}", userName, e.getMessage());
        }
        return products;
    }

    public void removeAllProductsFromCart(String userName) {
        logger.info("Removing all products from shopping cart with user name {} ", userName);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("UPDATE shopping_cart SET products = '', quantity = 0, total_amount = 0 WHERE user_name = ?")) {
            statement.setString(1, userName);
            statement.executeUpdate();
        } catch (SQLException e) {
           logger.error("Error removing all products from shopping cart with user name {}", userName);
        }
    }
}
