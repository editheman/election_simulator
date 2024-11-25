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
        boolean ruleaza = true;
        String id, nume, regiune, CNP, numeCircumscriptie, neindemanatic, CNPCandidat;
        int varsta;

        Input input = new Input();

        while(ruleaza) {
            int optiune = scanner.nextInt();
            scanner.nextLine();
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
                    CNP = scanner.next().trim();
                    varsta = scanner.nextInt();
                    nume = scanner.nextLine().trim();
                    System.out.println(input.adaugareCandidat(id, CNP, varsta, nume));
                    break;
                case 5:
                    id = scanner.next().trim();
                    CNP = scanner.nextLine().trim();
                    System.out.println(input.eliminareCandidat(id, CNP));
                    break;
                case 6:
                    id = scanner.next().trim();
                    numeCircumscriptie = scanner.next().trim();
                    CNP = scanner.next().trim();
                    varsta = scanner.nextInt();
                    neindemanatic = scanner.next().trim();
                    nume = scanner.nextLine().trim();
                    System.out.println(input.adaugareVotant(id, numeCircumscriptie, CNP, varsta, neindemanatic, nume));
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
                    CNP = scanner.next().trim();
                    CNPCandidat = scanner.nextLine().trim();
                    System.out.println(input.votare(id, numeCircumscriptie, CNP, CNPCandidat));
                    break;
                case 10:
                    id = scanner.nextLine().trim();
                    System.out.println(input.oprireAlegeri(id));
                    break;
                case 11:
                    id = scanner.next().trim();
                    numeCircumscriptie = scanner.nextLine().trim();
                    System.out.println();
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17:
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