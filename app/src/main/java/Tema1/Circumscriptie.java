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

    public void adaugareVotant(String nume, int varsta, String cnp, String neindemanatic){
        Votant votant = new Votant(nume, varsta, cnp, neindemanatic, false);
        votanti.add(votant);
    }
    public void eliminareVotanti(){
        votanti.clear();
    }

    public void printareVotanti(){
        for(Votant votant : votanti){
            System.out.println(votant.getNume() + " " + votant.getCnp() + " " + votant.getVarsta());
        }
    }

    public String adaugareVot(String cnpVotant, String cnpCandidat, String numeCandidat, Candidat candidat, String numeVotant){
        Votant votant1 = new Votant();
        for(Votant votant : votanti){
            if(votant.getCnp().equals(cnpVotant)){
                for(Voturi vot : voturi){
                    if(vot.getCnpVotant().equals(cnpVotant)){
                        adaugareFrauda(cnpVotant, cnpCandidat, numeVotant);
                        return "FRAUDA: Votantul cu CNP-ul " + cnpVotant + " a incercat sa comita o frauda. Votul a fost anulat";
                    }
                }
                for(Frauda frauda : fraude){
                    if(frauda.getCnpVotant().equals(cnpVotant)){
                        return "FRAUDA: Votantul cu CNP-ul " + cnpVotant + " a incercat sa comita o frauda. Votul a fost anulat";
                    }
                }
                if(votant.getaVotat()){
                    adaugareFrauda(cnpVotant, cnpCandidat, numeVotant);
                    return "FRAUDA: Votantul cu CNP-ul " + cnpVotant + " a incercat sa comita o frauda. Votul a fost anulat";
                }
                if(votant.getNeindemanatic().equals("nu")) {
                    Voturi vot = new Voturi(cnpVotant, cnpCandidat);
                    voturi.add(vot);
                    candidat.incNrVoturi(1);
                }
                votant.setaVotat(true);
                votant1 = votant;
            }
        }
        return votant1.getNume() + " a votat pentru " + numeCandidat;
    }

    public void adaugareFrauda(String cnpVotant, String cnpCandidat, String numeVotant){
        Frauda frauda = new Frauda(cnpVotant, cnpCandidat, numeVotant);
        fraude.add(frauda);
    }

    public String raport(ArrayList<Candidat> candidati, String numeCircumscriptie){
        ArrayList<Candidat> candidatiCircumscriptie = new ArrayList<>();
        StringBuilder constructor = new StringBuilder();
        candidatiCircumscriptie.addAll(candidati);

        for(Candidat candidat : candidatiCircumscriptie){
            candidat.setNrVoturi(0);
        }

        for(Voturi vot : voturi){
            for(Candidat candidat : candidatiCircumscriptie){
                if(vot.getCnpCandidat().equals(candidat.getCnp()))
                    candidat.incNrVoturi(1);
            }
        }
        constructor.append("Raport voturi " + numeCircumscriptie + ":").append("\n");
        candidatiCircumscriptie.sort(Comparator.comparing(Candidat::getNrVoturi).reversed().thenComparing(Candidat::getCnp).reversed());
        for(Candidat candidat : candidatiCircumscriptie){
            constructor.append(candidat.getNume() + " " + candidat.getCnp() + " - " + candidat.getNrVoturi()).append("\n");
        }
        return constructor.toString();
    }

    public Candidat raportCircumscriptie(ArrayList<Candidat> candidati){
        ArrayList<Candidat> candidatiCircumscriptie = new ArrayList<>();
        candidatiCircumscriptie.addAll(candidati);

        for(Candidat candidat : candidatiCircumscriptie){
            candidat.setNrVoturi(0);
        }

        for(Candidat candidat : candidatiCircumscriptie){
            for(Voturi vot : voturi){
                if(vot.getCnpCandidat().equals(candidat.getCnp()))
                    candidat.incNrVoturi(1);
            }
        }
        candidatiCircumscriptie.sort(Comparator.comparing(Candidat::getNrVoturi).reversed());
        return candidatiCircumscriptie.get(0);
    }

    public ArrayList<Candidat> numarVoturiPeCircumscriptie(ArrayList<Candidat> candidati){
        ArrayList<Candidat> candidatiCircumscriptie = new ArrayList<>();

        for(Candidat candidat : candidati){
            Candidat candidat1 = new Candidat(candidat.getNume(), candidat.getVarsta(), candidat.getCnp());
            candidatiCircumscriptie.add(candidat1);
        }


        for(Candidat candidat : candidatiCircumscriptie){
            candidat.setNrVoturi(0);
        }

        for(Candidat candidat : candidatiCircumscriptie){
            for(Voturi vot : voturi){
                if(vot.getCnpCandidat().equals(candidat.getCnp()))
                    candidat.incNrVoturi(1);
            }
        }
        candidatiCircumscriptie.sort(Comparator.comparing(Candidat::getNume));

        return candidatiCircumscriptie;
    }

}
