package Tema1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Input {
//    imi initializez un vector de alegerei pentru a pute incepe rularea programului
    private ArrayList<Alegeri> alegeri = new ArrayList<>();
//    startAlegeri :functia testeaza statusul in care se afla alegerea respectiva, si returneaza eroarea aferenta
    public String startAlegeri(String id) {
        for (Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
//                singurul moment in care pot sa pornesc alegerile
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
//    creareAlegeri : creez o alegere noua, prima data cautand daca mai exista alta cu alelasi id. In acest caz, nu o creez
//    pe cea noua si returnez eroarea aferenta
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

//  adaugareCircumscriptie : la fel ca la alegeri, testez daca mai exista vreo circumscriptie cu acelasi id. In caz afirmativ, returnez eroare,
//    altfel creez circumscriptia. pe langa asta, testez ca alegerile sa fie in desfasurare, pentru ca altfel nu pot
//    adauga alta circumscriptie
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

//    eliminareCircumscriptie: caut in vectorul de alegeri si identific dula id alegerea corecta. daca alegerea este in desfasurare,
//    caut cutcumscriptia cu pricina in vectorul de circumscriptii iar daca exista o elimin. in caz contrar
//    in oricare din cele enuntate anterior, returnez o erare aferenta
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
//  adaugareCandidat : prima data trebuie sa testez daca datele introduse sunt corecte (cnp care sa respecte anumite criterii, sa aiba o anumita varsta)
//    ulterior, caut alegerile, circumscriptia, si verific sa nu mai fie niciun candidat care sa aibe acelasi cnp
    public String adaugareCandidat(String id, String cnp, int varsta, String nume){
        if (cnp.length() != 13)
            return "EROARE: CNP invalid";

        for (char ch : cnp.toCharArray()) {
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
                    if(candidat.getCnp().equals(cnp)) {
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
                alegere.adaugareCandidat(nume, varsta, cnp);
            }
        }
        return "S-a adaugat candidatul " + nume;
    }
//    eliminareCandidat : la eliminarea candidatului, caut alegerea corecta, circumscriptia, perioada de votare sa fie una aferenta,
//    iar apoi candidatul daca exista in lista de candidati. in acest caz, il elimin, in caz contrar returnez eroarea corespunzatoare
    public String eliminareCandidat(String id, String cnp){
        boolean exista = false, exista2 = false;
        Candidat candidat2 = new Candidat();
        for (Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                if(alegere.getStatut() != 0) {
                    return "EROARE: Nu este perioada de votare";
                }
                for(Candidat candidat : alegere.candidati){
                    if(candidat.getCnp().equals(cnp)) {
                        candidat2 = candidat;
                        exista = true;
                    }
                }
                if(!exista)
                    return "EROARE: Nu exista un candidat cu CNP-ul " + cnp;
                exista2 = true;
            }
        }
        if(!exista2)
            return "EROARE: Nu exista alegeri cu acest id";
        for(Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                alegere.eliminareCandidat(cnp);
            }
        }
        return "S-a sters candidatul " + candidat2.getNume();
    }
//    adaugareVotant : pentru adaugarea votantului, verific ca datele introduse sa indeplineasca anumimte criterii (varsta minima, cnp de o anumita marime
//    si cu caracteristicile specifice). verific ca alegerea sa existe si sa fie in stadiul corect, circumscriptia sa existe, si ca votantul
//    sa nu impartaseasca acelasi cnp cu alta persoana. in caz contrar, returnez eroarea caracteristica pentru problema intampinata
    public String adaugareVotant(String id, String numeCircumscriptie, String cnp, int varsta, String neindemanatic, String nume){
        if (cnp.length() != 13)
            return "EROARE: CNP invalid";

        for (char ch : cnp.toCharArray()) {
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
                            if(votant.getCnp().equals(cnp)) {
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
                        circumscriptie.adaugareVotant(nume, varsta, cnp, neindemanatic);
                    }
                }
            }
        }
        return "S-a adaugat votantul " + nume;
    }
//    printareCandidati : verific daca exista alegera si starea ei, si apoi apelez functia printare candidati, ce va afisa candidatii, cu datele in ordinea
//    precizata in enunt
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
//    printareVotanti : pentru printarea votantilor, caut alegerea corecta, circumscriptia corecta, si apelez functia  printareVotanti care
//    imi va printa fiecare votant
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
//     votare : in momentul in care un votant este neindemanatic, votul lui nu va fi luat in considerare si va fi trecut direct la frauda
    public String votare(String id, String numeCircumscriptie, String cnpVotant, String cnpCandidat) {
        boolean exista = false;
        boolean exista2 = false;
        boolean exista3 = false;
        boolean exista4 = false;
        boolean exista5 = false;
        String raspunsFunctie = "";
//        caut alegerea data ca argument
        for(Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                exista = true;
//                verific statusul ei
                if(alegere.getStatut() != 0)
                    return "EROARE: Nu este perioada de votare";
//                caut circumscriptia corecta
                for(Circumscriptie circumscriptie : alegere.circumscriptii) {
                    if(circumscriptie.getNume().equals(numeCircumscriptie))
                        exista2 = true;
//                    verific daca inainte de a vota, votantul a fost inscris in circumscriptie
                    for (Votant votant : circumscriptie.votanti) {
                        if (votant.getCnp().equals(cnpVotant)) {
                            exista4 = true;
                        }
                    }
                }
//                nu exista circumscriptia
                if(!exista2)
                    return "EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie;
//                 votantul nu este inregistrat
                if (!exista4)
                    return "EROARE: Nu exista un votant cu CNP-ul " + cnpVotant;
                for(Circumscriptie circumscriptie1 : alegere.circumscriptii) {
                    if(circumscriptie1.getNume().equals(numeCircumscriptie)){
                        for(Candidat candidat : alegere.candidati) {
                            if (candidat.getCnp().equals(cnpCandidat)) {
                                exista3 = true;
                                for (Votant votant : circumscriptie1.votanti) {
                                    if (votant.getCnp().equals(cnpVotant)) {
                                        exista5 = true;
//                                        apelam functia care salveaza datele votantului, si ii asteptam raspunsul
                                        raspunsFunctie = circumscriptie1.adaugareVot(cnpVotant, cnpCandidat, candidat.getNume(), candidat, votant.getNume());
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
//        alegeri invalide
        if(!exista)
            return "EROARE: Nu exista alegeri cu acest id";
//        candidat inexistent
        if(!exista3)
            return "EROARE: Nu exista un candidat cu CNP-ul " + cnpCandidat;
        //un votant incearca sa voteze la alta circumscriptie
        if(!exista5){
//            votantul a fost descoperit in alta circumscriptie ca fiind inscris, si incearca sa voteze la una diferita
            for(Alegeri alegere : alegeri)
                if(alegere.getId().equals(id))
                    for(Circumscriptie circumscriptie1 : alegere.circumscriptii){
                        for(Votant votant : circumscriptie1.votanti){
                            if (votant.getCnp().equals(cnpVotant)) {
//                                il declaram ca fruda
                                circumscriptie1.adaugareFrauda(cnpVotant, cnpCandidat, votant.getNume());
                            }
                        }

                    }
            return "FRAUDA: Votantul cu CNP-ul " + cnpVotant + " a incercat sa comita o frauda. Votul a fost anulat";
        }
        return raspunsFunctie;
    }
//    oprireAlegeri : verificam daca exista alegerile si au statusul corect, iar apoi le schimbam statusul
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
//    raportCircumscriptie : validez existenta alegerii, statusul corect, rai apoi afisez un raport pentru circumscriptia data
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
//                        apelez functia care mi va afisa si calcula numarul de voturi pentru fiecare candidat
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
//    raportNational : validez existenta alegerii, statusul corect, rai apoi afisez un raport pe tara
//    fiecare candidat, are un atribut de tip int numit numarVoturi, care se incrementeaza de fiecare data cand cineva voteaza acea persoana
//    indiferent din ce circumscriptie, creeind un numar de voturi national
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
//                sortez candidatii dupa numarul de voturi, iar in caz de egalitate, dupa cnp in ordine (in ordine descrescatoare)
                alegere.candidati.sort(Comparator.comparing(Candidat::getNrVoturi).reversed().thenComparing(Candidat::getCnp).reversed());
                constructor.append("Raport voturi Romania:").append("\n");
//                dupa sortare, creed un string auxiliar in care adaug fiecare candidat cu datele sale, pentru a le putea returna
//                functiei main
                for(Candidat candidat : alegere.candidati) {
                    constructor.append(candidat.getNume() + " " + candidat.getCnp() + " - " + candidat.getNrVoturi()).append("\n");
                }
            }
        }
        if(!exista)
            return "EROARE: Nu exista alegeri cu acest id";
        return constructor.toString();
    }
//    analizaCircumscriptie : dupa verificarea axistentei alegerii, statusul ei si existenta circumscriptiiei, apelez functia care mi va calcula
//    numarul de voturi si mi va returna candidatul castigator
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

        return "In " + numeCircumscriptie + " au fost "+ nrVoturiCircumscriptie + " voturi din " + nrVoturiNationale + ". Adica " + procentaj + "%. Cele mai multe voturi au fost stranse de " + candidatCastigatorCircumscriptie.getCnp() + " " + candidatCastigatorCircumscriptie.getNume() + ". Acestea constituie " + procentajCircumscriptie + "% din voturile circumscriptiei.";

    }
//    analizaNationala : pentru fiecare regiune in parte, trec prin vectorul de ciscumscriptii, iar daca gasesc o circumscriptie intr o regiune pe care
//    inca nu am adaugat o in vectorul de regiuni, initializez regiunea si copiez candidatii alaturi de numarul nor de voturi pe circumscriptia
//    respectiva. in cazul in care dau de o regiune pe care o am deja in vectorul de regiuni, doar calculez numarul de voturi pentru fiecare
//    candidat in circumscriptia respectiva, si adaug la numarul de voturi pa care le aveau respectivii candidati deja in regiunea respectiva
//
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

                    for(Regiune regiune : regiuni) {
                        if (regiune.getNume().equals(numeRegiune)) {
                            for (Candidat candidat : regiune.candidatiRegiune) {
                                for(Candidat candidat2 : candidatiCircumscriptie) {
                                    if(candidat2.getCnp().equals(candidat.getCnp())) {
//                                        incrementez numarul de voturi cu cel din noua circumscriptie gasita in aceeasi regiune
                                        candidat.setNrVoturi(candidat.getNrVoturi() + candidat2.getNrVoturi());
                                    }
                                }
                            }
                            exista2 = true;
                        }
                    }
                    if(!exista2){
                        Regiune regiuneNoua = new Regiune();
                        regiuneNoua.setNume(numeRegiune.trim());
                        for(Candidat candidat : candidatiCircumscriptie) {
                            Candidat candidat1 = new Candidat(candidat.getNume(), candidat.getVarsta(), candidat.getCnp());
                            candidat1.setNrVoturi(candidat.getNrVoturi());
                            regiuneNoua.candidatiRegiune.add(candidat1);
                        }
                        regiuni.add(regiuneNoua);
                    }
                }
            }
        }
        if(!exista)
            return "EROARE: Nu exista alegeri cu acest id";

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
            procentajRegiune = (int)((double)((double)candidat.getNrVoturi() / (double)numarVoturiRegiune) * 100);
            procentajNational = (int)((double)((double)numarVoturiRegiune / (double)numarVoturiNationale) * 100);

            constructor.append("In " + regiune.getNume().trim() + " au fost " + numarVoturiRegiune + " voturi din " + numarVoturiNationale + ". Adica " + procentajNational + "%. Cele mai multe voturi au fost stranse de " + candidat.getCnp() + " " + candidat.getNume() + ". Acestea constituie " + procentajRegiune + "% din voturile regiunii.").append("\n");
        }
        return constructor.toString();
    }
//    numarVoturiNationale : calculez suma voturilor nationale
    public int numarVoturiNationale(ArrayList<Regiune> regiuni){
        int suma = 0;
        for(Regiune regiune : regiuni) {
            suma = suma + regiune.numarVoturiRegiune();
        }
        return suma;
    }
//    raportFraude : pentru fiecare circumscriptie, afisez vectorul de fraude care s au comis. daca intampin o neregula, returnez eraorea aferenta
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
//    eliminareAlegeri : caut daca alegerea exista in vectorul de alegeri, apoi o elimin, astfel eliminandu se poate
//    elementele acesteia
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
//    listareAlegeri : parcurg vectorul de alegeri si afisez fiecare alegere alaturi de datele pe care le are
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
