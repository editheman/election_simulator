package Tema1;


import java.util.ArrayList;

abstract class Persoana {
    private String cnp;
    private String nume;
    private int varsta;

    public static ArrayList<Persoana> persoane;
    public Persoana() {

    }
    public Persoana(String nume, int varsta, String cnp) {
        setCnp(cnp);
        setNume(nume);
        setVarsta(varsta);
    }

    public void setCnp(String cnp){
        this.cnp = cnp;
    }
    public String getCnp() {
        return cnp;
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
