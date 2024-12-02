package Tema1;

public class Voturi {
//    pentru fiecare vot, pentru a mi face ulterior cautarea mai usoara, ratin 2 cnp uri pentru a identifica
//    de unde vine, si catre cine merge votul
    private String cnpVotant;
    private String cnpCandidat;

    public Voturi(String cnpVotant, String cnpCandidat) {
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
