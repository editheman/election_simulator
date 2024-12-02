package Tema1;
import java.util.ArrayList;

public class Candidat extends Persoana {

    private int nrVoturi = 0;
    public ArrayList<Voturi> voturi = new ArrayList<>();

    public Candidat() {
    }

    public Candidat(String nume, int varsta, String cnp) {
        super(nume, varsta, cnp);
    }
    public void eliminareVoturiCandidat() {
        voturi.clear();
    }

    public void incNrVoturi(int nr) {
        nrVoturi = nrVoturi + nr;
    }
    public void setNrVoturi(int nrVoturi) {
        this.nrVoturi = nrVoturi;
    }
    public int getNrVoturi() {
        return nrVoturi;
    }
}
