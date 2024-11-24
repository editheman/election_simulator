package Tema1;

import java.util.ArrayList;

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
                return "EROARE: Deja există o circumscripție cu numele " + circumscriptie.getNume();
            }
        }
        Circumscriptie circumscriptie = new Circumscriptie(numeCircumscriptie, regiune);
        circumscriptii.add(circumscriptie);
        return "S-a adăugat circumscripția " + circumscriptie.getNume();
    }

    public void golireCircumscriptii(){
        circumscriptii.clear();
    }

    public void adaugareCandidat(String CNP, int varsta, String nume){
        Candidat candidat = new Candidat(CNP, varsta, nume);
        candidati.add(candidat);
    }
    public void eliminareCandidat(String CNP){
        for(Candidat candidat : candidati){
            if(candidat.getCNP().equals(CNP)){
                candidati.remove(candidat);
            }
        }
    }
}
