package by.kudman.old;

import java.io.IOException;
import java.util.Scanner;

public class Commands {
    User user;
    boolean repeater = true;

    public void console(String command) {
        switch (command) {
            case "/shop" -> Shopping.printList(Shopping.getShopList());
            case "/user" -> {
                if (LoggingIn.isLoggedIn()) {
                    System.out.println("Logging out...");
                    LoggingIn.loggingOut();
                    user = null;
                }
                if (!LoggingIn.newUser()) {
                    rebound();
                    // could be replaced by an exception
                }
            }
            case "/login" -> {
                if (LoggingIn.isLoggedIn()) {
                    System.out.println("It is logged in already");
                    break;
                }
                if (!LoggingIn.loginPage()) {
                    rebound();// could be replaced by an exception
                } else {
                    this.user = LoggingIn.getLoggedUser();
                }
            }
            case "/logout" -> {
                if (!LoggingIn.isLoggedIn()) {
                    System.out.println("It is logged out already!");
                    rebound();
                    break;
                }
                LoggingIn.loggingOut();
                this.user = null;
                rebound();
            }
            case "/add" -> {
                if (!LoggingIn.isLoggedIn()) {
                    System.out.println("Please log in");
                    rebound();
                    break;
                }
                try {
                    user.addCart(takingNumber());
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Wrong input data!");
                    System.out.println("Returning back...");
                    System.out.println();
                }
            }
            case "/del" -> {
                if (!LoggingIn.isLoggedIn()) {
                    System.out.println("Please log in");
                    rebound();
                    break;
                }
               if (user.getCart().isEmpty()) {
                    System.out.println("Your cart is empty!");
                    break;
                }
                try {
                    user.delCart(takingNumber());
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Wrong input data!");
                    System.out.println("Returning back...");
                    System.out.println();
                    rebound();
                }
            }
            case "/clear" -> {
                if (!LoggingIn.isLoggedIn()) {
                    System.out.println("Please log in");
                    rebound();
                    break;
                }
                if (user.getCart().isEmpty()) {
                    System.out.println("Your cart is empty!");
                    break;
                }
                user.clearCart();
            }
            case "/cart" -> {
                if (!LoggingIn.isLoggedIn()) {
                    System.out.println("Please log in");
                    rebound();
                    break;
                }
                if (user.getCart().isEmpty()) {
                    System.out.println("Tour cart is empty!");
                    break;
                }
                user.printCart();
            }
            case "/buy" -> {
                if (!LoggingIn.isLoggedIn()) {
                    System.out.println("Please log in");
                    rebound();
                    break;
                }
                if (user.getCart().isEmpty()) {
                    System.out.println("Your cart is empty!");
                    break;
                }
                try {
                    user.printCart();
                   // System.out.println("to pay: " + user.fullPrice());
                    BillOut bill = new BillOut(user);
                    bill.printBill();
                    user.clearCart();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.out.println("Purchase successful. Waiting for another command...");
            }
            case "/start" -> {
                rebound();
                user.clearCart();
                user = null;
            }
            case "/end" -> {
                System.out.println("Application will be stop...");
                repeater = false;
            }
            case "/info" -> System.out.println("Info page");
            default -> System.out.println("Wrong command! Waiting for another one!");
        }
    }

    public void rebound() {
        System.out.println("Hello world! This is our shop.");
        System.out.println("For further info type /info");
        System.out.println("Waiting for command...");
    }

    private static int takingNumber() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("Wrong input data!");
            System.out.println("try again!");
            scanner.next();
        }
        return scanner.nextInt();
    }


}
