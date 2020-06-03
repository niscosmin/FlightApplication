package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String url = "jdbc:mysql://localhost:3306/flightapp?serverTimezone=UTC";
    private static String user = "root";
    private static String password = "";
    private static Connection connection ;

    private DatabaseConnection(){
    }

    public static Connection getConection(){
        if( connection == null){
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
