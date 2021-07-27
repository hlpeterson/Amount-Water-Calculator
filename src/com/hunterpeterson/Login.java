package com.hunterpeterson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class Login implements ActionListener {
    JFrame frame = new JFrame();
    JLabel welcomeMessageLabel = new JLabel("Welcome! Please log in or create an account below. ");
    JLabel userLabel = new JLabel("Username: ");
    JLabel passLabel = new JLabel("Password: ");
    JLabel errorLabel = new JLabel();
    JButton loginButton = new JButton("Log in");
    JButton signUpButton = new JButton("Sign up");
    JTextField usernameInput = new JTextField();
    JPasswordField passwordInput = new JPasswordField();

    Login() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);

        welcomeMessageLabel.setBounds(70, 20, 350, 25);
        userLabel.setBounds(30, 60, 100, 25);
        passLabel.setBounds(30, 100, 100, 25);
        errorLabel.setBounds(70, 140, 300, 25);

        loginButton.setBounds(50, 200, 100, 25);
        loginButton.addActionListener(this);

        signUpButton.setBounds(150, 200, 100, 25);
        signUpButton.addActionListener(this);


        usernameInput.setBounds(120, 60, 150, 25);
        passwordInput.setBounds(120, 100, 150, 25);

        frame.add(welcomeMessageLabel);
        frame.add(userLabel);
        frame.add(passLabel);
        frame.add(errorLabel);
        frame.add(loginButton);
        frame.add(signUpButton);
        frame.add(usernameInput);
        frame.add(passwordInput);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ArrayList<String> usernames = Database.getUsers();
            ArrayList<String> passwords = Database.getPasswords();
            ArrayList<String> weights = Database.getWeights();
            ArrayList<String> sexes = Database.getSexes();
            ArrayList<String> ages = Database.getAges();
            ArrayList<String> activities = Database.getActivities();
            if (e.getSource() == loginButton) {
                String username = usernameInput.getText();
                String password = String.valueOf(passwordInput.getPassword());

                if (username.isEmpty() || password.isEmpty() || username.length() > 20 || password.length() > 20) {
                    errorLabel.setText("One or more required fields have invalid values");
                } else {
                    if (usernames.contains(username)) {
                        for (int i = 0; i < usernames.size(); i++) {
                            if (usernames.get(i).equals(username) && passwords.get(i).equals(password)) {
                                int weight = 0;
                                int age = 0;
                                String sex = "";
                                String activity = "";
                                for (int j = 0; j < usernames.size(); j++) {
                                    if (usernames.get(j).equals(username)) {
                                        weight = Integer.parseInt(weights.get(j));
                                        age = Integer.parseInt(ages.get(j));
                                        sex = sexes.get(j);
                                        activity = activities.get(j);
                                    }
                                }
                                frame.dispose();
                                Results result = new Results(weight, age, sex, activity, username, password);

                            }
                            errorLabel.setText("Your password is incorrect");
                        }
                    } else {
                        errorLabel.setText("That username does not exist");
                    }
                }
            } else {
                SignUp signUp = new SignUp();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

