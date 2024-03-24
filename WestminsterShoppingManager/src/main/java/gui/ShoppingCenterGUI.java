package gui;

import console.Clothing;
import console.Electronics;
import console.Product;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static console.WestminsterShoppingManager.productArrayList;


public class ShoppingCenterGUI extends JFrame {

    private CartFrame cartFrame;

    private DefaultTableModel model;
    private JTable table;
    private JComboBox productTypeComboBox;

    private ShoppingCart shoppingCart;
    private List<Product> userProductList;



    public ShoppingCenterGUI() {

        // Set up the main JFrame
        setTitle("Westminster Shopping Centre");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(950, 700));
        setLocationRelativeTo(null);
        setVisible(true);

        // Initialize shopping cart
        shoppingCart = new ShoppingCart();
        userProductList = new ArrayList<>();
        cartFrame = new CartFrame(shoppingCart); // Initialize cartFrame
        cartFrame.setVisible(false);
        components(); // Call the method to create GUI components
    }

    public void components() {  // Method to create GUI components

        JPanel jpanel = new JPanel(); // Create a JPanel to hold various components
        jpanel.setLayout(null);

        // Add a label and combo box for selecting product category
        JLabel label1 = new JLabel("Select Product category");
        label1.setBounds(100, 50, 200, 30);
        jpanel.add(label1);
        productTypeComboBox = new JComboBox(new String[]{"All", "Electronics", "Clothing"});
        productTypeComboBox.setSelectedItem("All");

        productTypeComboBox.setBounds(350, 50, 200, 30);
        jpanel.add(productTypeComboBox);

        // Add a button to view the shopping cart
        JButton shoppingCartbtn = new JButton("Shopping Cart");
        shoppingCartbtn.setBounds(780, 10, 140, 55);
        jpanel.add(shoppingCartbtn);

        // Set up action listener for the shopping cart button
        shoppingCartbtn.addActionListener(e -> {
            // Update the table in cartFrame with the current cartItems
            cartFrame.updateTable(shoppingCart.getCartItems());

            cartFrame.setVisible(true);
        });


        model = new DefaultTableModel(new String[]{"Product ID", "Name", "Category", "Price", "Info"}, 0);
        for (Product product : productArrayList) {
            String category = product.getProductCategory();
            String info;


            if ("Clothing".equals(category) && product instanceof Clothing) {
                info = ((Clothing) product).getClothingInfo();
            } else if ("Electronics".equals(category) && product instanceof Electronics) {
                info = ((Electronics) product).getElectronicInfo();
            } else {
                info = ""; // Default value for other categories
            }
            // Add product data to the table model
            Object[] productArray = {product.getProductID(), product.getProductName(), product.getProductCategory(),
                    product.getPrice(), info};
            model.addRow(productArray);
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setGridColor(Color.BLACK);
        scrollPane.setBounds(20, 100, 910, 300);

        JPanel jPanel2 = new JPanel();
        jPanel2.setBackground(new Color(240, 240, 240));
        jPanel2.setBounds(20, 400, 910, 300);
        jpanel.add(jPanel2);

        // Set up list selection listeners to update panel2 based on selected row
//        ListSelectionModel selectionModel = table.getSelectionModel();
//        selectionModel.addListSelectionListener(e -> {
//            if (!e.getValueIsAdjusting()) {
//                int selectedRow = table.getSelectedRow();
//                if (selectedRow != -1) {
//                    Object[] rowData = new Object[model.getColumnCount()];
//                    for (int i = 0; i < model.getColumnCount(); i++) {
//                        rowData[i] = table.getValueAt(selectedRow, i);
//                    }
//                   // updateDetailsPanel(jPanel2, model, rowData);
//                }
//            }
//        });


        ListSelectionModel selectionModel1 = table.getSelectionModel();
        selectionModel1.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Get the data of the selected item
                    Object[] rowData = new Object[model.getColumnCount()];
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        rowData[i] = table.getValueAt(selectedRow, i);
                    }

                    // Update the details jpanel with the selected item's data
                    updateDetailsPanel(jPanel2, model, rowData);
                }
            }
        });


        productTypeComboBox.addItemListener(e -> {
            String selectedCategory = (String) productTypeComboBox.getSelectedItem();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);

            if (!selectedCategory.equals("All")) {
                sorter.setRowFilter(RowFilter.regexFilter(selectedCategory, 2));


            } else {
                table.setRowSorter(null);


            }

            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Object[] rowData = new Object[model.getColumnCount()];
                for (int i = 0; i < model.getColumnCount(); i++) {
                    rowData[i] = table.getValueAt(selectedRow, i);
                }
                updateDetailsPanel(jPanel2, model, rowData);//calling the method updateDetailsPanel to update the jpanel2 with the deails
            }
            jPanel2.removeAll();// method removes all components (such as labels, buttons, etc.) that are currently added to the jPanel
            jPanel2.repaint();
        });
        jpanel.add(scrollPane);
        add(jpanel);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) { // Create a custom cell renderer to highlight low-stock products
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                String productId = table.getValueAt(row, 0).toString();
                Product selectedProduct = getProductById(productId);

                if (selectedProduct != null) {
                    int quantity = getQuantity(selectedProduct);

                    // Set the background color to red if quantity is less than 3
                    if (quantity < 3) {
                        c.setBackground(Color.red);
                    } else {
                        // Set the default background color for other rows
                        c.setBackground(table.getBackground());
                    }
                }

                return c;
            }
        };

        // Apply the custom renderer to the first column (column index 0)
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);

    }

    private Product getProductById(String productId) { // Method to retrieve a product based on its ID
        for (Product product : productArrayList) {
            if (product.getProductID().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    private int getQuantity(Product selectedProduct) {

        return selectedProduct.getAvailableItemCount();
    }

        private void updateDetailsPanel(JPanel jPanel2, DefaultTableModel model, Object[] rowData) {
            // Clear existing details
            jPanel2.removeAll();
            jPanel2.setLayout(null);

            JLabel headingLabel = new JLabel("Selected Product Details");
            headingLabel.setBounds(20, 5, 200, 20);
            headingLabel.setForeground(Color.BLACK);
            Font font = headingLabel.getFont();
            headingLabel.setFont(new Font(font.getName(), Font.BOLD, 16));
            jPanel2.add(headingLabel);

            int yPos = 40; // Initial Y position for details ,yPos is incremented to ensure that each new label is positioned below the previous one, providing a vertical layout.
            int labelHeight = 20; // Height of each label

            // Retrieve the product ID and category from the selected row
            String productId = rowData[0].toString();
            String category = rowData[2].toString();

            // Find the product in the productList based on the ID
            Product selectedProduct = null;
            for (Product product : productArrayList) {
                if (product.getProductID().equals(productId)) {
                    selectedProduct = product;
                    break;
                }
            }

            // If the product is found, display its details
            if (selectedProduct != null) {
                // Display common product details
                JLabel productIdLabel = new JLabel("Product ID: " + selectedProduct.getProductID());
                productIdLabel.setBounds(10, yPos, 300, labelHeight);
                productIdLabel.setForeground(Color.BLACK);
                jPanel2.add(productIdLabel);
                yPos += labelHeight;

                JLabel productNameLabel = new JLabel("Name: " + selectedProduct.getProductName());
                productNameLabel.setBounds(10, yPos, 300, labelHeight);
                productNameLabel.setForeground(Color.BLACK);
                jPanel2.add(productNameLabel);
                yPos += labelHeight;

                JLabel categoryLabel = new JLabel("Category: " + selectedProduct.getProductCategory());
                categoryLabel.setBounds(10, yPos, 300, labelHeight);
                categoryLabel.setForeground(Color.BLACK);
                jPanel2.add(categoryLabel);
                yPos += labelHeight;

                JLabel priceLabel = new JLabel("Price: " + selectedProduct.getPrice()+'\u00A3');
                priceLabel.setBounds(10, yPos, 300, labelHeight);
                priceLabel.setForeground(Color.BLACK);
                jPanel2.add(priceLabel);
                yPos += labelHeight;

                //String additionalInfo = selectedProduct.getAvailableItems();
                JLabel infoLabel = new JLabel("Available Items: " + selectedProduct.getAvailableItemCount());
                infoLabel.setBounds(10, yPos, 300, labelHeight);
                infoLabel.setForeground(Color.BLACK);
                jPanel2.add(infoLabel);
                yPos += labelHeight;

                // Display additional info based on the product category
                if ("Clothing".equals(category) && selectedProduct instanceof Clothing) {
                    Clothing clothingProduct = (Clothing) selectedProduct;
                    JLabel colorLabel = new JLabel("Color: " + clothingProduct.getColour());
                    colorLabel.setBounds(10, yPos, 300, labelHeight);
                    colorLabel.setForeground(Color.BLACK);
                    jPanel2.add(colorLabel);
                    yPos += labelHeight;

                    JLabel sizeLabel = new JLabel("Size: " + clothingProduct.getSize());
                    sizeLabel.setBounds(10, yPos, 300, labelHeight);
                    sizeLabel.setForeground(Color.BLACK);
                    jPanel2.add(sizeLabel);
                    yPos += labelHeight;
                } else if ("Electronics".equals(category) && selectedProduct instanceof Electronics) {
                    Electronics electronicsProduct = (Electronics) selectedProduct;
                    JLabel warrantyLabel = new JLabel("Warranty: " + electronicsProduct.getWarrantyPeriod());
                    warrantyLabel.setBounds(10, yPos, 300, labelHeight);
                    warrantyLabel.setForeground(Color.BLACK);
                    jPanel2.add(warrantyLabel);
                    yPos += labelHeight;

                    JLabel brandLabel = new JLabel("Brand: " + electronicsProduct.getBrand());
                    brandLabel.setBounds(10, yPos, 300, labelHeight);
                    brandLabel.setForeground(Color.BLACK);
                    jPanel2.add(brandLabel);
                    yPos += labelHeight;
                }

                JButton addToCartButton = new JButton("Add to Shopping Cart");
                addToCartButton.setBounds(300, 210, 190, 47);
                //addToCartButton.addActionListener(e -> addToShoppingCart(rowData));
                addToCartButton.addActionListener(e -> {
                    addToShoppingCart(rowData);

                    // Display a message using JOptionPane
                    JOptionPane.showMessageDialog(this, "Product successfully added to the shopping cart");
                });
                jPanel2.add(addToCartButton);


                // Update the shopping cart table in the cartFrame
                cartFrame.updateTable(shoppingCart.getCartItems());
            } else {
                // If the product is not found, display a message
                JLabel notFoundLabel = new JLabel("Product not found.");
                notFoundLabel.setBounds(10, yPos, 300, labelHeight);
                notFoundLabel.setForeground(Color.BLACK);
                jPanel2.add(notFoundLabel);
            }

            jPanel2.revalidate();
            jPanel2.repaint();
        }

        private void addToShoppingCart(Object[] rowData) {
            String productId = rowData[0].toString();
            String productName = rowData[1].toString();
            String productCategory = rowData[2].toString();
            double price = Double.parseDouble(rowData[3].toString());

            for (Product product : productArrayList) {
                if (product.getProductID().equals(productId) && product.getProductName().equals(productName)
                        && product.getProductCategory().equals(productCategory) && product.getPrice() == price) {
                    shoppingCart.addProductToCart(product);

                    // Update the total cost or perform other actions as needed
                    double totalCost = shoppingCart.calculateTotalCost();
                    System.out.println("Total Cost in Cart: "+ totalCost+ '\u00A3');

                    // Print the contents of the shopping cart
                    System.out.println("Shopping Cart Contents:");
                    for (Product cartProduct : shoppingCart.getCartItems()) {
                        System.out.println(cartProduct);
                    }

                    // Update the cartFrame table
                    cartFrame.updateTable(shoppingCart.getCartItems());

                    break; // Stop the loop once the product is found
                }
            }
        }


    public static void main4() {
        SwingUtilities.invokeLater(() -> new ShoppingCenterGUI());
    }
    }
