package by.kudman.newone.service;

import by.kudman.newone.dao.UserDAO;

import java.sql.SQLException;

public class UserAuthorization {
    private static UserAuthorization userAuthorization;
    private final UserDAO userDAO = UserDAO.getUserDAO();
    private User user = User.getUser();

    public static UserAuthorization getUserAuthorization() {
        if (userAuthorization == null) {
            userAuthorization = new UserAuthorization();
        }
        return userAuthorization;
    }

    public boolean logIn(String login, String password) throws SQLException {
        return userDAO.findBy(login).getPassword().equals(password);
    }
    public void logOut(){
        user.dropUser();
    }
}
