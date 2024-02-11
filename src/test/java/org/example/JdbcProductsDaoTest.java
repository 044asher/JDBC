package org.example;

import org.example.Products.JdbcProductsDAO;
import org.example.Products.Products;
import org.junit.jupiter.api.*;

import java.util.List;

public class JdbcProductsDaoTest {
    private JdbcProductsDAO productsDAO;
    private Products testProduct;

    @BeforeEach
    public void setUp() {
        productsDAO = new JdbcProductsDAO();
        testProduct = new Products();
        testProduct.setProductId(15);
        testProduct.setProductName("TestProduct");
        testProduct.setPrice(10.0);
        testProduct.setDescription("TestDescription");
        productsDAO.createProduct(testProduct);
    }
    @AfterEach
    public void clearTests(){
        productsDAO.deleteProduct(testProduct);
    }

    @Test
    public void testCreateProduct() {
        Products newProduct = new Products();
        newProduct.setProductId(16);
        newProduct.setProductName("NewProduct");
        newProduct.setPrice(20.0);
        newProduct.setDescription("NewDescription");
        productsDAO.createProduct(newProduct);

        Products retrievedProduct = productsDAO.getProductById(newProduct.getProductId());

        Assertions.assertEquals(newProduct.getProductId(), retrievedProduct.getProductId());
        Assertions.assertEquals(newProduct.getProductName(), retrievedProduct.getProductName());
        Assertions.assertEquals(newProduct.getPrice(), retrievedProduct.getPrice());
        Assertions.assertEquals(newProduct.getDescription(), retrievedProduct.getDescription());

        productsDAO.deleteProduct(newProduct);
    }

    @Test
    public void testGetProductById() {
        Products retrievedProduct = productsDAO.getProductById(testProduct.getProductId());
        Assertions.assertEquals(testProduct.getProductId(), retrievedProduct.getProductId());
    }

    @Test
    public void testGetAllProducts() {
        List<Products> productsList = productsDAO.getAllProducts();
        Assertions.assertFalse(productsList.isEmpty());

        Products retrievedProduct = productsDAO.getProductById(testProduct.getProductId());
        Assertions.assertNotNull(retrievedProduct);
    }

    @Test
    public void testUpdateProduct() {
        testProduct.setPrice(15.0);
        testProduct.setDescription("UpdatedDescription");
        productsDAO.updateProduct(testProduct);
        Assertions.assertEquals(15.0, testProduct.getPrice());
        Assertions.assertEquals("UpdatedDescription", testProduct.getDescription());
    }

    @Test
    public void testDeleteProduct() {
        Products retrievedProduct = productsDAO.getProductById(testProduct.getProductId());
        Assertions.assertEquals(testProduct.getProductId(), retrievedProduct.getProductId());

        productsDAO.deleteProduct(testProduct);
        retrievedProduct = productsDAO.getProductById(testProduct.getProductId());
        Assertions.assertEquals(0, retrievedProduct.getProductId());
    }

    @Test
    public void testGetProductPrice() {
        double retrievedPrice = productsDAO.getProductPrice(testProduct.getProductName());
        Assertions.assertEquals(testProduct.getPrice(), retrievedPrice);
    }

}
