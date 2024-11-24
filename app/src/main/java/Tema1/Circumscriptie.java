package Tema1;

import java.util.ArrayList;

public class Circumscriptie {
    private String nume;
    private String regiune;
    public ArrayList<Votant> votanti = new ArrayList<>();


    public Circumscriptie(String nume, String regiune) {
        setNume(nume);
        setRegiune(regiune);
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getNume() {
        return nume;
    }
    public void setRegiune(String regiune) {
        this.regiune = regiune;
    }
    public String getRegiune() {
        return regiune;
    }

    public void eliminareVotanti(){
        votanti.clear();
    }
}
