package org.example;


import org.example.Users.JdbcUserDao;
import org.example.Users.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class JdbcUserDaoTest {
    private JdbcUserDao userDao;
    private Users testUser;

    @BeforeEach
    public void setUp(){
        userDao = new JdbcUserDao();
        testUser = new Users();
        testUser.setUserId(8);
        testUser.setUserName("Test");
        testUser.setSurname("User");
        testUser.setAge(10);
        testUser.setEmail("test@example.com");
        userDao.createUser(testUser);
    }
    @AfterEach
    public void clearTests(){
        userDao.deleteUser(testUser);
    }
    @Test
    public void testCreateUser(){
        Users newUser = new Users();
        newUser.setUserId(150);
        newUser.setUserName("Test");
        newUser.setSurname("User");
        newUser.setAge(10);
        newUser.setEmail("test@example.com");
        userDao.createUser(newUser);

        Users retrievedUser = userDao.getUserById(newUser.getUserId());
        Assertions.assertEquals(newUser.getUserName(), retrievedUser.getUserName());
        Assertions.assertEquals(newUser.getSurname(), retrievedUser.getSurname());
        Assertions.assertEquals(newUser.getAge(), retrievedUser.getAge());
        Assertions.assertEquals(newUser.getEmail(), retrievedUser.getEmail());

        userDao.deleteUser(newUser);
    }

    @Test
    public void testGetUserById(){
        Users retrievedUser = userDao.getUserById(testUser.getUserId());
        Assertions.assertEquals(testUser.getUserId(), retrievedUser.getUserId());
    }

    @Test
    public void testGetAllUsers(){
        List<Users> users = userDao.getAllUsers();
        Assertions.assertFalse(users.isEmpty());

        Users retrievedUser = userDao.getUserById(testUser.getUserId());
        Assertions.assertNotNull(retrievedUser);
    }

    @Test
    public void testUpdateUser(){
        testUser.setUserName("Updated");
        testUser.setEmail("updated@test.com");
        userDao.updateUser(testUser);
        Users retrievedUser = userDao.getUserById(testUser.getUserId());
        Assertions.assertEquals("Updated", retrievedUser.getUserName());
        Assertions.assertEquals("updated@test.com", retrievedUser.getEmail());
    }

    @Test
    public void testDeleteUser(){
        Users retrievedUser = userDao.getUserById(testUser.getUserId());
        Assertions.assertEquals(testUser.getUserId(), retrievedUser.getUserId());

        userDao.deleteUser(testUser);
        retrievedUser = userDao.getUserById(testUser.getUserId());
        Assertions.assertEquals(0, retrievedUser.getUserId());
    }

    @Test
    public void testGetUserId(){
        int retrievedUser = userDao.getUserId(testUser.getUserName());
        Assertions.assertEquals(testUser.getUserId(), retrievedUser);
    }
}
