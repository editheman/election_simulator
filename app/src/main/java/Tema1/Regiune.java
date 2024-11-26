package Tema1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Regiune {
    private String nume;
    public ArrayList<Candidat> candidatiRegiune = new ArrayList<>();

    public int numarVoturiRegiune(){
        int suma = 0;
        for(Candidat candidat : candidatiRegiune){
            suma = suma + candidat.getNrVoturi();
        }
        return suma;
    }

    public Candidat castigator(){
        candidatiRegiune.sort(Comparator.comparing(Candidat::getNrVoturi).reversed());
        return candidatiRegiune.get(0);
    }

    public void setNume(String nume){
        this.nume = nume;
    }
    public String getNume(){
        return this.nume;
    }
}
