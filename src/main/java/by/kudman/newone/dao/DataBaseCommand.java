package by.kudman.newone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseCommand {
    private static DataBaseCommand dataBaseCommand;

    private DataBaseCommand() {
    }

    public static DataBaseCommand getDataBaseCommand() {
        if (dataBaseCommand == null) {
            dataBaseCommand = new DataBaseCommand();
        }
        return dataBaseCommand;
    }

    public ResultSet sqlConsoleExecute(Connection connection, String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        return statement.executeQuery();
    }
    public void sqlConsoleUpdate(Connection connection, String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
    }
}