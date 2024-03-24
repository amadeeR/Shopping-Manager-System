package console;

import java.io.File;

import static gui.LoginGUI.main1;

// The main class for managing the shopping system.
public class MainShoppingManager {

    // Static instances of WestminsterShoppingManager and Validation classes.
    static WestminsterShoppingManager myWestminsterShoppingManager = new WestminsterShoppingManager();
    static Validation validation = new Validation();

    // Main method where the program starts.
    public static void main(String[] args) {
        // Call the Menu method to display the main menu and handle user choices.
        Menu();
    }

    // Method to display the main menu and handle user choices.
    public static void Menu() {
        boolean showMenu = true;

        // File object to check if data exists from a previous run.
        File temp = new File("src/main/java/console/products.txt");

        // Check if data exists from a previous run and load it if found.
        if (temp.exists()) {
            System.out.println("\nATTENTION! Saved progress from a previous run has been found! The progress has been " +
                    "reloaded automatically.");
            myWestminsterShoppingManager.loadProduct("src/main/java/console/products.txt");
        }

        // Main menu loop.
        while (showMenu) {
            // Display the main menu options.
            String menu = """
                    -------MENU--------
                    
                    Enter 1 to Add a new product
                    Enter 2 to Delete a product
                    Enter 3 to Print the list of products
                    Enter 4 to Save products to a file
                    Enter 5 to start the GUI
                    Enter 6 to Exit the programme
                   
                   --------------------
                    """;
            System.out.println(menu);

            // Get user choice and validate it using the numberValidation method.
            int choice = validation.numberValidation("Select an option between (1-6): ", 1, 6);

            // Switch statement to perform actions based on user choice.
            switch (choice) {
                case 1:
                    // Call the addProduct method of WestminsterShoppingManager to add a new product.
                    myWestminsterShoppingManager.addProduct();
                    break;

                case 2:
                    // Call the deleteProduct method of WestminsterShoppingManager to delete a product.
                    myWestminsterShoppingManager.deleteProduct();
                    break;

                case 3:
                    // Print the list of products in ascending order of product ID.
                    System.out.println("----Products are printed in ascending order of the product ID----");
                    myWestminsterShoppingManager.printProduct();
                    break;

                case 4:
                    // Call the saveProductsFile method of WestminsterShoppingManager to save products to a file.
                    myWestminsterShoppingManager.saveProductsFile("src/main/java/console/products.txt");
                    break;

                case 5:
                    // Start the graphical user interface by calling the main1 method from LoginGUI.
                    System.out.println("The Graphical User Interface starts Now ");
                    main1();
                    break;

                case 6:
                    // Exit the program by setting showMenu to false.
                    showMenu = false;
                    System.out.println("Programme exit successful");
            }
        }
    }
}

