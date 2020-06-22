package model;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder

public class ZborModel {

    private int id;
    private String sursa;
    private String destinatie;
    private String oraPlecare;
    private String oraSosire;
    private String zile;
    private int pret;

    public ZborModel(int id, String sursa, String destinatie, String oraPlecare, String oraSosire, String zile, int pret) {
        this.id = id;
        this.sursa = sursa;
        this.destinatie = destinatie;
        this.oraPlecare = oraPlecare;
        this.oraSosire = oraSosire;
        this.zile = zile;
        this.pret = pret;
    }

    public ZborModel(String sursa, String destinatie, String oraPlecare, String oraSosire, String zile, int pret) {
        this.sursa = sursa;
        this.destinatie = destinatie;
        this.oraPlecare = oraPlecare;
        this.oraSosire = oraSosire;
        this.zile = zile;
        this.pret = pret;
    }
}
