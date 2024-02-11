package org.example.UserDetails;

import java.util.List;

public interface userDetailsDAO {
    void createUserDetails(UserDetails userDetails);
    UserDetails getUserDetailsByID(int detailsId);
    List<UserDetails> getAllUserDetails();
    void updateUserDetails(UserDetails userDetails);
    void deleteUserDetails(UserDetails userDetails);

}
