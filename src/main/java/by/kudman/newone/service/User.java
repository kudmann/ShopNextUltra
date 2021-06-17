package by.kudman.newone.service;

import java.util.ArrayList;

public class User {
    private static User user;
    private String login;
    private String password;
    private String email;
    private ArrayList<Product> cart;

    private User() {
    }

    public static User getUser(){
        if (user==null){
            user = new User();
        }
        return user;
    }

    public void dropUser() {
        user = null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Product> cart) {
        this.cart = cart;
    }
}
