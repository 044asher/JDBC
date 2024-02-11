package org.example.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class JdbcUserDao implements UsersDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/shop";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final Logger logger = LogManager.getLogger(JdbcUserDao.class);

    @Override
    public void createUser(Users user) {
        logger.info("Creating user with id {}", user.getUserId());
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (user_id, user_name, surname, email, age) VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getEmail());
            statement.setInt(5, user.getAge());
            statement.executeUpdate();
        } catch (SQLException e) {
                logger.error("Error creating user: {}", e.getMessage());
            }
        }

    @Override
    public Users getUserById(int userId) {
        logger.info("Getting user with id {}", userId);
        Users user = new Users();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?")) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setAge(resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            logger.error("Error getting user by id {}: {}", userId, e.getMessage());
        }
        return user;
    }

    @Override
    public List<Users> getAllUsers(){
        logger.info("Getting all users");
        List<Users> usersList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
        ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()){
                Users user = new Users();
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setAge(resultSet.getInt("age"));
                usersList.add(user);
            }
        }
        catch (SQLException e){
            logger.error("Error getting all users: {}", e.getMessage());
        }
        return usersList;
    }
    @Override
    public void updateUser(Users user) {
        logger.info("Updating user with id {}", user.getUserId());
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET user_id = ?, user_name = ?, surname = ?, email = ?, age = ? WHERE user_id = ?")) {
            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getEmail());
            statement.setInt(5, user.getAge());
            statement.setInt(6, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating user with id {}: {}", user.getUserId(), e.getMessage());
        }
    }

    @Override
    public void deleteUser(Users user) {
        logger.info("Deleting user with id {}", user.getUserId());
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE user_id = ?")) {
            statement.setInt(1, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting user with id {}: {}", user.getUserId(), e.getMessage());
        }
    }
    public int getUserId(String userName) {
        logger.info("Getting user id with name {}", userName);
        int userId = 0;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT user_id FROM users WHERE user_name = ?")) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("user_id");
            }
        } catch (SQLException e) {
            logger.error("Error getting user id with name {}: {}", userName, e.getMessage());
        }
        return userId;
    }

}