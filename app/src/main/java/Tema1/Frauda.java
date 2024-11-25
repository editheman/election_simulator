package Tema1;

import java.util.ArrayList;

public class Frauda {
    private String cnpVotant;
    private String cnpCandidat;

    public Frauda(String cnpVotant, String cnpCandidat) {
        setCnpVotant(cnpVotant);
        setCnpCandidat(cnpCandidat);
    }

    public void setCnpVotant(String cnpVotant) {
        this.cnpVotant = cnpVotant;
    }
    public void setCnpCandidat(String cnpCandidat) {
        this.cnpCandidat = cnpCandidat;
    }

    public String getCnpVotant() {
        return cnpVotant;
    }
    public String getCnpCandidat() {
        return cnpCandidat;
    }

}
