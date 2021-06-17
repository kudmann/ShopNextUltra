package by.kudman.newone.service;

public class UserInputData {
    private static UserInputData userInputData;
    private final User user = User.getUser();

    public void inputLogin(String login) {
        user.setLogin(login);
    }

    private UserInputData() {
    }

    public static UserInputData getUserInputData() {
        if (userInputData == null) {
            userInputData = new UserInputData();
        }
        return userInputData;
    }

    public void inputPassword(String password) {
        user.setPassword(password);
    }

    public void inputEmail(String email) {
        user.setEmail(email);
    }
}
