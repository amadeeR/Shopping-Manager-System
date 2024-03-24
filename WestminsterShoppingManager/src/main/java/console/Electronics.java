package console;

import java.io.Serializable;

// The Electronics class extends the Product class and implements the Serializable interface.
// This interface is used to indicate that instances of this class can be serialized,
// meaning they can be converted into a byte stream for storage or transmission.
public class Electronics extends Product implements Serializable {
    private String brand;
    private double warrantyPeriod;
    public Electronics(String productID, String productName,String productCategory, int availableItemCount, double price,String brand,
                       double warrantyPeriod) {
        super(productID, productName,productCategory, availableItemCount, price);
        this.brand=brand;
        this.warrantyPeriod=warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(double warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getElectronicInfo() {
        return "Brand : " + brand + "\nWarranty: " + warrantyPeriod;
    }



    @Override
    public String toString() {
        return "productCategory= " + productCategory + '\n' +
                "productID= " + productID + '\n' +
                "productName= " + productName + '\n' +
                "brand= " + brand + '\n' +
                "warrantyPeriod= " + warrantyPeriod +"\n"+
                "availableItemCount= " + availableItemCount +"\n"+
                "price= " + price +'\n'

                ;
    }
}
