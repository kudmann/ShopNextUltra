package by.kudman.newone.service;

import java.util.ArrayList;

public class CartOperations {
    private final ArrayList<Product> shopAssortment = AssortmentOperations.getAssortmentOperations().getAssortmentList();
    private final User user = User.getUser();

    public void addToCart(Integer id) {
        ArrayList<Product> updatedCart = user.getCart();
        updatedCart.add(shopAssortment.get(id));
        user.setCart(updatedCart);
    }

    public void deleteFromCart(Integer id) {
        ArrayList<Product> updatedCart = user.getCart();
        updatedCart.remove(id);
        user.setCart(updatedCart);
    }

    public void clearCart() {
        user.setCart(new ArrayList<>());
    }
}
