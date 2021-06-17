package by.kudman.newone.dao;

import by.kudman.newone.service.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements DAO<User, String> {
    private UserDAO() {
    }

    private static UserDAO userDAO;
    private final DataBaseCommand dataBaseCommand = DataBaseCommand.getDataBaseCommand();

    public static UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    @Override
    public User findBy(String userName) throws SQLException {
        User user = null;
        String findByCommand = "SELECT * FROM user_list WHERE login ='"+userName+"'";
        try (Connection connection = ConnectionPull.getConnection()) {
            ResultSet resultSet = dataBaseCommand.sqlConsoleExecute(connection, findByCommand);
            while (resultSet.next()) {
                if (userName.equals(resultSet.getString("login"))) {
                    user = User.getUser();
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                }
            }

        }
        return user; //returns null if cannot find user
    }

    @Override
    public User insert(User user) throws SQLException {
        String insertCommand = "INSERT INTO user_list(login,password) VALUES ('" + user.getLogin() + "','" + user.getPassword() + "')";
        try (Connection connection = ConnectionPull.getConnection()) {
            DataBaseCommand.getDataBaseCommand().sqlConsoleUpdate(connection, insertCommand);
        }
        return user;
    }

    @Override
    public User update(User user) throws SQLException {
        String updateCommand = "UPDATE user_list SET password = '" + user.getPassword() + "', email = '" + user.getEmail() + "' WHERE login = '" + user.getLogin() + "'";
        try (Connection connection = ConnectionPull.getConnection()) {
            DataBaseCommand.getDataBaseCommand().sqlConsoleUpdate(connection, updateCommand);
        }
        return user;
    }


    @Override
    public boolean delete(User user) {
        String deleteCommand = "DELETE FROM user_list WHERE login=" + user.getLogin();
        try (Connection connection = ConnectionPull.getConnection()) {
            DataBaseCommand.getDataBaseCommand().sqlConsoleExecute(connection, deleteCommand);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
}
