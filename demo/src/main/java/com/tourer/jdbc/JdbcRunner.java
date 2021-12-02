package com.tourer.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcRunner {
    
    public static void buildJdbc() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/tourer";
        String user = "root";
        String password = "Calebbb1234567890";
        
        Connector.Connect(url, user, password);

    }
}
