package com.example.e_store.querying;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;

@Slf4j
@Service
public class Trending {

    @Value("${spring.datasource.username}")
    String userName;

    @Value("${spring.datasource.password}")
    String password;

    private PreparedStatement top5Customers;
    private PreparedStatement top10Books;
    private PreparedStatement topSales;

    public Trending() {
        restartTable();
    }

    private void restartTable() {
        try {
            // Change The Two Arguments Depending On your SQL
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/e_store", "", "");
            log.info("Username DB: {}", userName);
            log.info("Password DB: {}", password);
            top10Books = connection.prepareStatement(
                    "SELECT b.isbn, b.title, sum(c.quantity) AS total_sold_copies\n" +
                            "FROM book AS b, checkout as c\n" +
                            "WHERE b.book_id = c.book_id\n" +
                            "AND c.date_of_purchase >= DATE_ADD(NOW(),INTERVAL -90 DAY)\n" +
                            "GROUP BY b.book_id\n" +
                            "ORDER BY total_sold_copies DESC\n" +
                            "LIMIT 10;\n");
            top5Customers = connection.prepareStatement(
                    "SELECT u.user_id, u.email, u.phone_number, sum(c.quantity) AS total_purchase\n" +
                            "FROM user as u , checkout as c\n" +
                            "WHERE u.user_id = c.customer_id\n" +
                            "AND c.date_of_purchase >= DATE_ADD(NOW(),INTERVAL -90 DAY)\n" +
                            "GROUP BY user_id\n" +
                            "ORDER BY total_purchase DESC\n" +
                            "LIMIT 5;\n"
            );
            topSales = connection.prepareStatement(
                    "SELECT b.isbn, b.title, b.price, sum(c.quantity) AS total_sales\n" +
                            "FROM book AS b, checkout AS c\n" +
                            "WHERE b.book_id = c.book_id\n" +
                            "AND c.date_of_purchase >= DATE_ADD(NOW(),INTERVAL -30 DAY)\n" +
                            "GROUP BY b.book_id\n" +
                            "ORDER BY total_sales DESC;\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getTop5Customers() throws SQLException {
        return top5Customers.executeQuery();
    }

    public ResultSet getTop10Books() throws SQLException {
        return top10Books.executeQuery();
    }

    public ResultSet getTopSales() throws SQLException {
        return topSales.executeQuery();
    }
}
