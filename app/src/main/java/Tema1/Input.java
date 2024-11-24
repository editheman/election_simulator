package Tema1;

import java.util.ArrayList;

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
                if(alegere.getStatut() != 0)
                    System.out.println("EROARE: Inca nu au inceput alegerile");
                alegere.printareCandidati();
                exista = true;
            }
        }
        if(!exista)
            System.out.println("EROARE: Nu exista alegeri cu acest id");
    }


}
