package Tema1;

import java.util.ArrayList;

public class Circumscriptie {
    private String nume;
    private String regiune;
    public ArrayList<Votant> votanti = new ArrayList<>();

    public Circumscriptie() {}
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

    public void adaugareVotant(String nume, int varsta, String CNP, String neindemanatic){
        Votant votant = new Votant(nume, varsta, CNP, neindemanatic);
        votanti.add(votant);
    }
    public void eliminareVotanti(){
        votanti.clear();
    }

    public void printareVotanti(){
        for(Votant votant : votanti){
            System.out.println(votant.getNume() + " " + votant.getCNP() + " " + votant.getVarsta());
        }
    }

}
