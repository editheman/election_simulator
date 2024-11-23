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
        if(validareCNP(CNP))
            this.CNP = CNP;
    }
    public String getCNP() {
        return CNP;
    }

    public void setNume(String nume) {
        if(getCNP() != null)
            this.nume = nume;
    }
    public String getNume() {
        return nume;
    }

    public void setVarsta(int varsta) {
        if(getCNP() != null)
            if(validareVarsta(varsta))
                this.varsta = varsta;
    }
    public int getVarsta() {
        return varsta;
    }

    protected String validareCNP(String CNP) {
        if (CNP.length() != 13) {
            return "EROARE: CNP invalid";
        }

        for (char ch : CNP.toCharArray()) {
            if (!Character.isDigit(ch))
                return "EROARE: CNP invalid";
        }
    }

    protected boolean validareVarsta(int varsta){
        if(varsta < 18){
            System.out.println("EROARE: Vârstă invalidă");
            return false;
        }
        return true;
    }

}
