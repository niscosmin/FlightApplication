package auditService;

import controller.UserController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuditService {

    private String url ="jdbc:mysql://localhost/flightapp";
    private String username = "root";
    private String pass =   "";
    private Connection connection;
    private PreparedStatement insertQuerry;
    String nume,  actiune,  ora;


    public AuditService(){
        try {
            connection = DriverManager.getConnection(url, username, pass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            insertQuerry = connection.prepareStatement("INSERT INTO audit VALUES(?, ?, ?)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static final class SingletonHolder{
        private static final AuditService INSTANCE = new AuditService();
    }

    public static AuditService getInstance(){
        return  AuditService.SingletonHolder.INSTANCE;
    }


    public AuditService(String nume, String actiune, String ora) {
        this.nume = nume;
        this.actiune = actiune;
        this.ora = ora;
    }

    public boolean saveAudit(String nume, String actiune, String ora ){
        try {
            insertQuerry.setString(1, nume);
            insertQuerry.setString(2,actiune);
            insertQuerry.setString(3, ora);
            return insertQuerry.executeUpdate() !=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
