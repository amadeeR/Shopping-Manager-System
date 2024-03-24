package gui;

import console.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static gui.ShoppingCenterGUI.main4;

public class LoginGUI extends JFrame {
    private JTextField textfldUsername;
    private JPasswordField jPasswordField;
    private String enteredUsername;
    private String enteredPassword;
    static Font myFont1 = new Font("SansSerif",Font.BOLD,18);

    //public static JComboBox<String> productCategoryComboBox;

    public LoginGUI() {
//        setTitle("Login");
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(400,250));
        setLocationRelativeTo(null);
        setVisible(true);
        components();

    }

    public void components(){

        JPanel jPanel =new JPanel();
       // jPanel.setBackground(Color.WHITE);
        jPanel.setLayout(null);
        jPanel.setBounds(50, 50, 300, 200);

        JLabel labelLogin = new JLabel("Login");
        labelLogin.setFont(myFont1);
        labelLogin.setBounds(160,10,100,30);
        jPanel.add(labelLogin);
        add(jPanel);

        JLabel labelUsername = new JLabel("Username : ");
        labelUsername.setBounds(30, 50, 80, 30);
        textfldUsername = new JTextField(10);
        textfldUsername.setBounds(120, 50, 150, 30);
        jPanel.add(labelUsername);
        jPanel.add(textfldUsername);

        JLabel labelPassword = new JLabel("Password : ");
        labelPassword.setBounds(30, 90, 80, 30);
        jPasswordField = new JPasswordField(10);
        jPasswordField.setBounds(120, 90, 150, 30);
        jPanel.add(labelPassword);
        jPanel.add(jPasswordField);

        JButton jbtnLogin = new JButton("Login");
        jbtnLogin.setBounds(120, 130, 80, 30);
        jPanel.add(jbtnLogin);

        JButton jbtnReset = new JButton("Reset");
        jbtnReset.setBounds(220, 130, 80, 30);
        jPanel.add(jbtnReset);

        JLabel signUpLabel = new JLabel("Don't have an account? ");
        // signUpLabel.setForeground(Color.BLUE);  // Change text color to blue for hyperlink-like appearance
        signUpLabel.setBounds(30, 170, 200, 30);
        jPanel.add(signUpLabel);

        JButton jbtnSignUp = new JButton("<html><a href =\'#\'>SignUp</a></html>");
        jbtnSignUp.setBounds(180, 170, 80, 30);
        jPanel.add(jbtnSignUp);

        jbtnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open SignUpGUI when the button is clicked
                handleLoginButtonClick();

            }
        });

        jbtnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open SignUpGUI when the button is clicked
                SignUpGUI.main2();
                // Close the LoginGUI if needed
                dispose();
            }
        });

    }
    private void handleLoginButtonClick() {
        String username = textfldUsername.getText();
        String password = new String(jPasswordField.getPassword());

        if (isValidCredentials(username, password)) {
            JOptionPane.showMessageDialog(this, "Login Successful");
            main4();
            dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidCredentials(String username, String password) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/java/gui/user.txt"))) {
            ArrayList<User> userArrayList = (ArrayList<User>) ois.readObject();

            for (User user : userArrayList) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return true; // Credentials match
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false; // Credentials do not match or file reading error
    }
      public static void main1() {
       SwingUtilities.invokeLater(() -> new LoginGUI());
   }


//   public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new LoginGUI());
//        // new LoginGUI();
//    }

}



