package console;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainShoppingManagerTest {

    @Test
    void testMenu() {
        // Save the original System.in and System.out
        InputStream originalSystemIn = System.in;
        PrintStream originalSystemOut = System.out;

        // Prepare the input for the menu
        String simulatedUserInput = "1\nTest Product\n10.0\n2\n1\n3\n4\n5\n6\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the method to be tested
        MainShoppingManager.Menu();

        // Restore the original System.in and System.out
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);

        // Verify the output
        String expectedOutput = """
                -------MENU--------
                                
                Enter 1 to Add a new product
                Enter 2 to Delete a product
                Enter 3 to Print the list of products
                Enter 4 to Save products to a file
                Enter 5 to start the GUI
                Enter 6 to Exit the programme
                                
               --------------------
                Adding a new product:
                Enter product name: Enter product price: 
                Product added successfully!
                Deleting a product:
                Enter the product ID to delete: Product deleted successfully!
                ----Products are printed in ascending order of the product ID----
                Product ID: 1, Name: Test Product, Price: $10.0
                Saving products to a file...
                Products saved successfully to src/main/java/console/products.txt
                The Graphical User Interface starts Now 
                Programme exit successful
                """;

        assertEquals(expectedOutput, outputStream.toString().trim());
    }
}
