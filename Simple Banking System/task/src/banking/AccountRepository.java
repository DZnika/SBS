package banking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    private Connection connection;

    AccountRepository(String file) throws SQLException {

    try {
        connection = DriverManager.getConnection("jdbc:sqlite:" + file);
        String createTableSql = "CREATE TABLE IF NOT EXISTS card" +
                "(\n" +
                "id INTEGER PRIMARY KEY,\n" +
                "number TEXT,\n" +
                "pin TEXT,\n" +
                "balance INTEGER DEFAULT 0\n" +
                ")";
        try (Statement stmt0 = connection.createStatement()) {
                ((Statement) stmt0).execute(createTableSql);
        } catch (SQLException e) {
                System.out.println("Connection to SQLite database established.");
            }
        } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }
    void insert (Account acc) throws SQLException {

        PreparedStatement pstmt = connection.prepareStatement("insert into card values (?, ?, ?, ?)");

        pstmt.setInt(1, acc.getId());
        pstmt.setString(2, acc.getCardNumber());
        pstmt.setString(3, acc.getPin());
        pstmt.setDouble(4, acc.getBalance());

        pstmt.executeUpdate();
    }
}
