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
        // Pentru citirea datelor de la tastatura se folosește câmpul scanner.
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}