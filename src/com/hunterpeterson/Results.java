package com.hunterpeterson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Results implements ActionListener {
    JFrame frame = new JFrame();
    JButton updateButton = new JButton("Update information");
    JButton logOutButton = new JButton("Log out");
    JLabel resultsDisplay = new JLabel();
    private int weight;
    private int age;
    private String sex;
    private String activity;
    private String username;
    private String password;

    Results(int weight, int age, String sex, String activity, String username, String password) {
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        this.activity = activity;
        this.username = username;
        this.password = password;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);

        updateButton.setBounds(100, 300, 150, 25);
        updateButton.addActionListener(this);
        logOutButton.setBounds(300, 300, 150, 25);
        logOutButton.addActionListener(this);

        resultsDisplay.setBounds(20, 20, 600, 100);
        resultsDisplay.setText("You should drink " + calculateResults(this.weight, this.age, this.sex, this.activity) + " ounces of water per day");
        resultsDisplay.setFont(new Font(null, Font.ITALIC, 25));
        frame.add(resultsDisplay);
        frame.add(updateButton);
        frame.add(logOutButton);
        frame.setVisible(true);
    }

    private String calculateResults(int weight, int age, String sex, String activity) {
        double average;
        double finalValue;
        String returnString;
        int roundedValue;
        if(sex.equals("M") || sex.equals("F")) {
            average = (weightAverage(weight) + ageAndSexAverage(age, sex)) / 2;
            finalValue = average + activityAverage(activity);
        } else {
            finalValue = weightAverage(weight) + activityAverage(activity);
        }
        roundedValue = Math.round(Float.parseFloat(Double.toString(finalValue)));
        returnString = String.valueOf(roundedValue);
        return returnString;
    }

    private double weightAverage(int weight) {
        return weight * .75;
    }

    private double ageAndSexAverage(int age, String sex) {
        if(sex.equals("M")) {
            if(age <= 1) {
                return .8 * 33.814;
            } else if (age <= 3) {
                return 1.3 * 33.814;
            } else if (age <= 8) {
                return 1.7 * 33.814;
            } else if (age <= 13) {
                return 2.4 * 33.814;
            } else if (age <= 18) {
                return 3.3 * 33.814;
            } else {
                return 3.7 * 33.814;
            }
        } else if(sex.equals("F")) {
            if(age <= 1) {
                return .8 * 33.814;
            } else if (age <= 3) {
                return 1.3 * 33.814;
            } else if (age <= 8) {
                return 1.7 * 33.814;
            } else if (age <= 13) {
                return 2.1 * 33.814;
            } else if (age <= 18) {
                return 2.3 * 33.814;
            } else {
                return 2.7 * 33.814;
            }
        } else {
            return 0;
        }
    }

    private int activityAverage(String activity) {
        if(activity.equals("Light")) {
            return 0;
        } else if(activity.equals("Moderate")){
            return 18;
        } else {
            return 48;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == updateButton) {
            frame.dispose();
            Welcome welcome = new Welcome(username, password);
        } else {
            frame.dispose();
            Login login = new Login();
        }
    }
}

