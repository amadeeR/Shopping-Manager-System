package gui;

import console.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SignUpGUI extends JFrame {
    static Font myFont1 = new Font("SansSerif",Font.BOLD,18);
    private JTextField txtFldUsername;
    private JPasswordField jPasswordField;
    private JButton jbtnSignUp;
    //public static JComboBox<String> productCategoryComboBox;
public static ArrayList<User> userArrayList = new ArrayList<User>();
    public SignUpGUI() {
//        setTitle("Login");
        super("SignUp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(400,250));
        setLocationRelativeTo(null);
        setVisible(true);
        components();

//        setLayout(new FlowLayout());
//        add (new JLabel("Select Product Category "));
//        add(new JComboBox<>(new String[]{"All", "Electronics", "Clothing"}));
//        add(new JButton("Shopping Cart"));



    }
    public void components(){

        JPanel jPanel =new JPanel();
        // jPanel.setBackground(Color.WHITE);
        jPanel.setLayout(null);
        jPanel.setBounds(50, 50, 300, 200);

        JLabel labelSignUp = new JLabel("SignUP");
        labelSignUp.setFont(myFont1);
        jPanel.add(labelSignUp);
        labelSignUp.setBounds(160,10,100,30);
        add(jPanel);

        JLabel labelUsername = new JLabel("Username : ");
        txtFldUsername = new JTextField(10);
        labelUsername.setBounds(30, 50, 80, 30);
        txtFldUsername.setBounds(120, 50, 150, 30);
        jPanel.add(labelUsername);
        jPanel.add(txtFldUsername);

        JLabel labelPassword = new JLabel("Password : ");
        labelPassword.setBounds(30, 90, 80, 30);
        jPasswordField = new JPasswordField(10);
        jPasswordField.setBounds(120, 90, 150, 30);
        jPanel.add(labelPassword);
        jPanel.add(jPasswordField);

        jbtnSignUp = new JButton("SignUp");
        jbtnSignUp.setBounds(120, 130, 80, 30);
        jPanel.add(jbtnSignUp);

        JButton jbtnReset = new JButton("Reset");
        jbtnReset.setBounds(220, 130, 80, 30);
        jPanel.add(jbtnReset);

        JLabel signUpLabel = new JLabel("Already have an account? ");
        // signUpLabel.setForeground(Color.BLUE);  // Change text color to blue for hyperlink-like appearance
        signUpLabel.setBounds(30, 170, 200, 30);
        jPanel.add(signUpLabel);

        JButton jbtnLogin = new JButton("<html><a href =\'#\'>Login</a></html>");
        jbtnLogin.setBounds(190, 170, 80, 30);
        jPanel.add(jbtnLogin);

        jbtnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignupButtonClick();
            }
        });

        jbtnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open SignUpGUI when the button is clicked
                new LoginGUI();
                // Close the LoginGUI if needed
                dispose();
            }
        });





    }
    public void openLogin(){
            jbtnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open SignUpGUI when the button is clicked
                new LoginGUI();
                // Close the LoginGUI if needed
                dispose();
            }
        });}
    private void handleSignupButtonClick() {
        String username = txtFldUsername.getText();
        String password = jPasswordField.getText();


        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method without further processing
        }

        // Print the user input details on the console
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        User user = new User(username, password);
        userArrayList.add(user);
        saveUserListToFile();
        openLogin();
    }

    private void saveUserListToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/java/gui/user.txt"))) {
            oos.writeObject(userArrayList);
            System.out.println("User details saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving user details to file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main2() {
        SwingUtilities.invokeLater(() -> new SignUpGUI());
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new SignUpGUI());
//        // new LoginGUI();
//    }

}
