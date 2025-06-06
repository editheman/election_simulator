package Tema1;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.text.*;

public class App {
    private Scanner scanner;

    public App(InputStream input) {
        this.scanner = new Scanner(input);
    }

    public void run() {
//        un flag pentru a putea face programul sa ruleze in bucla pana la primirea comenzii 18
        boolean ruleaza = true;
        String id, nume, regiune, cnp, numeCircumscriptie, neindemanatic, cnpCandidat;
        int varsta;
//        initializez un obiect de tip input, acesta fiind unul ce imi va prelua comenzile si creerea instantelor necesare
        Input input = new Input();

        while(ruleaza) {
            int optiune = scanner.nextInt();
            scanner.nextLine();
//            tratez fiecare input posibil, si citesc variabilele necesare pentru respectivul input
            switch(optiune) {
                case 0:
                    id = scanner.next();
                    nume = scanner.nextLine().trim();
                    System.out.println(input.creareAlegeri(id, nume));
                    break;
                case 1:
                    id = scanner.nextLine();
                    System.out.println(input.startAlegeri(id));
                    break;
                case 2:
                    id = scanner.next();
                    nume = scanner.next();
                    regiune = scanner.nextLine();
                    System.out.println(input.adaugareCircumscriptie(id, nume, regiune));
                    break;
                case 3:
                    id = scanner.next();
                    nume = scanner.nextLine().trim();
                    System.out.println(input.eliminareCircumscriptie(id, nume));
                    break;
                case 4:
                    id = scanner.next();
                    cnp = scanner.next().trim();
                    varsta = scanner.nextInt();
                    nume = scanner.nextLine().trim();
                    System.out.println(input.adaugareCandidat(id, cnp, varsta, nume));
                    break;
                case 5:
                    id = scanner.next().trim();
                    cnp = scanner.nextLine().trim();
                    System.out.println(input.eliminareCandidat(id, cnp));
                    break;
                case 6:
                    id = scanner.next().trim();
                    numeCircumscriptie = scanner.next().trim();
                    cnp = scanner.next().trim();
                    varsta = scanner.nextInt();
                    neindemanatic = scanner.next().trim();
                    nume = scanner.nextLine().trim();
                    System.out.println(input.adaugareVotant(id, numeCircumscriptie, cnp, varsta, neindemanatic, nume));
                    break;
                case 7:
                    id = scanner.nextLine().trim();
                    input.printareCandidati(id);
                    break;
                case 8:
                    id = scanner.next().trim();
                    numeCircumscriptie = scanner.nextLine().trim();
                    input.printareVotanti(id, numeCircumscriptie);
                    break;
                case 9:
                    id = scanner.next().trim();
                    numeCircumscriptie = scanner.next().trim();
                    cnp = scanner.next().trim();
                    cnpCandidat = scanner.nextLine().trim();
                    System.out.println(input.votare(id, numeCircumscriptie, cnp, cnpCandidat));
                    break;
                case 10:
                    id = scanner.nextLine().trim();
                    System.out.println(input.oprireAlegeri(id));
                    break;
                case 11:
                    id = scanner.next().trim();
                    numeCircumscriptie = scanner.nextLine().trim();
                    System.out.println(input.raportCircumscriptie(id, numeCircumscriptie));
                    break;
                case 12:
                    id = scanner.nextLine().trim();
                    System.out.println(input.raportNational(id));
                    break;
                case 13:
                    id = scanner.next().trim();
                    numeCircumscriptie = scanner.nextLine().trim();
                    System.out.println(input.analizaCircumscriptie(id, numeCircumscriptie));
                    break;
                case 14:
                    id = scanner.nextLine().trim();
                    System.out.println(input.analizaNationala(id));
                    break;
                case 15:
                    id = scanner.nextLine().trim();
                    System.out.println(input.raportFraude(id));
                    break;
                case 16:
                    id = scanner.nextLine().trim();
                    System.out.println(input.eliminareAlegeri(id));
                    break;
                case 17:
                    System.out.println(input.listareAlegeri());
                    break;
                case 18:
                    ruleaza = false;
                    break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}