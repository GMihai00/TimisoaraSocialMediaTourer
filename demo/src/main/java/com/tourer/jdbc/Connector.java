package com.tourer.jdbc;


import java.sql.Statement;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.tourer.App;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;


public class Connector {
    
    public static Pattern ALREADY_IN_USE_USERNAME = Pattern.compile("Duplicate entry '.+' for key 'userprofile.username'");
    public static String ERROR_DUBLICATE_USERNAME = "User already exist with given username";
    public static Pattern ALREADY_IN_USE_EMAIL = Pattern.compile("Duplicate entry '.+' for key 'userprofile.email'");
    public static String ERROR_DUBLICATE_EMAIL = "User already exist with given email";
    public static Connection connector;
    public static Statement statement;
    public static Integer USERID;
    public static void Connect(String url, String user, String password) throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            
            e.printStackTrace();
            System.exit(2);
        }
        Connector.connector =  DriverManager.getConnection(url, user, password);
        Connector.statement = connector.createStatement();
        
    }

    public static void runUpdate(String query) throws SQLException{

        Connector.statement.executeUpdate(query);
    }

    public static ResultSet runQuery(String query) throws SQLException{

        ResultSet result = Connector.statement.executeQuery(query);
        return result;
    }

    public static void closeStament() throws SQLException{
        Connector.statement.close();
    }

    public static void closeConnection() throws SQLException{
        Connector.connector.close();
    }


    public static boolean checkUserExistance(String username, String password) throws SQLException{
        

        String query = "SELECT id from UserProfile WHERE username='" + username + "' AND password='" + password + "'";
        
        ResultSet resultSet = runQuery(query);
        if (!resultSet.next())
            return false;

        USERID = resultSet.getInt("id");
        return true;
    }

    public static boolean createUser(String username, String password, String email){
        String query = "INSERT INTO UserProfile (creditcardno, username, password, email) VALUES ('-','" + username + "','" + password + "','" + email  + "');";
        try {
            runUpdate(query);
        } catch (SQLException e) {
            
            String SQLConstraintError = e.getMessage();
            System.out.println(SQLConstraintError);
            Matcher userMatcher = ALREADY_IN_USE_USERNAME.matcher(SQLConstraintError);
            if(userMatcher.find())
            {
                JOptionPane.showMessageDialog(App.accountCreationFrame, ERROR_DUBLICATE_USERNAME, "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }   
            Matcher mailMatcher = ALREADY_IN_USE_EMAIL.matcher(SQLConstraintError);
            if(mailMatcher.find())
            {
                JOptionPane.showMessageDialog(App.accountCreationFrame, ERROR_DUBLICATE_EMAIL, "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }   
            
            JOptionPane.showMessageDialog(App.accountCreationFrame, "Failed to create user", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
            
        }
        try{
            MailSender.sendEmail(email);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Failed to send email");
        }
        return true;
    }

    public static Vector <String> getUserList(String username) throws SQLException{
        Vector <String> rez = new Vector <String>();

        String query = "SELECT username FROM UserProfile WHERE username REGEXP '" + username + "' ORDER BY username LIMIT 10";
        ResultSet resultSet = runQuery(query);
        while(resultSet.next()){
            String name = resultSet.getString("username");
            rez.add(name);
        }
        
        return rez;
    }

    public static Vector <String> getLocationList(String name) throws SQLException{
        Vector <String> rez = new Vector <String>();

        String query = "SELECT name FROM  Location WHERE name REGEXP '" + name + "' ORDER BY name LIMIT 10";
        ResultSet resultSet = runQuery(query);
        while(resultSet.next()){
            String lname = resultSet.getString("name");
            rez.add(lname);
        }
        
        return rez;
    }

    public static boolean createLocation(int latitude, int longitude, String name, String description) throws SQLException{
        String query = "INSERT INTO Location(id, latitude, longitude, description, name) VALUES (" + Connector.USERID + "," + latitude + "," + longitude + ",'" + description + "','" + name  + "');";

        try {
            runUpdate(query);
        } catch (SQLException e) {
            
            boolean updateok = modifyLocation(latitude, longitude, name, description);
            
            if(updateok == false){
                JOptionPane.showMessageDialog(App.mainFrame, "Failde to add location to database", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else
            {
                JOptionPane.showMessageDialog(App.mainFrame, "Current location already existant, updated it", "UPDATE", JOptionPane.INFORMATION_MESSAGE);
                
            }
            

        }
        
        return true;
    }

    public static boolean modifyLocation(int latitude, int longitude, String name, String description){
        String query = "UPDATE Location SET name='" + name + "', description='" + description  +"' WHERE id="+ Connector.USERID +" AND latitude="+ latitude +" AND longitude="+ longitude +";";

        try {
            runUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
            
        }
        
        return true;
    }

}
