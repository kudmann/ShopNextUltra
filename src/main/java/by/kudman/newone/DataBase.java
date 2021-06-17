package by.kudman.newone;

import java.sql.*;

public class DataBase {
    public static Connection connectDB() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
    }

    public static ResultSet sqlScriptQuery(Connection connect, String script)
            throws SQLException {
        PreparedStatement ps = connect.prepareStatement(script);
        return ps.executeQuery();
    }

    public static void sqlScriptUpdate(Connection connect, String script)
            throws SQLException {
        PreparedStatement ps = connect.prepareStatement(script);
        ps.executeUpdate();
    }
}
