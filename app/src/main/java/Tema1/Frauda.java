package Tema1;

import java.util.ArrayList;

public class Frauda {
    private String cnpVotant;
    private String cnpCandidat;
    private String numeVotant;

    public Frauda(String cnpVotant, String cnpCandidat, String numeVotant) {
        setCnpVotant(cnpVotant);
        setCnpCandidat(cnpCandidat);
        setNumeVotant(numeVotant);
    }

    public void setCnpVotant(String cnpVotant) {
        this.cnpVotant = cnpVotant;
    }
    public void setCnpCandidat(String cnpCandidat) {
        this.cnpCandidat = cnpCandidat;
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
    public String getCnpCandidat() {
        return cnpCandidat;
    }

}
