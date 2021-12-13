package com.tourer.jdbc;


import java.sql.Statement;
import java.util.Vector;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;


public class Connector {
    
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
            e.printStackTrace();
            return false;
            
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
}
