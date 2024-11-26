package Tema1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Input {
    private ArrayList<Alegeri> alegeri = new ArrayList<>();

    public String startAlegeri(String id) {
        for (Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                if(alegere.getStatut() == -1) {
                    alegere.setStatut(0);
                    return "Au pornit alegerile " + alegere.getNume();
                } else {
                    return "EROARE: Alegerile deja au inceput";
                }
            }
        }
        return "EROARE: Nu exista alegeri cu acest id";
    }

    public String creareAlegeri(String id, String nume) {
        for (Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                return "EROARE: Deja exista alegeri cu id " + id;
            }
        }
        Alegeri alegere = new Alegeri(id, nume);
        alegeri.add(alegere);
        return "S-au creat alegerile " + alegere.getNume();
    }

    public String adaugareCircumscriptie(String id, String numeCircumscriptie, String regiune){
        boolean exista = false;
        for (Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                if(alegere.getStatut() != 0) {
                    return "EROARE: Nu este perioada de votare";
                }
                exista = true;
            }
        }
        if(!exista)
            return "EROARE: Nu exista alegeri cu acest id";
        Alegeri alegere2 = new Alegeri();
        for(Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                alegere2 = alegere;
            }
        }

        return alegere2.adaugareCircumscriptie(numeCircumscriptie, regiune);
    }

    public String eliminareCircumscriptie(String id, String numeCircumscriptie){
        boolean exista = false;
        for (Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                if(alegere.getStatut() != 0) {
                    return "EROARE: Nu este perioada de votare";
                }
                exista = true;
            }
        }
        if(!exista)
            return "EROARE: Nu exista alegeri cu acest id";
        exista = false;
        Circumscriptie circumscriptie1 = new Circumscriptie();
        for(Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                for(Circumscriptie circumscriptie : alegere.circumscriptii){
                    if(circumscriptie.getNume().equals(numeCircumscriptie)) {
                        circumscriptie.eliminareVotanti();
                        circumscriptie1 = circumscriptie;
                        exista = true;
                    }
                }
            }
        }
        for(Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                alegere.circumscriptii.remove(circumscriptie1);
            }
        }
        if(!exista)
            return "EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie;
        return "S-a sters circumscriptia " + numeCircumscriptie;

    }

    public String adaugareCandidat(String id, String CNP, int varsta, String nume){
        if (CNP.length() != 13)
            return "EROARE: CNP invalid";

        for (char ch : CNP.toCharArray()) {
            if (!Character.isDigit(ch))
                return "EROARE: CNP invalid";
        }

        if(varsta < 35)
            return "EROARE: Varsta invalida";

        boolean exista = false;
        for (Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                if(alegere.getStatut() != 0) {
                    return "EROARE: Nu este perioada de votare";
                }
                for(Candidat candidat : alegere.candidati){
                    if(candidat.getCNP().equals(CNP)) {
                        return "EROARE: Candidatul " + candidat.getNume() + " are deja acelasi CNP";
                    }
                }
                exista = true;
            }
        }
        if(!exista)
            return "EROARE: Nu exista alegeri cu acest id";
        for(Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                alegere.adaugareCandidat(nume, varsta, CNP);
            }
        }
        return "S-a adaugat candidatul " + nume;
    }

    public String eliminareCandidat(String id, String CNP){
        boolean exista = false, exista2 = false;
        Candidat candidat2 = new Candidat();
        for (Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                if(alegere.getStatut() != 0) {
                    return "EROARE: Nu este perioada de votare";
                }
                for(Candidat candidat : alegere.candidati){
                    if(candidat.getCNP().equals(CNP)) {
                        candidat2 = candidat;
                        exista = true;
                    }
                }
                if(!exista)
                    return "EROARE: Nu exista un candidat cu CNP-ul " + CNP;
                exista2 = true;
            }
        }
        if(!exista2)
            return "EROARE: Nu exista alegeri cu acest id";
        candidat2.eliminareVoturiCandidat();
        for(Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                alegere.eliminareCandidat(CNP);
            }
        }
        return "S-a sters candidatul " + candidat2.getNume();
    }

    public String adaugareVotant(String id, String numeCircumscriptie, String CNP, int varsta, String neindemanatic, String nume){
        if (CNP.length() != 13)
            return "EROARE: CNP invalid";

        for (char ch : CNP.toCharArray()) {
            if (!Character.isDigit(ch))
                return "EROARE: CNP invalid";
        }

        if(varsta < 18)
            return "EROARE: Varsta invalida";
        boolean exista = false;
        boolean exista2 = false;
        for (Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                if(alegere.getStatut() != 0) {
                    return "EROARE: Nu este perioada de votare";
                }
                for(Circumscriptie circumscriptie : alegere.circumscriptii) {
                    if(circumscriptie.getNume().equals(numeCircumscriptie)) {
                        exista2 = true;
                        for(Votant votant : circumscriptie.votanti) {
                            if(votant.getCNP().equals(CNP)) {
                                return "EROARE: Votantul " + nume + " are deja acelasi CNP";
                            }
                        }
                    }
                }
                exista = true;
            }
        }
        if(!exista2)
            return "EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie;
        if(!exista)
            return "EROARE: Nu exista alegeri cu acest id";
        for(Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                for (Circumscriptie circumscriptie : alegere.circumscriptii) {
                    if(circumscriptie.getNume().equals(numeCircumscriptie)) {
                        circumscriptie.adaugareVotant(nume, varsta, CNP, neindemanatic);
                    }
                }
            }
        }
        return "S-a adaugat votantul " + nume;
    }

    public void printareCandidati(String id){
        boolean exista = false;
        for (Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                if(alegere.getStatut() != 0) {
                    System.out.println("EROARE: Inca nu au inceput alegerile");
                    return;
                }
                alegere.printareCandidati();
                exista = true;
            }
        }
        if(!exista)
            System.out.println("EROARE: Nu exista alegeri cu acest id");
    }

    public void printareVotanti(String id, String numeCircumscriptie){
        boolean exista = false;
        boolean exista2 = false;
        for (Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                exista = true;
                if(alegere.getStatut() != 0) {
                    System.out.println("EROARE: Inca nu au inceput alegerile");
                    return;
                }
                for(Circumscriptie circumscriptie : alegere.circumscriptii) {
                    if(circumscriptie.getNume().equals(numeCircumscriptie)) {
                        exista2 = true;
                        if(circumscriptie.votanti.isEmpty()) {
                            System.out.println("GOL: Nu sunt votanti in " + numeCircumscriptie);
                            return;
                        }
                        System.out.println("Votantii din " + numeCircumscriptie + ":");
                        circumscriptie.printareVotanti();
                    }
                }
            }
        }
        if(!exista){
            System.out.println("EROARE: Nu exista alegeri cu acest id");
        }
        if(!exista2){
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
            return;
        }
    }

    public String votare(String id, String numeCircumscriptie, String cnpVotant, String cnpCandidat) {
        boolean exista = false;
        boolean exista2 = false;
        boolean exista3 = false;
        boolean exista4 = false;
        boolean exista5 = false;
        String raspunsFunctie = "";

        for(Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                exista = true;
                if(alegere.getStatut() != 0)
                    return "EROARE: Nu este perioada de votare";
                for(Circumscriptie circumscriptie : alegere.circumscriptii) {
                    if(circumscriptie.getNume().equals(numeCircumscriptie))
                        exista2 = true;
                    for (Votant votant : circumscriptie.votanti) {
                        if (votant.getCNP().equals(cnpVotant)) {
                            exista4 = true;
                        }
                    }
                }
                if(!exista2)
                    return "EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie;
                if (!exista4)
                    return "EROARE: Nu exista un votant cu CNP-ul " + cnpVotant;
                for(Circumscriptie circumscriptie1 : alegere.circumscriptii) {
                    if(circumscriptie1.getNume().equals(numeCircumscriptie)){
                        for(Candidat candidat : alegere.candidati) {
                            if (candidat.getCNP().equals(cnpCandidat)) {
                                exista3 = true;
                                for (Votant votant : circumscriptie1.votanti) {
                                    if (votant.getCNP().equals(cnpVotant)) {
                                        exista5 = true;
                                        raspunsFunctie = circumscriptie1.adaugareVot(cnpVotant, cnpCandidat, candidat.getNume(), candidat, votant.getNume());
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        if(!exista)
            return "EROARE: Nu exista alegeri cu acest id";
        if(!exista3)
            return "EROARE: Nu exista un candidat cu CNP-ul " + cnpCandidat;
        if(!exista5){
            //un votant incearca sa voteze la alta circumscriptie
            for(Alegeri alegere : alegeri)
                if(alegere.getId().equals(id))
                    for(Circumscriptie circumscriptie1 : alegere.circumscriptii){
                        for(Votant votant : circumscriptie1.votanti){
                            if (votant.getCNP().equals(cnpVotant)) {
                                circumscriptie1.adaugareFrauda(cnpVotant, cnpCandidat, votant.getNume());
                            }
                        }

                    }
            return "FRAUDA: Votantul cu CNP-ul " + cnpVotant + " a incercat sa comita o frauda. Votul a fost anulat";
        }
        return raspunsFunctie;
    }

    public String oprireAlegeri(String id){
        boolean exista = false;
        Alegeri alegere1 = new Alegeri();

        for(Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)){
                exista = true;
                if(alegere.getStatut() != 0)
                    return "EROARE: Nu este perioada de votare";
                alegere.setStatut(1);
                alegere1 = alegere;
            }
        }
        if(!exista)
            return "EROARE: Nu exista alegeri cu acest id";
    return "S-au terminat alegerile " + alegere1.getNume();
    }

    public String raportCircumscriptie(String id, String numeCircumscriptie){
        boolean exista = false;
        boolean exista2 = false;
        String commandOutput = "";

        for(Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)){
                exista = true;
                if(alegere.getStatut() != 1)
                    return "EROARE: Inca nu s-a terminat votarea";
                for(Circumscriptie circumscriptie : alegere.circumscriptii) {
                    if(circumscriptie.getNume().equals(numeCircumscriptie)){
                        exista2 = true;
                        if(circumscriptie.voturi.isEmpty())
                            return "GOL: Lumea nu isi exercita dreptul de vot in " + numeCircumscriptie;
                        commandOutput = circumscriptie.raport(alegere.candidati, numeCircumscriptie);
                    }
                }
            }
        }
        if(!exista)
            return "EROARE: Nu exista alegeri cu acest id";
        if(!exista2)
            return "EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie;
        return commandOutput;
    }

    public String raportNational(String id){
        boolean exista = false;
        boolean exista2 = false;
        StringBuilder constructor = new StringBuilder();

        for(Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)){
                exista = true;
                if(alegere.getStatut() != 1)
                    return "EROARE: Inca nu s-a terminat votarea";
                for(Circumscriptie circumscriptie : alegere.circumscriptii) {
                    if(!circumscriptie.voturi.isEmpty())
                        exista2 = true;
                }
                if(!exista2)
                    return "GOL: Lumea nu isi exercita dreptul de vot in Romania";
                alegere.candidati.sort(Comparator.comparing(Candidat::getNrVoturi).reversed().thenComparing(Candidat::getCNP).reversed());
                constructor.append("Raport voturi Romania:").append("\n");
                for(Candidat candidat : alegere.candidati) {
                    constructor.append(candidat.getNume() + " " + candidat.getCNP() + " - " + candidat.getNrVoturi()).append("\n");
                }
            }
        }
        if(!exista)
            return "EROARE: Nu exista alegeri cu acest id";
        return constructor.toString();
    }

    public String analizaCircumscriptie(String id, String numeCircumscriptie){
        boolean exista = false;
        Candidat candidatCastigatorCircumscriptie = new Candidat();
        int nrVoturiNationale = 0;
        int nrVoturiCircumscriptie = 0;

        for(Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)){
                exista = true;
                if(alegere.getStatut() != 1)
                    return "EROARE: Inca nu s-a terminat votarea";
                for(Candidat candidat : alegere.candidati) {
                    nrVoturiNationale = nrVoturiNationale + candidat.getNrVoturi();
                }
                for(Circumscriptie circumscriptie : alegere.circumscriptii) {
                    if(circumscriptie.getNume().equals(numeCircumscriptie)){
//                        exista2 = true;
                        nrVoturiCircumscriptie = circumscriptie.voturi.size();
                        candidatCastigatorCircumscriptie = circumscriptie.raportCircumscriptie(alegere.candidati);
                    }
                }
            }
        }
        if(!exista)
            return "EROARE: Nu exista alegeri cu acest id";
        if(nrVoturiNationale == 0)
            return "GOL: Lumea nu isi exercita dreptul de vot in " + numeCircumscriptie;
        int procentaj = (int)(((double)nrVoturiCircumscriptie / nrVoturiNationale) * 100);
        int procentajCircumscriptie = (candidatCastigatorCircumscriptie.getNrVoturi() / nrVoturiCircumscriptie) * 100;

        return "In " + numeCircumscriptie + " au fost "+ nrVoturiCircumscriptie + " voturi din " + nrVoturiNationale + ". Adica " + procentaj + "%. Cele mai multe voturi au fost stranse de " + candidatCastigatorCircumscriptie.getCNP() + " " + candidatCastigatorCircumscriptie.getNume() + ". Acestea constituie " + procentajCircumscriptie + "% din voturile circumscriptiei.";

    }

    public String analizaNationala(String id){
        ArrayList<Regiune> regiuni = new ArrayList<>();
        boolean exista = false;
        boolean exista2 = false;


        ArrayList<Candidat> candidatiCircumscriptie = new ArrayList<>();

        for(Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)){
                exista = true;
                if(alegere.getStatut() != 1)
                    return "EROARE: Inca nu s-a terminat votarea";
                for(Circumscriptie circumscriptie : alegere.circumscriptii) {
                    exista2 = false;
                    String numeRegiune = circumscriptie.getRegiune().trim();
                    candidatiCircumscriptie.clear();
                    candidatiCircumscriptie = circumscriptie.numarVoturiPeCircumscriptie(alegere.candidati);


//                    System.out.println("Initial:" + numeRegiune + "\n");
                    for(Regiune regiune : regiuni) {
                        if (regiune.getNume().equals(numeRegiune)) {
//                            System.out.println(regiune.getNume());
//                            for(Regiune regiune2 : regiuni) {
//                                System.out.println(regiune2.getNume());
//                                for(Candidat candidat : regiune2.candidatiRegiune) {
//                                    System.out.println(candidat.getNume() + " " + candidat.getNrVoturi());
//                                }
//                                System.out.println("\n");
//                            }
                            for (Candidat candidat : regiune.candidatiRegiune) {
                                for(Candidat candidat2 : candidatiCircumscriptie) {
                                    if(candidat2.getCNP().equals(candidat.getCNP())) {
                                        candidat.setNrVoturi(candidat.getNrVoturi() + candidat2.getNrVoturi());
                                    }
                                }
                            }
                            exista2 = true;
                        }
                    }
//                    System.out.println("Copie:\n");
//                    for(Candidat candidat : candidatiCircumscriptie) {
//                        System.out.println(candidat.getNume() + " " + candidat.getNrVoturi());
//                    }
                    if(!exista2){
//                        System.out.println("Yes");
                        Regiune regiuneNoua = new Regiune();
                        regiuneNoua.setNume(numeRegiune.trim());
                        for(Candidat candidat : candidatiCircumscriptie) {
                            Candidat candidat1 = new Candidat(candidat.getNume(), candidat.getVarsta(), candidat.getCNP());
                            candidat1.setNrVoturi(candidat.getNrVoturi());
                            regiuneNoua.candidatiRegiune.add(candidat1);
                        }
//                        regiuneNoua.candidatiRegiune.addAll(candidatiCircumscriptie);
                        regiuni.add(regiuneNoua);
//
//                        for(Candidat candidat : regiuneNoua.candidatiRegiune) {
//                            System.out.println(candidat.getNume() + " " + candidat.getNrVoturi());
//
//                        }
                    }
                }
            }
        }
        if(!exista)
            return "EROARE: Nu exista alegeri cu acest id";

//        System.out.println("After:");
//        for(Regiune regiune : regiuni) {
//            System.out.println(regiune.getNume());
//            for(Candidat candidat : regiune.candidatiRegiune) {
//                System.out.println(candidat.getNume() + " " + candidat.getNrVoturi());
//            }
//        }

        StringBuilder constructor = new StringBuilder();
        int numarVoturiNationale = numarVoturiNationale(regiuni);
        int numarVoturiRegiune;
        int procentajNational;
        int procentajRegiune;
        Candidat candidat = new Candidat();

        if(numarVoturiNationale == 0)
            return "GOL: Lumea nu isi exercita dreptul de vot in Romania.";

        constructor.append("In Romania au fost " + numarVoturiNationale(regiuni) + " voturi.\n");
        regiuni.sort(Comparator.comparing(Regiune::getNume).reversed());
        for(Regiune regiune : regiuni) {
            candidat = regiune.castigator();
            numarVoturiRegiune = regiune.numarVoturiRegiune();
//            System.out.println(candidat.getNrVoturi() + " " +numarVoturiRegiune+ " " + numarVoturiNationale +"ciocan");
            procentajRegiune = (int)((double)((double)candidat.getNrVoturi() / (double)numarVoturiRegiune) * 100);
            procentajNational = (int)((double)((double)numarVoturiRegiune / (double)numarVoturiNationale) * 100);

            constructor.append("In " + regiune.getNume().trim() + " au fost " + numarVoturiRegiune + " voturi din " + numarVoturiNationale + ". Adica " + procentajNational + "%. Cele mai multe voturi au fost stranse de " + candidat.getCNP() + " " + candidat.getNume() + ". Acestea constituie " + procentajRegiune + "% din voturile regiunii.").append("\n");
        }
        return constructor.toString();
    }

//    public String anal(String id){
//        boolean exista = false;
//
//    }

    public int numarVoturiNationale(ArrayList<Regiune> regiuni){
        int suma = 0;
        for(Regiune regiune : regiuni) {
            suma = suma + regiune.numarVoturiRegiune();
        }
        return suma;
    }

    public String raportFraude(String id) {
        boolean exista = false;
        boolean exista2 = false;
        StringBuilder constructor = new StringBuilder();

        constructor.append("Fraude comise:").append("\n");

        for(Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)){
                exista = true;
                if(alegere.getStatut() != 1)
                    return "EROARE: Inca nu s-a terminat votarea";
                for(Circumscriptie circumscriptie : alegere.circumscriptii) {
                    if(!circumscriptie.fraude.isEmpty()){
                        exista2 = true;
                        Collections.reverse(circumscriptie.fraude);
                        for(Frauda frauda : circumscriptie.fraude){
                            constructor.append("In " + circumscriptie.getNume() + ": " + frauda.getCnpVotant() + " " + frauda.getNumeVotant()).append("\n");
                        }
                    }
                }
            }
        }
        if(!exista)
            return "EROARE: Nu exista alegeri cu acest id";
        if(!exista2)
            return "GOL: Romanii sunt cinstiti";
        return constructor.toString();
    }

    public String eliminareAlegeri(String id) {
        boolean exista = false;
        Alegeri alegere1 = new Alegeri();

        for(Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)){
                exista = true;
                alegere1 = alegere;
            }
        }
        if(!exista)
            return "EROARE: Nu exista alegeri cu acest id";
        alegeri.remove(alegere1);
        return "S-au sters alegerile " + alegere1.getNume();
    }

    public String listareAlegeri(){
        if(alegeri.isEmpty())
            return "GOL: Nu sunt alegeri";
        StringBuilder constructor = new StringBuilder();
        constructor.append("Alegeri:\n");
        for(Alegeri alegere : alegeri) {
            constructor.append(alegere.getId() + " " + alegere.getNume()).append("\n");
        }
        return constructor.toString();
    }

}
