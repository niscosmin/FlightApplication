package auditService;

import controller.UserController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {

    private String url ="jdbc:mysql://localhost/flightapp";
    private String username = "root";
    private String pass =   "";
    private Connection connection;

    private  DBConection(){
        try{
            connection = DriverManager.getConnection(url, username, pass);
        }catch (SQLException e ){
            e.printStackTrace();
        }
    }

    private static final class SingletonHolder{
        private static final DBConection INSTANCE = new DBConection();
    }

    public static DBConection getInstance(){
        return  DBConection.SingletonHolder.INSTANCE;
    }
}
