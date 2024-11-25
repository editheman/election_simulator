package Tema1;

import java.util.ArrayList;
import java.util.Comparator;

public class Circumscriptie {
    private String nume;
    private String regiune;
    public ArrayList<Votant> votanti = new ArrayList<>();
    public ArrayList<Voturi> voturi = new ArrayList<>();
    public ArrayList<Frauda> fraude = new ArrayList<>();

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

    public String adaugareVot(String cnpVotant, String cnpCandidat, String numeCandidat, Candidat candidat){
        Votant votant1 = new Votant();

        for(Votant votant : votanti){
            if(votant.getCNP().equals(cnpVotant)){
                for(Voturi voturi : voturi){
                    if(voturi.getCNPVotant().equals(cnpVotant)){
                        adaugareFrauda(cnpVotant, cnpCandidat);
                        return "FRAUDA: Votantul cu CNP-ul " + cnpVotant + " a incercat sa comita o frauda. Votul a fost anulat";
                    }
                }
                for(Frauda frauda : fraude){
                    if(frauda.getCnpVotant().equals(cnpVotant)){
                        adaugareFrauda(cnpVotant, cnpCandidat);
                        return "FRAUDA: Votantul cu CNP-ul " + cnpVotant + " a incercat sa comita o frauda. Votul a fost anulat";
                    }
                }
                if(votant.getNeindemanatic().equals("da"))
                    adaugareFrauda(cnpVotant, cnpCandidat);
                else{
                    Voturi vot = new Voturi(cnpVotant, cnpCandidat);
                    voturi.add(vot);
                    candidat.incNrVoturi();
                }
                votant1 = votant;
            }
        }
        return votant1.getNume() + " a votat pentru " + numeCandidat;
    }
    public void adaugareFrauda(String cnpVotant, String cnpCandidat){
        Frauda frauda = new Frauda(cnpVotant, cnpCandidat);
        fraude.add(frauda);
    }

//    public String raport(String numeCircumscriptie){
//        StringBuilder constructor = new StringBuilder();
//        constructor.append("Raport voturi " + numeCircumscriptie + ":").append("\n");
//        votanti.sort(Comparator.comparing(Votant::getCNP).reversed());
//        for(Votant votant : votanti){
////            System.out.println(votant.getNume() + " " + votant.getCNP() + " " + votant.getVarsta());
//            constructor.append(votant.getNume() + " " + votant.getCNP() + " " + votant.getVarsta() + " - " + votant.).append("\n");
//        }
//        return constructor.toString();
//    }

}
