package console;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WestminsterShoppingManagerTest {

    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    void testAddProduct() {
        // Simulate user input for adding an Electronic product
        simulateUserInput("1\nA001\nTest Electronic Product\n5\n350\nSoftlogic\n12\n");

        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        shoppingManager.addProduct();

        // Verify the output
        String expectedOutput = "Enter the product Name:"; // Add more expectations based on your actual output
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void testAddClothingProduct() {
        // Simulate user input for adding a Clothing product
        simulateUserInput("2\nB001\nTest Clothing Product\n3\n50\nL\nRed\n");

        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        shoppingManager.addProduct();

        // Verify the output
        String expectedOutput = "Enter the product Name:"; // Add more expectations based on your actual output
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    // Add more test methods for other scenarios mentioned in your question

    private void simulateUserInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
}
