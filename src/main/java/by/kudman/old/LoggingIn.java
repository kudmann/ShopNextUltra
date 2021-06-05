package by.kudman.old;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

/*
this class contains operating logging in methods and list of users with methods
that allowed to create new users
 */
public class LoggingIn {
    private static User loggedUser;
    private static boolean loggedIn;
    private static List<User> userList;


    public static boolean newUser() {
        System.out.println("This is sign up page, please enter your new user's login");
        System.out.println("or enter /0 to return back");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.next();
        if (login.equals("/0")) { //could be an exception
            System.out.println("Canceled. Returning back...");
            return false;
        }
        if (loginMatch(login)) { //could be an exception
            System.out.println("There is such login! Try again!");
            return false;
        }
        User user = new User();
        user.setLogin(login);
        System.out.println("Enter your new login's password (all symbols allowed)");
        System.out.println("or enter /0 to return back");
        String password = scanner.next();
        if (password.equals("/0")) { //could be an exception
            System.out.println("Canceled. Returning back...");
            return false;
        }
        user.setPassword(password);
        userList.add(user);
        user.setCart();
        user.setThings();
        System.out.println("New user set successfully");
        return true;
    }

    /*
    method returns true if user creation successful
    and false in not (the user canceled operation or entered login match with saved in userList)
     */
    public static void defaultUserList() {
        File file = new File("users.data");
        try (FileOutputStream fileOStream = new FileOutputStream(file, true);
             ObjectOutputStream objOStream = new ObjectOutputStream(fileOStream)) {
            objOStream.writeInt(5);
            for (int i = 0; i < 5; i++) {
                String defaultUserName = "user" + i;
                String defaultPassword = "111" + i;
                objOStream.writeObject(new User(defaultUserName,defaultPassword));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
    method initiate userList and fills it with 5 default users:
    user0-1110, user1-1111, user2-1112, user3-1113, user4-1114
    is should calls when app starts
     */
    public static boolean loginMatch(String login) {
        for (User user : userList) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    /*
    method checks is load login matches containing logins in userList
    true - matches, false - not
     */
    public static void loggingIn(String login, String password) {
        for (User user : userList) {
            if (password.equals(user.getPassword()) & login.equals(user.getLogin())) {
                System.out.println("Log in successful");
                loggedIn = true;
                loggedUser = user;
                return;
            }
        }
        loggedIn = false;
        System.out.println("Wrong login or password!");
    }

    /*
    method checks is load login and password matches containing logins and passwords
    it also changes field boolean loggedIn - true if matches, false if not
    */
    public static boolean loginPage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("This is log in page. Please enter existing login");
        System.out.println("or enter /0 to return back");
        String login = scanner.next();
        if (login.equals("/0")) { // could be an exception
            System.out.println("Canceled. Returning back...");
            return false;
        }
        if (!loginMatch(login)) { //could be an exception
            System.out.println("Login doesn't match! Try again!");
            return false;
        }
        System.out.println("Enter your password");
        System.out.println("or enter /0 to return back");
        String password = scanner.next();
        if (password.equals("/0")) { //could be an exception
            System.out.println("Canceled. Returning back...");
            return false;
        }
        loggingIn(login, password);
        return true;
    }

    /*
    method gets login and password from System.in scanner and makes all
    necessary operations: checks logins and passwords with exceptions (not real
    exceptions yet)
     */
    public static void loggingOut() {
        setLoggedIn(false);
        System.out.println("Logging out successful");
        loggedUser = null;
    }

    /*
    method sets field loggedIn as false and normalize Shopping.shopList
    It is strongly recommends to clear user's cart
     */
    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(boolean loggedIn) {
        LoggingIn.loggedIn = loggedIn;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }
    public static List<User> getUserList(){
        return userList;
    }
    public static void setUserList(List<User> userList){
        LoggingIn.userList = userList;
    }
}
