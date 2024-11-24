package Tema1;


import java.util.ArrayList;

abstract class Persoana {
    private String CNP;
    private String nume;
    private int varsta;

    public static ArrayList<Persoana> persoane;
    public Persoana() {

    }
    public Persoana(String nume, int varsta, String CNP) {
        setCNP(CNP);
        setNume(nume);
        setVarsta(varsta);
    }

    public void setCNP(String CNP){
        this.CNP = CNP;
    }
    public String getCNP() {
        return CNP;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getNume() {
        return nume;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }
    public int getVarsta() {
        return varsta;
    }

}
