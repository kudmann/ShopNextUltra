package by.kudman.old;

import java.io.*;
import java.util.*;

/*
User contains fields login/password and cart with operating methods
*/
public class User implements Serializable {
    private String login;
    private String password;
    private transient Set<Product> cart;
    private transient int[] things;

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

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public void addCart(int listNumber) throws IndexOutOfBoundsException {
        try {
            things[listNumber - 1]++;
            cart.add(Shopping.getShopList().get(listNumber - 1));
        } catch (NullPointerException e) {
            things = new int[Shopping.getShopList().size()];
            cart = new HashSet<>();
            Arrays.fill(things, 0);
            things[listNumber - 1]++;
            cart.add(Shopping.getShopList().get(listNumber - 1));
        }
        System.out.println(Shopping.getShopList().get(listNumber - 1).getName() + " successfully added.");
    }

    public void delCart(int listNumber) throws IndexOutOfBoundsException {
        if (listNumber < 1 | listNumber > cart.size() - 1) {
            System.out.println("Wrong input data");
            return;
        }
        int tmp = 0;
        for (Product product : cart) {
            if (tmp == listNumber - 1) {
                for (int i = 0; i < Shopping.getShopList().size(); i++) {
                    if (product.equals(Shopping.getShopList().get(i))) {
                        things[i]--;
                        if (things[i] == 0) {
                            System.out.println(product.getName() + " successfully removed.");
                            cart.remove(product);
                            return;
                        }
                        System.out.println(Shopping.getShopList().get(tmp).getName() + " successfully removed.");
                    }
                }
            }
            tmp++;
        }
    }

    public void clearCart() {
        try {
            cart.clear();
        } catch (NullPointerException ignored) {
        } finally {
            Arrays.fill(things, 0);
        }
    }

    public void printCart() {
        Formatter formatter = new Formatter();
        BillOut billTMP = new BillOut(this);
        System.out.println(billTMP.stringCart(formatter));
        formatter.close();
    }

    public Set<Product> getCart() {
        return cart;
    }

    public double fullPrice() {
        double tmp = 0;
        for (int i = 0; i < things.length; i++) {
            if (things[i] > 0) {
                tmp += things[i] * Shopping.getShopList().get(i).getPrice();
            }
        }
        return tmp;
    }

    public int[] getThings() {
        return things;
    }

    public void setCart() {
        cart = new LinkedHashSet<>();
    }

    public void setThings() {
        things = new int[Shopping.getShopList().size()];
        Arrays.fill(things, 0);
    }
}
