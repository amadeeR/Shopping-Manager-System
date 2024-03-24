package console;

import java.io.Serializable;

// Abstract class representing a generic product.
// It implements Serializable to indicate that instances of subclasses can be serialized.
public abstract class Product implements Serializable {
        protected String productID;
        protected String productName;
        protected int availableItemCount;
        protected double price;
        protected String productCategory;
        protected int quantity;

    // Constructor for creating a Product object.
    public Product(String productID, String productName, String productCategory, int availableItemCount, double price) {
            this.productID = productID;
            this.productName = productName;
            this.productCategory = productCategory;
            this.availableItemCount = availableItemCount;
            this.price = price;
        }

    public int getQuantity() {
        return quantity;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }
    public String getProductCategory() {
        return productCategory;
    }

//    public void setProductCategory(String productCategory) {
//        this.productCategory = productCategory;
//    }

    public int getAvailableItemCount() {
        return availableItemCount;
    }

    public double getPrice() {
        return price;
    }
}

