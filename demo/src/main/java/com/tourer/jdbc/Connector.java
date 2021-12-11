package com.tourer.jdbc;


import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

//like %string% same with regex .*string.*
public class Connector {
    
    public static Connection connector;
    public static Statement statement;
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
}
