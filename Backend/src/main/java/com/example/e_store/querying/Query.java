package com.example.e_store.querying;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {

    @Value("${spring.datasource.username}")
    String userName;

    @Value("${spring.datasource.password}")
    String password;

    private void restartTable() {
        try {
            // Change The Two Arguments Depending On your SQL
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/e_store", userName, password);
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
