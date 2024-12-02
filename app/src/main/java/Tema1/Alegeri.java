package Tema1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Alegeri {
    private String id;
    private String nume;
    private int statut = -1;

    public ArrayList<Circumscriptie> circumscriptii = new ArrayList<>();
    public ArrayList<Candidat> candidati = new ArrayList<>();

    public Alegeri(String id, String nume) {
        setId(id);
        setNume(nume);
    }
    public Alegeri() {}

    public void setId(String id) {
        this.id = id;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getId() {
        return id;
    }
    public String getNume() {
        return nume;
    }

    public int getStatut() {
        return statut;
    }

    public String adaugareCircumscriptie(String numeCircumscriptie, String regiune){
        for(Circumscriptie circumscriptie : circumscriptii){
            if(circumscriptie.getNume().equals(numeCircumscriptie)){
                return "EROARE: Deja exista o circumscriptie cu numele " + circumscriptie.getNume();
            }
        }
        Circumscriptie circumscriptie = new Circumscriptie(numeCircumscriptie, regiune);
        circumscriptii.add(circumscriptie);
        return "S-a adaugat circumscriptia " + circumscriptie.getNume();
    }

    public void golireCircumscriptii(){
        circumscriptii.clear();
    }

    public void adaugareCandidat(String nume, int varsta, String cnp){
        Candidat candidat = new Candidat(nume, varsta, cnp);
        candidati.add(candidat);
    }
    public void eliminareCandidat(String cnp){
        Candidat candidat2 = new Candidat();
        for(Candidat candidat : candidati){
            if(candidat.getCnp().equals(cnp)){
                candidat2 = candidat;
            }
        }
        candidati.remove(candidat2);
    }

    public void printareCandidati(){
        if(candidati.isEmpty()){
            System.out.println("GOL: Nu sunt candidati");
            return;
        }
        candidati.sort(Comparator.comparing(Candidat::getCnp));
        System.out.println("Candidatii:");
        for(Candidat candidat : candidati){
            System.out.println(candidat.getNume() + " " + candidat.getCnp() + " " + candidat.getVarsta());
        }
    }
}
