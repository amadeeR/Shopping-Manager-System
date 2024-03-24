package console;

import java.io.Serializable;

// The Clothing class extends the Product class and implements the Serializable interface.
public class Clothing extends Product implements Serializable {

    // Private instance variables for size and colour.
    private String size;
    private String colour;

    // Constructor for creating a Clothing object.
    public Clothing(String productID, String productName, String productCategory, int availableItemCount, double price,
                    String size, String colour) {
        // Call the constructor of the superclass (Product) using the super keyword.
        super(productID, productName, productCategory, availableItemCount, price);

        // Initialize size and colour for the Clothing object.
        this.size = size;
        this.colour = colour;
    }

    // Getter method for retrieving the size.
    public String getSize() {
        return size;
    }

    // Setter method for setting the size.
    public void setSize(String size) {
        this.size = size;
    }

    // Getter method for retrieving the colour.
    public String getColour() {
        return colour;
    }

    // Setter method for setting the colour.
    public void setColour(String colour) {
        this.colour = colour;
    }

    // Method to get a formatted string containing size and colour information.
    public String getClothingInfo() {
        return "Size: " + size + "\nColor: " + colour;
    }

    // Override the toString method to provide a formatted string representation of the Clothing object.
    @Override
    public String toString() {
        return  "The productCategory is = " + productCategory + '\n' +
                "productID= " + productID  +"\n"+
                "productName= " + productName + '\n' +
                "size= " + size + '\n' +
                "colour= " + colour + '\n' +
                "availableItemCount= " + availableItemCount +'\n'+
                "price= " + price +"\n";
    }
}
