package org.example;

import org.example.Orders.JdbcOrderDao;
import org.example.Orders.Order;
import org.example.Products.JdbcProductsDAO;
import org.example.Products.Products;
import org.example.Products.ProductsDAO;
import org.example.ShoppingCart.JdbcCartDAO;
import org.example.UserDetails.JdbcUserDetailsDAO;
import org.example.UserDetails.UserDetails;
import org.example.UserDetails.userDetailsDAO;
import org.example.Users.JdbcUserDao;
import org.example.Users.Users;
import org.example.Users.UsersDAO;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        UsersDAO userDao = new JdbcUserDao();

        Users newUser = new Users();
        newUser.setUserId(100);
        newUser.setUserName("Lera");
        newUser.setSurname("Valerivna");
        newUser.setEmail("valeri@gmail.com");
        newUser.setAge(21);

        // Create
        userDao.createUser(newUser);

        // Read
        List<Users> usersList = userDao.getAllUsers();
        for (Users user : usersList) {
            System.out.println(user);
        }

        System.out.println("New User: " + userDao.getUserById(newUser.getUserId()));

        // Update
        newUser.setUserName("(Changed) Valeria");
        userDao.updateUser(newUser);
        // verify update
        System.out.println("Updated User: " + userDao.getUserById(newUser.getUserId()));

        // Delete
        userDao.deleteUser(newUser);
        // Verify deletion
        System.out.println("is Deleted: " + userDao.getUserById(newUser.getUserId()));



        //--------------------------------------------------------------------------//

        userDetailsDAO userDetailsDAO = new JdbcUserDetailsDAO();

        UserDetails newUserDetails = new UserDetails();

        newUserDetails.setDetailsId(5);
        newUserDetails.setUserId(5);
        newUserDetails.setAddress("Valeri st. 32");
        newUserDetails.setPhoneNumber("+38066594934");

        System.out.println("-------------------------------------------------------------------------------------------------------");

        // Create
        userDetailsDAO.createUserDetails(newUserDetails);

        // Read
        List<UserDetails> userDetailsList = userDetailsDAO.getAllUserDetails();
        for(UserDetails userDetails : userDetailsList){
            System.out.println(userDetails);
        }
        System.out.println("New User Details: " + userDetailsDAO.getUserDetailsByID(newUserDetails.getDetailsId()));

        //Update
        newUserDetails.setAddress("(Changed) Kharkivska st. 101");
        userDetailsDAO.updateUserDetails(newUserDetails);
        //Verify update
        System.out.println("Updated: " + userDetailsDAO.getUserDetailsByID(newUserDetails.getDetailsId()));

        //Delete
        userDetailsDAO.deleteUserDetails(newUserDetails);
        //verfy deletion
        System.out.println("is Deleted: " + userDetailsDAO.getUserDetailsByID(newUserDetails.getDetailsId()));


        //--------------------------------------------------------------------------//
        ProductsDAO productsDAO = new JdbcProductsDAO();
        Products newProduct = new Products();

        newProduct.setProductId(10);
        newProduct.setProductName("Crisps \"Lays\"");
        newProduct.setPrice(38.99);
        newProduct.setDescription("100 g. Crisps with Salt");

        System.out.println("-------------------------------------------------------------------------------------------------------");

        //Create
        productsDAO.createProduct(newProduct);

        //Read
        List<Products> productsList = productsDAO.getAllProducts();
        for(Products products : productsList){
            System.out.println(products);
        }

        System.out.println("New product: " + productsDAO.getProductById(newProduct.getProductId()));

        //Update
        newProduct.setDescription("(Changed) 300 g. Crisps with Salt");
        productsDAO.updateProduct(newProduct);
        //verify update
        System.out.println("Updated product: " + productsDAO.getProductById(newProduct.getProductId()));

        //Delete
        productsDAO.deleteProduct(newProduct);
        //verify deletion
        System.out.println("is Deleted: " + productsDAO.getProductById(newProduct.getProductId()));

        System.out.println("--------------------------------------------------------------------");
        JdbcCartDAO cartDAO = new JdbcCartDAO();
        cartDAO.addProductToCart("Надя", "Crisps");
        cartDAO.removeProductFromCart("Надя", "Crisps");
        System.out.println(cartDAO.getAllProductsFromCart("Надя"));
        //cartDAO.removeAllProductsFromCart("Надя");

        System.out.println("---------------------------------------------------------------------");
        JdbcOrderDao orderDao = new JdbcOrderDao();
        Order newOrder = new Order();
        newOrder.setOrderId(9);
        newOrder.setUserId(2);
        newOrder.setProductNames("Milk \"Dobryana\", Bear \"Bud\"");
        newOrder.setTotalAmount(68.99);

        //Read
        List<Order> orderList = orderDao.getAllOrders();
        for(Order order : orderList){
            System.out.println(order);
        }
        //
        System.out.println("\n" + orderDao.getAllOrdersByUser(3));

        //Save
        //orderDao.saveOrder(newOrder);
        System.out.println(orderDao.getAllOrdersByUser(2));

        OrderService orderService = new OrderService();
        //orderService.placeOrder("Катя");


    }
}
