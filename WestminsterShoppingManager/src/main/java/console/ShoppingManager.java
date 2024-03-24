package console;
// Interface representing a shopping manager
public interface ShoppingManager {
    void addProduct();
    void deleteProduct();
    void printProduct();
    void loadProduct(String filename);
    void saveProductsFile(String fileName);
}

