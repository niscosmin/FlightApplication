package model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class UserModel {


    private int id;
    private String userNume;
    private String password;
    private String email;

    public UserModel(int id, String userNume, String password, String email) {
        this.id = id;
        this.userNume = userNume;
        this.password = password;
        this.email = email;
    }

    public UserModel(String userNume, String password, String email) {
        this.userNume = userNume;
        this.password = password;
        this.email = email;
    }

    public UserModel(String userNume, String email) {
        this.userNume = userNume;
        this.email = email;
    }

    public UserModel(String userNume) {
        this.userNume = userNume;
    }
}


