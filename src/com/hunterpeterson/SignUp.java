package com.hunterpeterson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class SignUp implements ActionListener {
    JFrame frame = new JFrame();
    JLabel messageLabel = new JLabel("Please enter your information below");
    JLabel userLabel = new JLabel("Create Username: ");
    JLabel passLabel1 = new JLabel("Create Password: ");
    JLabel passLabel2 = new JLabel("Re-Type Password: ");
    JLabel errorLabel = new JLabel();
    JButton backButton = new JButton("Log in");
    JButton createAccountButton = new JButton("Sign up");
    JTextField usernameInput = new JTextField();
    JPasswordField passwordInput1 = new JPasswordField();
    JPasswordField passwordInput2 = new JPasswordField();

    SignUp() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);

        messageLabel.setBounds(70, 20, 300, 25);
        userLabel.setBounds(30, 50, 200, 25);
        passLabel1.setBounds(30, 80, 200, 25);
        passLabel2.setBounds(30, 110, 200, 25);
        errorLabel.setBounds(50, 200, 400, 25);

        backButton.setBounds(100, 300, 100, 25);
        backButton.addActionListener(this);
        createAccountButton.setBounds(250, 300, 100, 25);
        createAccountButton.addActionListener(this);

        usernameInput.setBounds(250, 50, 100, 25);
        passwordInput1.setBounds(250, 80, 100, 25);
        passwordInput2.setBounds(250, 110, 100, 25);

        frame.add(messageLabel);
        frame.add(userLabel);
        frame.add(passLabel1);
        frame.add(passLabel2);
        frame.add(backButton);
        frame.add(createAccountButton);
        frame.add(usernameInput);
        frame.add(passwordInput1);
        frame.add(passwordInput2);
        frame.add(errorLabel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createAccountButton) {
            try {
                ArrayList<String> usernames = Database.getUsers();

                String username = usernameInput.getText();
                String pass1 = String.valueOf(passwordInput1.getPassword());
                String pass2 = String.valueOf(passwordInput2.getPassword());
                if (!username.isEmpty() && !pass1.isEmpty() && !pass2.isEmpty()) {
                    if (username.length() <= 20 && pass1.length() <= 20 && pass2.length() <= 20) {
                        if (pass1.equals(pass2)) {
                            if(!usernames.contains(username)) {
                                frame.dispose();
                                Welcome welcome = new Welcome(username, pass1);
                            } else {
                                errorLabel.setText("That username is unavailable");
                            }
                        } else {
                            errorLabel.setText("Passwords do not match");
                        }
                    } else {
                        errorLabel.setText("One or more fields has an incorrect number of characters");
                    }
                } else {
                    errorLabel.setText("one or more required fields have been left empty");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            Login login = new Login();
        }
    }
}

