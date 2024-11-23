package Tema1;
import java.util.ArrayList;

public class Candidat extends Persoana {

    private int nrVoturi = 0;

    public Candidat() {
        ArrayList<Candidat> candidati = new ArrayList<>();
    }

    public Candidat(String nume, int varsta, String CNP) {
        super(nume, varsta, CNP);
    }

    public void incNrVoturi() {
        nrVoturi++;
    }
    public int getNrVoturi() {
        return nrVoturi;
    }
}
