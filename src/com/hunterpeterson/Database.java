package com.hunterpeterson;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static ArrayList<String> usernames;

    static {
        try {
            usernames = Database.getUsers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static ArrayList<String> passwords;

    static {
        try {
            passwords = Database.getPasswords();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static ArrayList<String> weights;

    static {
        try {
            weights = Database.getWeights();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static ArrayList<String> ages;

    static {
        try {
            ages = Database.getAges();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static ArrayList<String> sexes;

    static {
        try {
            sexes = Database.getSexes();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static ArrayList<String> activities;

    static {
        try {
            activities = Database.getActivities();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void insert(String username, String password, String weight, String age, String sex, String activity) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorial", "root", "");
        String query = " INSERT INTO logininfo (username, pass, weight, sex, age, activity)" + " VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString (1, username);
        preparedStatement.setString (2, password);
        preparedStatement.setInt (3, Integer.parseInt(weight));
        preparedStatement.setString (4, sex);
        preparedStatement.setInt (5, Integer.parseInt(age));
        preparedStatement.setString (6, activity);
        preparedStatement.execute();
        connection.close();
    }

    public static void insert(String username, String password) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorial", "root", "");
        String query = " INSERT INTO logininfo (username, pass)" + " VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString (1, username);
        preparedStatement.setString (2, password);
        connection.close();
    }

    public static void updatePassword(String username, String password) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorial", "root", "");
        String query = " UPDATE logininfo SET pass = ? WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString (1, password);
        preparedStatement.setString (2, username);
        preparedStatement.executeUpdate();
        connection.close();
    }

    public static boolean contains(String username) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorial", "root", "");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select username from logininfo");

        while(resultSet.next()) {
            if(resultSet.getString("username").equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> getUsers () throws SQLException {
        ArrayList<String> usernames = new ArrayList<String>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorial", "root", "");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select username from logininfo");

        while(resultSet.next()) {
            usernames.add(resultSet.getString("username"));
        }
        return usernames;
    }

    public static ArrayList<String> getPasswords () throws SQLException {
        ArrayList<String> passwords = new ArrayList<String>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorial", "root", "");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select pass from logininfo");

        while(resultSet.next()) {
            passwords.add(resultSet.getString("pass"));
        }
        return passwords;
    }

    public static ArrayList<String> getWeights () throws SQLException {
        ArrayList<String> weights = new ArrayList<String>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorial", "root", "");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select weight from logininfo");

        while(resultSet.next()) {
            weights.add(resultSet.getString("weight"));
        }
        return weights;
    }

    public static ArrayList<String> getSexes () throws SQLException {
        ArrayList<String> sexes = new ArrayList<String>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorial", "root", "");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select sex from logininfo");

        while(resultSet.next()) {
            sexes.add(resultSet.getString("sex"));
        }
        return sexes;
    }

    public static ArrayList<String> getAges () throws SQLException {
        ArrayList<String> ages = new ArrayList<String>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorial", "root", "");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select age from logininfo");

        while(resultSet.next()) {
            ages.add(resultSet.getString("age"));
        }
        return ages;
    }

    public static ArrayList<String> getActivities () throws SQLException {
        ArrayList<String> activities = new ArrayList<String>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorial", "root", "");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select activity from logininfo");

        while(resultSet.next()) {
            activities.add(resultSet.getString("activity"));
        }
        return activities;
    }


    public static void updateWeight(String username, String weight) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorial", "root", "");
        String query = " UPDATE logininfo SET weight = ? WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt (1, Integer.parseInt(weight));
        preparedStatement.setString (2, username);
        preparedStatement.executeUpdate();
        connection.close();
    }

    public static void updateAge(String username, String age) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorial", "root", "");
        String query = " UPDATE logininfo SET age = ? WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt (1, Integer.parseInt(age));
        preparedStatement.setString (2, username);
        preparedStatement.executeUpdate();
        connection.close();
    }

    public static void updateSex(String username, String sex) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorial", "root", "");
        String query = " UPDATE logininfo SET sex = ? WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString (1, sex);
        preparedStatement.setString (2, username);
        preparedStatement.executeUpdate();
        connection.close();
    }

    public static void updateActivity(String username, String activity) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorial", "root", "");
        String query = " UPDATE logininfo SET activity = ? WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString (1, activity);
        preparedStatement.setString (2, username);
        preparedStatement.executeUpdate();
        connection.close();
    }

}

