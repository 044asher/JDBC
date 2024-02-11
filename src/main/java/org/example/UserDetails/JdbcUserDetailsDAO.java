package org.example.UserDetails;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;


public class JdbcUserDetailsDAO implements userDetailsDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/shop";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final Logger logger = LogManager.getLogger(JdbcUserDetailsDAO.class);

    @Override
    public void createUserDetails(UserDetails userDetails) {
        logger.info("Creating userDetails with detailsId {}", userDetails.getDetailsId());
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user_details (details_id, user_id, address, phone_number) VALUES (?, ?, ?, ?)")) {
            statement.setInt(1, userDetails.getDetailsId());
            statement.setInt(2, userDetails.getUserId());
            statement.setString(3, userDetails.getAddress());
            statement.setString(4, userDetails.getPhoneNumber());
            statement.executeUpdate();
            logger.debug("UserDetails with detailsId {} created successfully", userDetails.getDetailsId());
        } catch (SQLException e) {
            logger.error("Error creating userDetails with detailsId {}: {}", userDetails.getDetailsId(), e.getMessage());
        }
    }

    @Override
    public UserDetails getUserDetailsByID(int detailsId) {
        logger.info("Getting userDetails with detailsId {}", detailsId);
        UserDetails userDetails = new UserDetails();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_details WHERE details_id = ?")) {
            statement.setInt(1, detailsId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userDetails.setDetailsId(resultSet.getInt("details_id"));
                userDetails.setUserId(resultSet.getInt("user_id"));
                userDetails.setAddress(resultSet.getString("address"));
                userDetails.setPhoneNumber(resultSet.getString("phone_number"));
            }
            logger.debug("UserDetails with detailsId {} got successfully", detailsId);
        } catch (SQLException e) {
            logger.error("Error getting userDetails with detailsId {}: {}", detailsId, e.getMessage());
        }
        return userDetails;
    }

    @Override
    public List<UserDetails> getAllUserDetails() {
        logger.info("Getting all userDetails");
        List<UserDetails> userDetailsList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_details")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserDetails userDetails = new UserDetails();
                userDetails.setDetailsId(resultSet.getInt("details_id"));
                userDetails.setUserId(resultSet.getInt("user_id"));
                userDetails.setAddress(resultSet.getString("address"));
                userDetails.setPhoneNumber(resultSet.getString("phone_number"));
                userDetailsList.add(userDetails);
            }
            logger.debug("All userDetails have got");
        } catch (SQLException e) {
            logger.error("Error getting all userDetails: {}", e.getMessage());
        }
        return userDetailsList;
    }

    @Override
    public void updateUserDetails(UserDetails userDetails) {
        logger.info("Updating userDetails with detailsId {}", userDetails.getDetailsId());
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("UPDATE user_details SET details_id = ?, user_id = ?, address = ?, phone_number = ? WHERE details_id = ?")) {
            statement.setInt(1, userDetails.getDetailsId());
            statement.setInt(2, userDetails.getUserId());
            statement.setString(3, userDetails.getAddress());
            statement.setString(4, userDetails.getPhoneNumber());
            statement.setInt(5, userDetails.getDetailsId());
            statement.executeUpdate();
            logger.debug("UserDetails with detailsId {} updated successfully", userDetails.getDetailsId());
        } catch (SQLException e) {
            logger.error("Error updating userDetails with detailsId {}: {}", userDetails.getDetailsId(), e.getMessage());
        }
    }

    @Override
    public void deleteUserDetails(UserDetails userDetails) {
        logger.info("Deleting userDetails with detailsId {}", userDetails.getDetailsId());
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM user_details WHERE details_id = ?")) {
             statement.setInt(1, userDetails.getDetailsId());
             statement.executeUpdate();
             logger.debug("UserDetails with detailsId {} deleted successfully", userDetails.getDetailsId());
        }
        catch (SQLException e) {
            logger.error("Error deleting userDetails with detailsId {}: {}", userDetails.getDetailsId(), e.getMessage());
        }
    }
}
