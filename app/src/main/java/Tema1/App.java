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
        // Implementați aici cerințele din enunț
        // Pentru citirea datelor de la tastatura se folosește câmpul scanner
        scanner = new Scanner(System.in);
        boolean ruleaza = true;

        while(ruleaza) {
            int optiune = scanner.nextInt();
            switch(optiune) {
                case 1:
                    
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    break;

                case 5:
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