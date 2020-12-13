package Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.* ; 

public class ConnectionClass {
    static public Connection connection;
    static public Connection getConnection(){
        String dbName="user";
        String userName="root";
        String password="";

        try {
            Class.forName("org.sqlite.JDBC").newInstance();
            connection= DriverManager.getConnection("jdbc:sqlite:C://Users/Dell/Desktop/Packitin.db");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}