package Tema1;

public class Voturi {
    private String CNPVotant;
    private String CNPCandidat;

    public Voturi(String CNPVotant, String CNPCandidat) {
        setCNPVotant(CNPVotant);
        setCNPCandidat(CNPCandidat);
    }

    public void setCNPVotant(String CNPCandidat) {
        this.CNPCandidat = CNPCandidat;
    }
    public void setCNPCandidat(String CNPVotant) {
        this.CNPVotant = CNPVotant;
    }

    public String getCNPVotant() {
        return CNPVotant;
    }
    public String getCNPCandidat() {
        return CNPCandidat;
    }
}
