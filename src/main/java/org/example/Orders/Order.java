package org.example.Orders;

public class Order {
    private int orderId;
    private int userId;
    private String productNames;
    private double totalAmount;

    @Override
    public String toString(){
        return "Orders {" +
                "order_id: " + orderId +
                ", user_id: " + userId +
                ", product_names: " + productNames +
                ", total_amount: " + totalAmount + '}';
    }
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProductNames() {
        return productNames;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
