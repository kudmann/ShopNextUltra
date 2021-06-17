package by.kudman.newone.service;

import by.kudman.newone.dao.UserDAO;

import java.sql.SQLException;

public class UserOperations { //with UserDAO class
    private static UserOperations userOperations;
    private final UserDAO userDAO = UserDAO.getUserDAO();

    public static UserOperations getUserOperations() {
        if (userOperations == null) {
            userOperations = new UserOperations();
        }
        return userOperations;
    }

    public boolean create(String login, String password) throws SQLException {
       if (userDAO.findBy(login) == null) {
           User user = User.getUser();
           user.setLogin(login);
           user.setPassword(password);
           userDAO.insert(user);
        return true;
       }
       return false; //make as exception in future
    }

    public void update() throws SQLException {
        userDAO.update(User.getUser());
    }

    public void delete() {
        userDAO.delete(User.getUser());
    }
}
