package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            DBManager.inTransaction(() -> {
                UserRepository userRepository = new UserRepository();
                userRepository.addUser("John");
                ProductRepository productRepository = new ProductRepository();
                productRepository.addProduct("Phone", 23);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}