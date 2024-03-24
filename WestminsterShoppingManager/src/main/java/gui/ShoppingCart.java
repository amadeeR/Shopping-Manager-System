package gui;
import console.Product;

import java.util.ArrayList;
public class ShoppingCart {
    public static ArrayList<Product> cartItems;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    // Method to add a product to the shopping cart
    public void addProduct(Product product) {
        cartItems.add(product);
    }

    public void addProductToCart(Product product) {
        cartItems.add(product);
    }


    // Method to remove a product from the shopping cart
    public void removeProduct(Product product) {
        cartItems.remove(product);
    }

    // Method to calculate the total cost of all products in the cart
    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (Product product : cartItems) {
            totalCost += product.getPrice();
        }
        return totalCost;
    }

    public ArrayList<Product> getCartItems() {
        return cartItems;
    }
}
