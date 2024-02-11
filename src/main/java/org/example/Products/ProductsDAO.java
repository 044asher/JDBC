package org.example.Products;

import java.util.List;

public interface ProductsDAO {
    void createProduct(Products product);
    Products getProductById(int productId);
    List<Products> getAllProducts();
    void updateProduct(Products product);
    void deleteProduct(Products product);
}
