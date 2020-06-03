package dao;

import model.ZborModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ZborDao {

    private Connection connection;
    private PreparedStatement insertQuerry;
    private PreparedStatement selectQuerryZbor;

    public ZborDao(Connection connection) {
        this.connection = connection;

        try {
            insertQuerry = connection.prepareStatement("INSERT INTO zboruri VALUES(null, ?, ?, ?, ?, ?, ?)");
            selectQuerryZbor = connection.prepareStatement("SELECT * FROM zboruri WHERE sursa = ?  AND destinatie = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertZboruri (ZborModel zborModel){
        try {
            insertQuerry.setString(1, zborModel.getSursa());
            insertQuerry.setString(2, zborModel.getDestinatie());
            insertQuerry.setString(3,zborModel.getOraPlecare());
            insertQuerry.setString(4, zborModel.getOraSosire());
            insertQuerry.setString(5, zborModel.getZile());
            insertQuerry.setInt(6, zborModel.getPret());
            return insertQuerry.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Optional<ZborModel> findZbor ( String sursa, String destinatie ){
        try {
            selectQuerryZbor.setString(1, sursa);
            selectQuerryZbor.setString(2, destinatie);
            ResultSet rs = selectQuerryZbor.executeQuery();

            if(rs.next()){
                ZborModel zborModel = ZborModel.builder()
                        .id(rs.getInt("id"))
                        .sursa(rs.getString("sursa"))
                        .destinatie(rs.getString("destinatie"))
                        .oraPlecare(rs.getString("ora_plecare"))
                        .oraSosire(rs.getString("ora_sosire"))
                        .zile(rs.getString("zile"))
                        .pret(rs.getInt("pret"))
                        .build();
                return Optional.of(zborModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }


}
