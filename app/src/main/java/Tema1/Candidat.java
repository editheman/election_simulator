package Tema1;
import java.util.ArrayList;

public class Candidat extends Persoana {

//    private int nrVoturi = 0;
    public ArrayList<Voturi> voturi = new ArrayList<>();

    public Candidat() {
    }

    public Candidat(String nume, int varsta, String CNP) {
        super(nume, varsta, CNP);
    }
    public void eliminareVoturiCandidat() {
        voturi.clear();
    }

//    public void incNrVoturi() {
//        nrVoturi++;
//    }
//    public int getNrVoturi() {
//        return nrVoturi;
//    }
}
