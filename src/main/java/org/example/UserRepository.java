package org.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository {
    public void addUser(String name) {
        String sql = "INSERT INTO users (name) VALUES (?)";
        try (PreparedStatement statement = DBManager.getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
