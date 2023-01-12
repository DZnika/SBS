package banking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    private static Connection connection;

    AccountRepository(String fileName) throws SQLException {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);

            String createTableSql = "CREATE TABLE IF NOT EXISTS card (\n"
                    + "    id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                    + "    number TEXT NOT NULL, \n"
                    + "    pin TEXT NOT NULL, \n"
                    + "    balance INTEGER DEFAULT 0\n"
                    + ");";

            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert(Account acc) throws SQLException {

        PreparedStatement pstmt = connection.prepareStatement("insert into card (number, pin, balance) values (?, ?, ?)");

        pstmt.setString(1, acc.getCardNumber());
        pstmt.setString(2, acc.getPin());
        pstmt.setDouble(3, acc.getBalance());

        pstmt.executeUpdate();
    }
    public void update(Account acc) {

    }
}
