package Tema1;

public class Voturi {
    private String CNPVotant;
    private String CNPCandidat;

    public Voturi(String CNPVotant, String CNPCandidat) {
        setCNPVotant(CNPVotant);
        setCNPCandidat(CNPCandidat);
    }

    public void setCNPVotant(String CNPVotant) {
        this.CNPVotant = CNPVotant;
    }
    public void setCNPCandidat(String CNPCandidat) {
        this.CNPCandidat = CNPCandidat;
    }

    public String getCNPVotant() {
        return CNPVotant;
    }
    public String getCNPCandidat() {
        return CNPCandidat;
    }
}
