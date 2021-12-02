package com.tourer.jdbc;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class UserData {
    private final static String query ="SELECT USERNAME, PASSWORD FROM USSERS";
    public static HashMap<String, String> userMap = new HashMap<String, String>();
    public static void getuserMap() throws SQLException{
        
        ResultSet rawData = Connector.runQuery(query);
        while(rawData.next()){
            String userName = rawData.getString("USERNAME");
            String password = rawData.getString("PASSWORD");
            UserData.userMap.put(userName, password);
        }
        rawData.close();
    }
}
