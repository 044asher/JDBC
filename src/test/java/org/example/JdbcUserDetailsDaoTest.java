package org.example;

import org.example.UserDetails.JdbcUserDetailsDAO;
import org.example.UserDetails.UserDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JdbcUserDetailsDaoTest {
    private JdbcUserDetailsDAO userDetailsDAO;
    private UserDetails testUserDetails;
    @BeforeEach
    public void setUp(){
        userDetailsDAO = new JdbcUserDetailsDAO();
        testUserDetails = new UserDetails();
        testUserDetails.setDetailsId(15);
        testUserDetails.setUserId(5);
        testUserDetails.setAddress("Example Street 1");
        testUserDetails.setPhoneNumber("+3806645454");
        userDetailsDAO.createUserDetails(testUserDetails);
    }
    @AfterEach
    public void clearTests(){
        userDetailsDAO.deleteUserDetails(testUserDetails);
    }

    @Test
    public void testCreateUserDetails(){
        UserDetails newUserDetails = new UserDetails();
        newUserDetails.setDetailsId(150);
        newUserDetails.setUserId(5);
        newUserDetails.setAddress("Example Street 1");
        newUserDetails.setPhoneNumber("+3806645454");
        userDetailsDAO.createUserDetails(newUserDetails);

        UserDetails retrievedUserDetails = userDetailsDAO.getUserDetailsByID(newUserDetails.getDetailsId());

        Assertions.assertEquals(newUserDetails.getDetailsId(), retrievedUserDetails.getDetailsId());
        Assertions.assertEquals(newUserDetails.getUserId(), retrievedUserDetails.getUserId());
        Assertions.assertEquals(newUserDetails.getAddress(), retrievedUserDetails.getAddress());
        Assertions.assertEquals(newUserDetails.getPhoneNumber(), retrievedUserDetails.getPhoneNumber());

        userDetailsDAO.deleteUserDetails(newUserDetails);
    }

    @Test
    public void testGetUserDetailsById(){
        UserDetails retrievedUserDetails = userDetailsDAO.getUserDetailsByID(testUserDetails.getDetailsId());
        Assertions.assertEquals(testUserDetails.getDetailsId(), retrievedUserDetails.getDetailsId());
    }

    @Test
    public void testGetAllUserDetails(){
        List<UserDetails> userDetailsList = userDetailsDAO.getAllUserDetails();
        Assertions.assertFalse(userDetailsList.isEmpty());

        UserDetails retrievedUserDetails = userDetailsDAO.getUserDetailsByID(testUserDetails.getDetailsId());
        Assertions.assertNotNull(retrievedUserDetails);
    }

    @Test
    public void testUpdateUserDetails(){
        testUserDetails.setPhoneNumber("(Changed) Deleted");
        testUserDetails.setAddress("(Changed) Deleted");
        userDetailsDAO.updateUserDetails(testUserDetails);
        Assertions.assertEquals("(Changed) Deleted", testUserDetails.getPhoneNumber());
        Assertions.assertEquals("(Changed) Deleted", testUserDetails.getAddress());
    }

    @Test
    public void testDeleteUserDetails(){
        UserDetails retrievedUserDetails = userDetailsDAO.getUserDetailsByID(testUserDetails.getDetailsId());
        Assertions.assertEquals(testUserDetails.getDetailsId(), retrievedUserDetails.getDetailsId());

        userDetailsDAO.deleteUserDetails(testUserDetails);
        retrievedUserDetails = userDetailsDAO.getUserDetailsByID(testUserDetails.getDetailsId());
        Assertions.assertEquals(0, retrievedUserDetails.getDetailsId());
    }
}
