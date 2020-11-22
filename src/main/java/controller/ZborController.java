package controller;
import dao.ZborDao;
import model.ZborModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class ZborController {

    private String url ="jdbc:mysql://localhost/flightapp";
    private String username = "root";
    private String pass =   "";
    private Connection connection;

    public ZborController() {
        try {
            connection = DriverManager.getConnection(url, username, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static final class SingletonHolder{
        private static final  ZborController INSTANCE = new ZborController();
    }

    public static  ZborController getInstance(){
        return  SingletonHolder.INSTANCE;
    }

    public boolean adaugaZbor(ZborModel zborModel){
        boolean rez = false;
        ZborDao zborDao = new ZborDao(connection);
        Optional<ZborModel> optionalZborModel = null;
        optionalZborModel = zborDao.findZbor(zborModel.getSursa(), zborModel.getDestinatie());
        if (!optionalZborModel.isPresent()){
            zborDao.insertZboruri(zborModel);
            rez =true;
        }
        return rez;
    }
}
