package org.example;

import org.example.ShoppingCart.JdbcCartDAO;
import org.example.ShoppingCart.ShoppingCartDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JdbcCartDaoTest {
    private ShoppingCartDAO cartDao;
    private String testUserName = "testUser";
    private String testProduct1 = "product3";
    private String testProduct2 = "product4";

    @BeforeEach
    public void setUp() {
        cartDao = new JdbcCartDAO();
        cartDao.removeAllProductsFromCart(testUserName);
    }
    @Test
    public void testAddProductToCart() {
        cartDao.addProductToCart(testUserName, testProduct1);

        List<String> productsInCart = cartDao.getAllProductsFromCart(testUserName);

        boolean found = false;
        for (String product : productsInCart) {
            if (product.endsWith(testProduct1)) {
                found = true;
                break;
            }
        }
        Assertions.assertTrue(found);
    }

    @Test
    public void testRemoveProductFromCart() {
        cartDao.addProductToCart(testUserName, testProduct1);
        cartDao.removeProductFromCart(testUserName, testProduct1);

        List<String> productsInCart = cartDao.getAllProductsFromCart(testUserName);
        boolean found = false;
        for (String product : productsInCart) {
            if (product.endsWith(testProduct1)) {
                found = true;
                break;
            }
        }
        Assertions.assertFalse(found);
    }

    @Test
    public void testGetAllProductsFromCart() {
        cartDao.addProductToCart(testUserName, testProduct1);

        List<String> productsInCart = cartDao.getAllProductsFromCart(testUserName);
        boolean found = false;

        for (String product : productsInCart) {
            if (product.endsWith(testProduct1)) {
                found = true;
                break;
            }
        }
        Assertions.assertTrue(found);
    }

    @Test
    public void testRemoveAllProductsFromCart() {
        cartDao.addProductToCart(testUserName, testProduct1);
        cartDao.addProductToCart(testUserName, testProduct2);

        cartDao.removeAllProductsFromCart(testUserName);

        List<String> productsInCart = cartDao.getAllProductsFromCart(testUserName);

        Assertions.assertFalse(productsInCart.contains(testProduct1));
        Assertions.assertFalse(productsInCart.contains(testProduct2));
    }

}
