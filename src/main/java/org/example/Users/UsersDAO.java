package org.example.Users;

import java.util.List;

public interface UsersDAO {
    void createUser(Users user);
    Users getUserById(int userId);
    List<Users> getAllUsers();
    void updateUser(Users user);
    void deleteUser(Users user);
}
