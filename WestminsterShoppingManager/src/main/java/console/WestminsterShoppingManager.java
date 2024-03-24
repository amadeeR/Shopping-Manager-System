package console;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class WestminsterShoppingManager implements ShoppingManager, Serializable {
    Scanner myScanner = new Scanner(System.in);
    Validation validation = new Validation();
    private static final int MAX_PRODUCTS = 50;
    private int noOfProducts;
    public static ArrayList<Product> productArrayList = new ArrayList<Product>();



    @Override
    public void addProduct() {
        if(productArrayList.size()>MAX_PRODUCTS){
            System.out.println("\n--Maximum limit reached! You cannot add more products--\n");
            return;
        }
        System.out.println();
        int productType = validation.numberValidation(" Enter 1 to Add an Electronic products \n Enter " +
                "2 to Add Clothing products\n",1,2);

        String productID = validation.productIDValidator("Enter a product ID starting with A or B (capitalized) followed" +
                " by 3 numbers (e.g., A001, B237). \n Enter the ProductID: ");


        String productName= validation.stringValidator("Enter the product Name: ");

        int availableItemCount= validation.numberValidation("Enter the available item count:",1,100);
        double price=validation.doubleValidator("Enter the price: ");
        switch(productType){
            case 1:
                addElectronics(productID,productName,availableItemCount,price);
               // System.out.println("List of Products:");
                break;
            case 2:
                addClothing(productID,productName,availableItemCount,price);

        }

    }
    public void addElectronics(String productID, String productName, int availableItemCount,double price){
        String brand= validation.stringValidator("Enter the brand: ");
        double warrantyPeriod= validation.doubleValidator("Enter the warranty period: ");
        String productCategory ="Electronics";
        System.out.println("Electronic product " +productName+" added Successfully!");

        Electronics electronics = new Electronics(productID,productName,productCategory,availableItemCount,price,
                brand,warrantyPeriod); //Creating an electronic object
        productArrayList.add(electronics);
      

    }
    public void addClothing(String productID, String productName, int availableItemCount,double price){
       String size ="";
        boolean sizeCheker=true;
        while(sizeCheker){

            System.out.print("""
                    Please enter the size
                     S  - Small
                     M  - Medium
                     L  - Large
                     XL - Extra large
                    """);
            System.out.println("Enter the first letter of the size(Ex: small = s):");
            String sizeSelection= myScanner.nextLine().toUpperCase();

            switch (sizeSelection){
                case "S":
                    size="Small";
                    sizeCheker=false;
                    break;
                case "M":
                    size="Medim";
                    sizeCheker=false;
                    break;
                case "L":
                    size="Large";
                    sizeCheker=false;
                    break;
                case "XL":
                    size="Extra Large";
                    sizeCheker=false;
                    break;
                default:
                    System.out.println("Please enter the correct size");
            }
        }
        String colour = validation.stringValidator("Enter the colour: ");
        String productCategory ="Clothing";
        System.out.println("Clothing product " +productName+" added Successfully!");

        Clothing clothing = new Clothing(productID,productName,productCategory,availableItemCount,price,size,colour);
        productArrayList.add(clothing);

    }

    @Override
    public void deleteProduct() {
        System.out.println("The product names and product IDs of the added products are listed below");
        for (Product product : productArrayList) {
            System.out.println(product.getProductName()+" "+product.productID);
        }
        boolean productFound;

        do {
            System.out.println("Enter the product ID of the product you would like to delete: ");
            String productID = myScanner.next();
            productFound = false;

            for (int i = 0; i < productArrayList.size(); i++) {
                Product product = productArrayList.get(i);

                if (product.getProductID().equalsIgnoreCase(productID)) {
                    productArrayList.remove(i);  // Remove the product at index i
                    productFound = true;

                    if (product instanceof Electronics) {
                        System.out.println("\n Electronics Product that holds Product ID " + productID + " has been deleted " +
                                "Successfully \n");
                    } else if (product instanceof Clothing) {
                        System.out.println("\n Clothing Product that holds Product ID " + productID + " has been deleted " +
                                "Successfully \n");
                    }

                    break;  // Exit the loop once the product is found and removed
                }
            }

            if (!productFound) {
                System.out.println("\nProduct with ID " + productID + " does not exist.\n");
            } else {
                System.out.println("\nRemaining number of Products: " + productArrayList.size());
            }
        } while (!productFound);
    }



    @Override
    public void printProduct() {
        sortProducts();
        System.out.println("----List of Products : ----");
        for (Product product : productArrayList) {
            if (product instanceof Electronics) {
                System.out.println(product);
                System.out.println("\n");
            } else if (product instanceof Clothing) {
                System.out.println(product);
                System.out.println("\n");
            }
        }
    }

    // Method to sort products in the productArrayList based on productID using Bubble Sort algorithm.
    public static void sortProducts() {
        // Set the initial bottom index to the second-to-last element in the list.
        int bottom = productArrayList.size() - 2;

        // Temporary variable to facilitate swapping.
        Product temp;

        // Flag to indicate whether the list is fully sorted.
        boolean notSorted = true;

        // Continue sorting until the list is fully sorted.
        while (notSorted) {
            notSorted = false;

            // Iterate through the list.
            for (int i = 0; i <= bottom; i++) {
                // Compare productIDs of adjacent products.
                if (productArrayList.get(i).getProductID().compareTo(productArrayList.get(i + 1).getProductID()) > 0) {
                    // Swap products if they are out of order.
                    temp = productArrayList.get(i);
                    productArrayList.set(i, productArrayList.get(i + 1));
                    productArrayList.set((i + 1), temp);
                    notSorted = true;
                }
            }

            // Move the bottom index up to the last sorted element.
            bottom--;
        }
    }


    @Override
    public void saveProductsFile(String fileName) {

    try{// Method to save the products from productArrayList to a file using object serialization.
        // Create a FileOutputStream to write data to the specified file.
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        // Create an ObjectOutputStream to serialize and write objects to the file.
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        for (Product product:productArrayList){
            objectOutputStream.writeObject(product);
        }
        objectOutputStream.close();// Close the ObjectOutputStream to release resources
        System.out.println("\nSaved to the file successfully\n");

}
    catch(IOException e){
        System.out.println("\n Saving unsuccessful \n");
    }

    }

    @Override
    public void loadProduct(String filename) {
        WestminsterShoppingManager.productArrayList.clear();
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            while (fileInputStream.available() > 0) {
                productArrayList.add((Product) objectInputStream.readObject());
            }

            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }

    }




}