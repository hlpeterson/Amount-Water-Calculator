package com.hunterpeterson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class Welcome implements ActionListener {
    JFrame frame = new JFrame();
    JLabel welcomeMessage = new JLabel("Welcome! Please fill out the questions below.");
    JLabel weightLabel = new JLabel("Weight: (lbs)");
    JLabel ageLabel = new JLabel("Age: (years)");
    JLabel sexLabel = new JLabel("Sex: (M, F, or N/A)");
    JLabel activityLabel = new JLabel("Activity level: ");
    JLabel bottomActivityLabel = new JLabel("(Light, Moderate, or Intense)");
    JLabel errorMessage = new JLabel();
    JTextField weightText = new JTextField();
    JTextField ageText = new JTextField();
    JTextField sexText = new JTextField();
    JTextField activityText = new JTextField();
    JButton calculateButton = new JButton("Calculate");
    String username;
    String password;

    Welcome(String username, String password) {
        this.username = username;
        this.password = password;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);

        weightLabel.setBounds(20, 90, 125, 25);
        welcomeMessage.setBounds(70, 20, 300, 25);
        ageLabel.setBounds(20, 183, 125, 25);
        sexLabel.setBounds(20, 277, 125, 25);
        activityLabel.setBounds(20, 370, 150, 25);
        bottomActivityLabel.setBounds(20, 385, 200, 25);
        errorMessage.setBounds(70, 55, 300, 25);

        weightText.setBounds(150, 90, 200, 25);
        ageText.setBounds(150, 183, 200, 25);
        sexText.setBounds(150, 277, 200, 25);
        activityText.setBounds(150, 370, 200, 25);

        calculateButton.setBounds(120, 440, 200, 25);
        calculateButton.addActionListener(this);



        frame.add(weightLabel);
        frame.add(welcomeMessage);
        frame.add(ageLabel);
        frame.add(sexLabel);
        frame.add(activityLabel);
        frame.add(bottomActivityLabel);
        frame.add(errorMessage);
        frame.add(weightText);
        frame.add(ageText);
        frame.add(sexText);
        frame.add(activityText);
        frame.add(calculateButton);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String weightInput = weightText.getText();
        String ageInput = ageText.getText();
        String sexInput = sexText.getText();
        String activityInput = activityText.getText();
        int weightValue;
        int ageValue;
        try {
            ArrayList<String> usernames = Database.getUsers();

            if(weightInput.isEmpty() || ageInput.isEmpty() || sexInput.isEmpty() || activityInput.isEmpty()) {
                errorMessage.setText("Error: one or more fields have been left empty");
                return;
            }

            try {
                weightValue = Integer.parseInt(weightInput);
                if(weightValue < 5 || weightValue > 1400) {
                    errorMessage.setText("Error: weight field is not a valid weight");
                    return;
                }
            } catch (NumberFormatException exception) {
                errorMessage.setText("Error: weight field is not a number");
                return;
            }

            try {
                ageValue = Integer.parseInt(ageInput);
                if(ageValue > 150) {
                    errorMessage.setText("Error: age field is not a valid age");
                    return;
                }
            } catch (NumberFormatException exception) {
                errorMessage.setText("Error: age field is not a number");
                return;
            }

            if(!sexInput.equals("M") && !sexInput.equals("F") && !sexInput.equals("N/A")) {
                errorMessage.setText("Error: sex field is invalid");
                return;
            }

            if(!activityInput.equals("Light") && !activityInput.equals("Moderate") && !activityInput.equals("Intense")) {
                errorMessage.setText("Error: activity field is invalid");
                return;
            }

            frame.dispose();
            if(!usernames.contains(username)) {
                try {
                    Database.insert(username, password, weightInput, ageInput, sexInput, activityInput);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else {
                try {
                    Database.updateActivity(username, activityInput);
                    Database.updateAge(username, ageInput);
                    Database.updateSex(username, sexInput);
                    Database.updateWeight(username, weightInput);
                } catch (SQLException throwables){
                    throwables.printStackTrace();
                }
            }
            Results result = new Results(weightValue, ageValue, sexInput, activityInput, username, password);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

