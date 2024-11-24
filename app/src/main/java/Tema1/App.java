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
        String id, nume, regiune, CNP;
        int varsta;

        Input input = new Input();

        while(ruleaza) {
            int optiune = scanner.nextInt();
            scanner.nextLine();
            switch(optiune) {
                case 0:
                    id = scanner.next();
                    nume = scanner.nextLine();
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
                    nume = scanner.nextLine();
                    System.out.println(input.eliminareCircumscriptie(id, nume));
                    break;
                case 4:
                    id = scanner.next();
                    CNP = scanner.next();
                    varsta = scanner.nextInt();
                    scanner.nextLine();
                    nume = scanner.nextLine();
                    break;
                case 5:
                    id = scanner.next();
                    CNP = scanner.nextLine();
                    System.out.println(input.eliminareCandidat(id, CNP));
                    break;

                case 6:
                    break;
                case 7:

                    break;

                case 8:

                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
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
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}