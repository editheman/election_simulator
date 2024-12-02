package Tema1;

import java.util.ArrayList;

public class Frauda {
    private String cnpVotant;
    private String numeVotant;

    public Frauda(String cnpVotant, String numeVotant) {
        setCnpVotant(cnpVotant);
        setNumeVotant(numeVotant);
    }

    public void setCnpVotant(String cnpVotant) {
        this.cnpVotant = cnpVotant;
    }
    public void setNumeVotant(String numeVotant) {
        this.numeVotant = numeVotant;
    }

    public String getNumeVotant(){
        return numeVotant;
    }
    public String getCnpVotant() {
        return cnpVotant;
    }

}
