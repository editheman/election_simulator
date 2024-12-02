package Tema1;
import java.util.ArrayList;

public class Candidat extends Persoana {

    private int nrVoturi = 0;

    public Candidat() {
    }

    public Candidat(String nume, int varsta, String cnp) {
        super(nume, varsta, cnp);
    }
//    incNrVoturi: incrementeaza numarul de voturi ale unui canidat
    public void incNrVoturi(int nr) {
        nrVoturi = nrVoturi + nr;
    }
    public void setNrVoturi(int nrVoturi) {
        this.nrVoturi = nrVoturi;
    }
//    getNrVoturi: returneaza numarul de voturi obtinut de un anumit candidat
    public int getNrVoturi() {
        return nrVoturi;
    }
}
