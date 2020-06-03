package dao;

import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDao {

    private Connection connection;
    private PreparedStatement insertQuerry;
    private PreparedStatement updateQuerry;
    private PreparedStatement selectQuerry;

    public UserDao(Connection connection) {
        this.connection = connection;

        try {
            insertQuerry = connection.prepareStatement("INSERT INTO register_user VALUES(null, ?, ?, ?)");
            updateQuerry = connection.prepareStatement("UPDATE register_user SET password = ? WHERE username = ?");
            selectQuerry = connection.prepareStatement("SELECT * FROM register_user WHERE username = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertRegister(UserModel userModel) {
        try {
            insertQuerry.setString(1, userModel.getUserNume());
            insertQuerry.setString(2, userModel.getPassword());
            insertQuerry.setString(3, userModel.getEmail());
            return insertQuerry.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Optional<UserModel> findUser(String username) throws SQLException {
        String sql = "SELECT * FROM register_user WHERE username = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                UserModel userModel = UserModel.builder()
                        .id(rs.getInt("id"))
                        .userNume(rs.getString("username"))
                        .password(rs.getString("password"))
                        .email(rs.getString("email"))
                        .build();
                return Optional.of(userModel);
            }
        }
        return Optional.empty();
    }


//    public boolean updatePass(){
//        updateQuerry.setString(1, );
//    }


    public List<UserModel> afisareDetaliiUtilizator (String nume){
        try {
            selectQuerry.setString(1, nume);

            ResultSet resultSet = selectQuerry.executeQuery();

            List<UserModel> userModels = new ArrayList<>();
            while (resultSet.next()){
                        String username = resultSet.getString("username");
                        String email = resultSet.getString("email");
                        UserModel user = new UserModel(username, email);
                        userModels.add(user);
            }
            return  userModels;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}


