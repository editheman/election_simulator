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
//    printareVotanti: afisez fiecare votant dintr o circumscriptie
    public void printareVotanti(){
        for(Votant votant : votanti){
            System.out.println(votant.getNume() + " " + votant.getCnp() + " " + votant.getVarsta());
        }
    }
//    adaugareVot: functia care adauga votul unui votant si verifica date despre el
    public String adaugareVot(String cnpVotant, String cnpCandidat, String numeCandidat, Candidat candidat, String numeVotant){
        Votant votant1 = new Votant();
        for(Votant votant : votanti){
            if(votant.getCnp().equals(cnpVotant)){
                for(Voturi vot : voturi){
//                    daca se gaseste deja in lista, inseamna ca acesta a incercat sa comita o frauda
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
//    adaugareFrauda: se initializeaza un nou element de tip frauda, pe care il adaugam in lista
    public void adaugareFrauda(String cnpVotant, String cnpCandidat, String numeVotant){
        Frauda frauda = new Frauda(cnpVotant, numeVotant);
        fraude.add(frauda);
    }
//   raport:  pentru o anumita circumscriptie, creez un vector de candidati in care fiecare element are un atribut numit
//    nrVoturi. voi trece prin lista de voturi de la circumscriptia respectiva, si voi incrementa numarul voturilor
//    unui candidat in momentul in care gasesc un vot pentru acesta, identificand votul cu candidatul dupa cnp
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
//    raportCircumscriptie: pentru a putea numara voturile pentru o anumita circumscriptie, creez o copie a vectorului de candidati,
//    unde initializez numarul de voturi cu 0 si il incrementez de fiecare data cand dau de un vot pentru acel candidat
//    dupa sortarea inversa dupa nr de voturi, primul candidat din lista va fi cel cu cele mai multe voturi, adica castigatorul
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
//    numarVoturiPeCircumscriptie:functia imi returneaza un vector in care fiecare candidat are nr de voturi egal cu cel pe care l a obtinut in circumscriptia
//    respectiva
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
