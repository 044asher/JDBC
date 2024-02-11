package org.example.Products;

public class Products {
    private int productId;
    private String productName;
    private double price;
    private String description;

    @Override
    public String toString() {
        return "Products{" +
                "product_id = " + productId +
                ", product_name ='" + productName + '\'' +
                ", price ='" + price + '\'' +
                ", description ='" + description +
                '}';
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
