package org.example.UserDetails;

public class UserDetails {
    private int detailsId;
    private int userId;
    private String address;
    private String phoneNumber;
    @Override
    public String toString() {
        return "userDetails{" +
                "details_id = " + detailsId +
                ", user_id ='" + userId + '\'' +
                ", address ='" + address + '\'' +
                ", phone_number ='" + phoneNumber +
                '}';
    }

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
