package gui;

import console.Clothing;
import console.Electronics;
import console.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;


public class CartFrame extends JFrame {

    private JPanel panel;

    private JLabel totalLabel;
    private DefaultTableModel model;

    JLabel discountLabel;
    JLabel discountLabelF;

    JLabel finalTotalLabel;
    private JTable table;
    private ShoppingCart shoppingCart;



    CartFrame(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;  // Initialize the shoppingCart field
        cartFrameSet();
    }

    public void cartFrameSet() {
        setTitle("Shopping cart");
        setSize(new Dimension(780, 600));
        setLocationRelativeTo(null);
        setVisible(true);
        components();
    }

    public void components() {
        panel = new JPanel();
        panel.setLayout(null);
        model = new DefaultTableModel(new String[]{"Product", "Quantity", "Price"}, 0);

        table = new JTable(model);
        TableColumnModel columnModel = table.getColumnModel();
        table.setGridColor(Color.BLACK);

        columnModel.getColumn(0).setPreferredWidth(300);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(100);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 740, 300);
        panel.add(scrollPane);

        totalLabel = new JLabel("Total Price:  0.0");
        totalLabel.setBounds(20, 350, 150, 20);
        panel.add(totalLabel);

        discountLabelF =new JLabel("First Purchase Discount(10%): 0.0");
        discountLabelF.setBounds(20, 380, 300, 20);
        panel.add(discountLabelF);

        discountLabel = new JLabel("Three items in the same category Discount(20%): 0.0");
        discountLabel.setBounds(20, 410, 300, 20);
        panel.add(discountLabel);

        finalTotalLabel = new JLabel("Final Total:  0.0");
        finalTotalLabel.setBounds(20, 440, 150, 20);

        panel.add(finalTotalLabel);

        add(panel);
    }

    public void updateTable(List<Product> cartItems) {
        model.setRowCount(0);
        double totalPrice = 0.0;
        double discount = 0.0;
        double finalTotal;

        for (Product product : cartItems) {
            double price = product.getPrice();
            totalPrice += 1 * price;

            Object[] productArray = {getProductInfo(product),1, product.getPrice()};
            model.addRow(productArray);


        }
        if (cartItems.size() >= 3) {
            discount = 0.2 * totalPrice;
        }


        finalTotal = totalPrice - discount;

        totalLabel.setText("Total Price: " +'\u00A3'+ totalPrice);


        discountLabel.setText("Discount (20%):  " + discount+'\u00A3');
        finalTotalLabel.setText("Final Total:  " + finalTotal+'\u00A3');

    }
    private String getProductInfo(Product product) {
        String category = product.getProductCategory();
        if ("Clothing".equals(category) && product instanceof Clothing) {
            Clothing clothing = (Clothing) product;
            return "ID: " + product.getProductID() + ", Name: " + product.getProductName() + ", Color: "
                    + clothing.getColour() + ", Size: " + clothing.getSize();
        } else if ("Electronics".equals(category) && product instanceof Electronics) {
            Electronics electronics = (Electronics) product;
            return "ID: " + product.getProductID() + ", Name: " + product.getProductName() + ", Warranty: "
                    + electronics.getWarrantyPeriod() + ", Brand: " + electronics.getBrand();
        } else {
            return ""; // Default value for other categories
        }
    }
}
