package com.tourer.jdbc;

import com.tourer.gui.map.Location;

import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.tourer.App;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;


public class Connector {
    
    public static Pattern ALREADY_IN_USE_USERNAME = Pattern.compile("Duplicate entry '.+' for key 'userprofile.username'");
    public final static String ERROR_DUBLICATE_USERNAME = "User already exist with given username";
    public static Pattern ALREADY_IN_USE_EMAIL = Pattern.compile("Duplicate entry '.+' for key 'userprofile.email'");
    public final static String ERROR_DUBLICATE_EMAIL = "User already exist with given email";
    public final static String ERROR_CARD_UPDATE = "Couldn't load card data";
    public final static String ERROR_LIKE_UPDATE = "Failed to update number of likes";
    public static Connection connector;
    public static Statement statement;
    public static Integer USERID;
    public static String USERNAME;
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
        if (!resultSet.next()){
            resultSet.close();
            return false;
        }
        USERNAME = username;
        USERID = resultSet.getInt("id");
        resultSet.close();
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

        String query = "SELECT username FROM UserProfile WHERE username REGEXP '" + username + "' AND id!=" + USERID + " ORDER BY username LIMIT 10";
        ResultSet resultSet = runQuery(query);
        while(resultSet.next()){
            String name = resultSet.getString("username");
            rez.add(name);
        }
        resultSet.close();
        return rez;
    }

    public static void deleteLocation(String name) throws SQLException{
        String query = "DELETE from location where id =" + Connector.USERID + " and name = '" + name + "';";
        PreparedStatement statement = Connector.connector.prepareStatement(query);
        statement.executeUpdate(); 
    }

    public static Vector <String> getLocationList(String name) throws SQLException{
        Vector <String> rez = new Vector <String>();

        String query = "SELECT name FROM  Location WHERE name REGEXP '" + name + "' ORDER BY name LIMIT 10";
        ResultSet resultSet = runQuery(query);
        while(resultSet.next()){
            String lname = resultSet.getString("name");
            rez.add(lname);
        }
        resultSet.close();
        return rez;
    }

    public static boolean createLocation(double latitude, double longitude, String name, String description) throws SQLException{
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

    public static boolean modifyLocation(double latitude, double longitude, String name, String description){
        String query = "UPDATE Location SET name='" + name + "', description='" + description  +"' WHERE id="+ Connector.USERID +" AND latitude="+ latitude +" AND longitude="+ longitude +";";

        try {
            runUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
            
        }
        
        return true;
    }

    public static Vector <Location> getVisitedLocations() throws SQLException{
        Vector <Location> locationList = new Vector<Location>(); 
        
        String query = "SELECT latitude, longitude, description, likes, dislikes, photo, name FROM  Location WHERE id=" + USERID + ";";

        ResultSet resultSet = runQuery(query);
        while(resultSet.next()){
            
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Double latitude = resultSet.getDouble("latitude");
            Double longitude = resultSet.getDouble("longitude");
            int likes = resultSet.getInt("likes");
            int dislikes = resultSet.getInt("dislikes");
            Set <String> userlikes = new TreeSet<>();
            Set <String> userdislikes = new TreeSet<>();
            String locationImage = resultSet.getString("photo");
            locationList.add(new Location(name, description, longitude, latitude, likes, dislikes, locationImage, userlikes, userdislikes));
        }
        resultSet.close();
        return locationList;
        
    }

    public static Vector <Location> getOtherVisitedLocations(String username) throws SQLException{
        Vector <Location> locationList = new Vector<Location>(); 
        
        String query = "SELECT latitude, longitude, description, likes, dislikes, photo, name FROM  Location WHERE id=( SELECT id FROM UserProfile WHERE username='" + username  +"');";
        Statement statement2 = connector.createStatement();
        ResultSet resultSet = runQuery(query);
        while(resultSet.next()){
            
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Double latitude = resultSet.getDouble("latitude");
            Double longitude = resultSet.getDouble("longitude");
            int likes = resultSet.getInt("likes");
            int dislikes = resultSet.getInt("dislikes");
            Set <String> userlikes = new TreeSet<>();
            Set <String> userdislikes = new TreeSet<>();

            String locationImage = resultSet.getString("photo");
            String query2 = "SELECT nameother, type from likestate WHERE  id=( SELECT id FROM UserProfile WHERE username='" + username  +"') AND name = '" + name + "';";
            
            ResultSet resultSet2 = statement2.executeQuery(query2);
            
            while(resultSet2.next()){
                boolean type = resultSet2.getBoolean("type");
                String nameother = resultSet2.getString("nameother");
               
                if(type == true)
                    userlikes.add(nameother);
                else
                    userdislikes.add(nameother);
                
            }
            resultSet2.close();
        
            locationList.add(new Location(name, description, longitude, latitude, likes, dislikes, locationImage, userlikes, userdislikes));
        }
        statement2.close();
        resultSet.close();
        return locationList;
        
    }
    public static void insertCardData(String cardType, String cardNumber, String expriationDate, String securityCode){
        String query = "INSERT INTO CreditCard(id, type, number, securityCode, expirationDate) VALUES(" + USERID + ",'" + cardType + "'," + cardNumber + "," + securityCode + ",STR_TO_DATE('01/" + expriationDate + "', '%d/%m/%Y')" + ");";
       
        try {
            runUpdate(query);
        } catch (SQLException e) {
            updateCardData(cardType, cardNumber, expriationDate, securityCode);
            
        }

    }

    public static void updateCardData(String cardType, String cardNumber, String expriationDate, String securityCode){
        String query = "UPDATE CreditCard SET type='" + cardType + "',number=" + cardNumber + ",expirationDate=STR_TO_DATE('01/" + expriationDate + "', '%d/%m/%Y')" + ",securityCode=" + securityCode + " WHERE id=" + USERID + ";";
       
        try {
            runUpdate(query);
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(App.accountCreationFrame, ERROR_CARD_UPDATE, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void  like(String name, String username, int like){
        String query = "UPDATE Location SET likes=" + like +  " WHERE id=( SELECT id FROM UserProfile WHERE username='" + username +  "') AND name = '" + name +  "';";
        try {
            runUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(App.accountCreationFrame, ERROR_LIKE_UPDATE, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }   

    public static void dislike(String name, String username, int dislike){
        
        String query = "UPDATE Location SET dislikes=" + dislike +  " WHERE id=( SELECT id FROM UserProfile WHERE username='" + username + "') AND name = '" + name +  "';";
        try {
            runUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(App.accountCreationFrame, ERROR_LIKE_UPDATE, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Location getFirstLocationByName(String namelocation) throws SQLException{
        Location location = new Location();
        String query = "SELECT latitude, longitude, description, likes, dislikes, name FROM  Location WHERE name='" + namelocation  +"' LIMIT 1;";
        ResultSet resultSet = runQuery(query);
        if(resultSet.next()){
            
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Double latitude = resultSet.getDouble("latitude");
            Double longitude = resultSet.getDouble("longitude");
            int likes = resultSet.getInt("likes");
            int dislikes = resultSet.getInt("dislikes");
            Set <String> userlikes = new TreeSet<>();
            Set <String> userdislikes = new TreeSet<>();
            
            location = new Location(name, description, longitude, latitude, likes, dislikes, "", userlikes, userdislikes);
            
        }
        return location;
        
    }

    public static void modifylikeState(String name, String username, boolean type) throws SQLException{
        
        String query = "SELECT * FROM likestate WHERE nameother = '" + USERNAME + "' AND type = " + type + " AND name = '" + name + "' AND id = ( SELECT id FROM UserProfile WHERE username='" + username +  "');";
        
        String query2 = "";
        ResultSet resultSet = runQuery(query);

        if(resultSet.next()){
            query2 = "DELETE FROM likestate WHERE nameother = '" + USERNAME + "' AND type = " + type + " AND name = '" + name + "' AND id = ( SELECT id FROM UserProfile WHERE username='" + username +  "');";
            
        }
        else
        {
            query2 = "INSERT INTO likestate(id, name, nameother, type) VALUES (( SELECT id FROM UserProfile WHERE username='" + username +  "'),'" + name + "', '" + USERNAME + "', " + type + "  );";
        }
        resultSet.close();
        runUpdate(query2);
    }
    
    public static void updatePhoto(String name, String newpath) throws SQLException{
        newpath = newpath.replace("\\", "\\\\");
        String query = "UPDATE Location SET photo='" + newpath + "' WHERE name='" + name + "' AND id=" + USERID + ";";

        System.out.println(query);
        runUpdate(query);


    }
}
