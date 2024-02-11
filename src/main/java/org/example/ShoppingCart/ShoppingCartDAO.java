package org.example.ShoppingCart;

import java.util.List;

public interface ShoppingCartDAO {
    void addProductToCart(String userName, String product);
    void removeProductFromCart(String userName, String product);
    List<String> getAllProductsFromCart(String userName);
    void removeAllProductsFromCart(String userName);
}
