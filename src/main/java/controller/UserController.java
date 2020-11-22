package controller;
import dao.UserDao;
import model.UserModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserController {
    private String url ="jdbc:mysql://localhost/flightapp";
    private String username = "root";
    private String pass =   "";
    private Connection connection;


    private  UserController(){
        try{
            connection = DriverManager.getConnection(url, username, pass);
        }catch (SQLException e ){
            e.printStackTrace();
        }
    }

    private static final class SingletonHolder{
        private static final UserController INSTANCE = new UserController();
    }

    public static UserController getInstance(){
        return  SingletonHolder.INSTANCE;
    }



    public boolean adaugaUser (UserModel userModel)  {
        boolean rez = false;
        UserDao userDao = new UserDao(connection);
        Optional<UserModel> optionalUserModel = null;
        try {
            optionalUserModel = userDao.findUser(userModel.getUserNume());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!optionalUserModel.isPresent()) {
            userDao.insertRegister(userModel);
            rez = true;
        }
        return rez;
    }


    public Optional<UserModel> loginUser (String username, String password)  {
        UserDao userDao = new UserDao(connection);
        try{
        Optional<UserModel> optionalUserModel = userDao.findUser(username);
        if (optionalUserModel.isPresent()) {
            if (optionalUserModel.get().getPassword().equals(password)) {
                return optionalUserModel;
            }
        }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
        }

        public List<UserModel> getUsernameEmailList(String nume){
            UserDao userDao = new UserDao(connection);
            return userDao.afisareDetaliiUtilizator(nume);
        }

        public boolean setNewUsername( String nume ){
        UserDao userDao = new UserDao(connection);
        return userDao.updateUsername(nume);
        }

        public boolean setNewEmail ( String email ){
        UserDao userDao = new UserDao(connection);
        return userDao.updateEmail(email);
        }

        public boolean setNewPass(String pass){
        UserDao userDao = new UserDao(connection);
        return userDao.updatePass(pass);
        }
}
