package org.example.Products;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JdbcProductsDAO implements ProductsDAO{
    private static final String URL = "jdbc:mysql://localhost:3306/shop";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final Logger logger = LogManager.getLogger(JdbcProductsDAO.class);
    @Override
    public void createProduct(Products product) {
        logger.info("Creating product {}", product);
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO products (product_id, product_name, price, description) VALUES (?, ?, ?, ?)")){
            statement.setInt(1, product.getProductId());
            statement.setString(2, product.getProductName());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getDescription());
            statement.executeUpdate();
        }catch (SQLException e){
            logger.error("Error creating product {}", product);
        }
    }

    @Override
    public Products getProductById(int productId) {
        logger.info("Getting product with id {}", productId);
        Products product = new Products();
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE product_id = ?")) {
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                product.setProductId(resultSet.getInt("product_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDescription(resultSet.getString("description"));
            }
        }catch (SQLException e){
            logger.error("Error getting product with id {}", productId);
        }
        return product;
    }

    @Override
    public List<Products> getAllProducts() {
        logger.info("Getting all products");
        List<Products> productsList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Products product = new Products();
                product.setProductId(resultSet.getInt("product_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDescription(resultSet.getString("description"));
                productsList.add(product);
            }
        }catch (SQLException e){
            logger.error("Error getting all products");
        }
        return productsList;
        }

    @Override
    public void updateProduct(Products product) {
        logger.info("Updating product {}", product);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("UPDATE products SET product_id = ?, product_name = ?, price = ?, description = ? WHERE product_id = ?")) {
            statement.setInt(1, product.getProductId());
            statement.setString(2, product.getProductName());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getDescription());
            statement.setInt(5, product.getProductId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating product {}", product);
        }
    }

    @Override
    public void deleteProduct(Products product) {
        logger.info("Deleting product {}", product);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE product_id = ?")) {
            statement.setInt(1, product.getProductId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Error deleting product {}", product);
        }
    }
    public double getProductPrice(String productName) {
        double price = 0;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT price FROM products WHERE product_name = ?")) {
            statement.setString(1, productName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                price = resultSet.getDouble("price");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return price;
    }

    public double getTotalPriceForUser(String userName) {
        logger.info("Getting total price for user {}", userName);
        double totalPrice = 0;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("SELECT products FROM shopping_cart WHERE user_name = ?")) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String productsString = resultSet.getString("products");
                List<String> productNames = Arrays.asList(productsString.split(", "));
                for (String productName : productNames) {
                    totalPrice += getProductPrice(productName);
                }
            }
        } catch (SQLException e) {
            logger.error("Error getting total price for user {}", userName);
        }
        return totalPrice;
    }

}
