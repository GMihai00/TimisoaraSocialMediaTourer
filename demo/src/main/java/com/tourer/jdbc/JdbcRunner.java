package com.tourer.jdbc;


import java.sql.SQLException;


public class JdbcRunner {
    
    public static void buildJdbc() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/tourer";
        String user = "root";
        String password = "Calebbb1234567890";
        
        Connector.Connect(url, user, password);

    }
}
