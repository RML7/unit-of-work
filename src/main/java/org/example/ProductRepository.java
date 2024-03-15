package org.example;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductRepository {
    public void addProduct(String name, double price) {
        String sql = "INSERT INTO products (name, price) VALUES (?, ?)";
        try (PreparedStatement statement = DBManager.getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
