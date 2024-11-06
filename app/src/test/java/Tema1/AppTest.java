package Tema1;

import static org.junit.jupiter.api.Assumptions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class AppTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test

    @DisplayName("Alegeri Succes")
    public void test() {
        ByteArrayInputStream in = new ByteArrayInputStream("0\nA0 Alegeri Electorale 2025\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("S-au creat alegerile Alegeri Electorale 2025")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Alegeri EROARE")
    public void test1() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n0\nA0 Alegeri Electorale 2025\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Deja există alegeri cu id A0")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Pornire alegeri Succes")
    public void test2() {
        ByteArrayInputStream in = new ByteArrayInputStream("0\nA0 Alegeri Electorale 2025\n1\nA0\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("Au pornit alegerile Alegeri Electorale 2025")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Pornire alegeri Succes, one word name")
    public void test2_1() {
        ByteArrayInputStream in = new ByteArrayInputStream("0\nA0 Alegeri\n1\nA0\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("Au pornit alegerile Alegeri")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Pornire alegeri EROARE id alegeri invalid")
    public void test3() {
        ByteArrayInputStream in = new ByteArrayInputStream("0\nA0 Alegeri Electorale 2025\n1\nA1\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Nu există alegeri cu acest id")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Pornire alegeri EROARE alegerile deja au inceput")
    public void test4() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n1\nA0\n1\nA0\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Alegerile deja au început")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Adaugare circumscripție Succes")
    public void test5() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 București Muntenia\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("S-a adăugat circumscripția București")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Adaugare circumscripție EROARE deja exista circumscripție")
    public void test6() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 București Muntenia\n2\nA0 București Muntenia\n18\n"
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Deja există o circumscripție cu numele București")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Adaugare circumscripție EROARE id alegeri invalid")
    public void test7() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA1 București Muntenia\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Nu există alegeri cu acest id")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Adaugare circumscripție EROARE alegerile nu sunt in stagiul IN_CURS")
    public void test8() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n2\nA0 București Muntenia\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Nu este perioada de votare")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Eliminare circumscripție Succes")
    public void test9() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 București Muntenia\n3\nA0 București\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("S-a șters circumscripția București")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Eliminare circumscripție EROARE circumscripția nu exista")
    public void test10() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n1\nA0\n3\nA0 București\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Nu există o circumscripție cu numele București")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Eliminare circumscripție EROARE id alegeri invalid")
    public void test11() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n1\nA0\n3\nA1 București\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Nu există alegeri cu acest id")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Eliminare circumscripție EROARE alegerile nu sunt in stagiul IN_CURS")
    public void test12() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                "0\nA0 Alegeri Electorale 2025\n3\nA0 București\n18\n".getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Nu este perioada de votare")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    String setup = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 București Muntenia\n";
    String end = "18\n";

    @Test

    @DisplayName("Adăugare candidat în alegeri Succes")
    public void test13() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "4\nA0 1234567891234 45 Dumitru Florin Ionescu\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("S-a adăugat candidatul Dumitru Florin Ionescu")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Adăugare candidat în alegeri EROARE CNP invalid")
    public void test14() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "4\nA0 12345678912345 45 Dumitru Florin Ionescu\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: CNP invalid")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Adăugare candidat în alegeri EROARE vârstă invalidă")
    public void test15() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "4\nA0 1234567891234 34 Dumitru Florin Ionescu\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Vârstă invalidă")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Adăugare candidat în alegeri EROARE CNP existent")
    public void test16() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "4\nA0 1234567891234 45 Dumitru Florin Ionescu\n"
                        + "4\nA0 1234567891234 45 Dumitru Florin Ionescu\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Candidatul Dumitru Florin Ionescu are deja același CNP")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Adăugare candidat în alegeri EROARE id alegeri invalid")
    public void test17() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "4\nA1 1234567891234 45 Dumitru Florin Ionescu\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Nu există alegeri cu acest id")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Adăugare candidat în alegeri EROARE alegerile nu sunt in stagiul IN_CURS")
    public void test18() {
        // creare alegeri, dar nu pornire
        setup = "0\nA0 Alegeri Electorale 2025\n";
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "4\nA0 1234567891234 45 Dumitru Florin Ionescu\n"
                        + "18\n")
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Nu este perioada de votare")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Eliminare candidat din alegeri Succes")
    public void test19() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "4\nA0 1234567891234 45 Dumitru Florin Ionescu\n"
                        + "5\nA0 1234567891234\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("S-a șters candidatul Dumitru Florin Ionescu")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Eliminare candidat din alegeri EROARE candidatul nu exista")
    public void test20() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "5\nA0 1234567891234\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Nu există un candidat cu CNP-ul 1234567891234")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Eliminare candidat din alegeri EROARE id alegeri invalid")
    public void test21() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "5\nA1 1234567891234\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Nu există alegeri cu acest id")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Adăugare votant în circumscripție Succes")
    public void test22() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "6\nA0 București 1234567891234 20 da Chipescu Ciprian\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("S-a adăugat votantul Chipescu Ciprian")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Adăugare votant în circumscripție EROARE CNP invalid")
    public void test23() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "6\nA0 București 12345678912345 20 da Chipescu Ciprian\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: CNP invalid")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Adăugare votant în circumscripție EROARE vârstă invalidă")
    public void test24() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "6\nA0 București 1234567891234 17 da Chipescu Ciprian\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Vârstă invalidă")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Adăugare votant în circumscripție EROARE CNP existent")
    public void test25() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "6\nA0 București 1234567891234 20 da Chipescu Ciprian\n"
                        + "6\nA0 București 1234567891234 20 da Chipescu Ciprian\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Votantul Chipescu Ciprian are deja același CNP")) {
            assertTrue(true);
        } else {
            fail(output);
        }

    }

    @Test

    @DisplayName("Adăugare votant în circumscripție EROARE circumscripția nu exista")
    public void test26() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup + "6\nA0 București 1234567891234 20 da Chipescu Ciprian\n"
                        + "6\nA0 București2 1234567891234 20 da Chipescu Ciprian\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Nu există o circumscripție cu numele București2")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    String setup21 = "0\nA0 Alegeri Electorale 2025\n1\nA0\n2\nA0 București Muntenia\n2\nA0 București2 Muntenia\n";
    String setup22 = "6\nA0 București 1234567891234 20 da Chipescu Ciprian\n"
            + "6\nA0 București 1234567891235 20 da Chipescu Ciprian2\n"
            + "6\nA0 București2 1234567891236 20 da Chipescu Ciprian3\n"
            + "6\nA0 București2 1234567891237 20 da Chipescu Ciprian4\n";
    String setup23 = "4\nA0 1234567891238 45 Dumitru Florin Ionescu\n"
            + "4\nA0 1234567891239 45 Dumitru Florin Ionescu2\n";

    @Test

    @DisplayName("Listarea candidaților din alegeri Succes")
    public void test27() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup21 + setup22 + setup23 + "7\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "Candidații:";
        String expected2 = "Dumitru Florin Ionescu2 1234567891239 45";
        String expected3 = "Dumitru Florin Ionescu 1234567891238 45";
        if (output.contains(expected) && output.contains(expected2) && output.contains(expected3)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Listarea candidaților din alegeri GOL")
    public void test28() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup21 + "7\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("GOL: Nu sunt candidați")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Listarea candidaților din alegeri EROARE id alegeri invalid")
    public void test29() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup21 + "7\nA1\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Nu există alegeri cu acest id")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Listarea votanților dintr-o circumscripție Succes")
    public void test31() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup21 + setup22 + setup23 + "8\nA0 București\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "Votanții din București:";
        String expected2 = "Chipescu Ciprian 1234567891234 20";
        String expected3 = "Chipescu Ciprian2 1234567891235 20";
        if (output.contains(expected) && output.contains(expected2) && output.contains(expected3)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Listarea votanților dintr-o circumscripție GOL")
    public void test32() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup21 + "8\nA0 București\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("GOL: Nu sunt votanți în București")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Listarea votanților dintr-o circumscripție EROARE circumscripția nu exista")
    public void test33() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup21 + "8\nA0 București23\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Nu există o circumscripție cu numele București2")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Listarea votanților dintr-o circumscripție EROARE id alegeri invalid")
    public void test34() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup21 + "8\nA1 București\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        if (output.contains("EROARE: Nu există alegeri cu acest id")) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    // avem:
    // 2 circumscripții:
    // București cu 2 votanți: Chipescu Ciprian, Chipescu Ciprian2
    // București2 cu 2 votanți: Chipescu Ciprian3, Chipescu Ciprian4
    // 2 candidați: Dumitru Florin Ionescu, Dumitru Florin Ionescu2

    String setup30 = setup21 + setup22 + setup23;

    // mai putem inca 1 circumscriptie cu alta regiune: Muntenia2
    String setup31 = "2\nA0 București3 Muntenia2\n";

    // mai putem inca 3 votanti in Bucuresti3
    String setup32 = "6\nA0 București3 1234567891238 20 da Chipescu Ciprian5\n"
            + "6\nA0 București3 1234567891239 20 da Chipescu Ciprian6\n"
            + "6\nA0 București3 1234567891230 20 da Chipescu Ciprian7\n";

    String setup3 = setup30 + setup31 + setup32;

    @Test

    @DisplayName("Votare Succes")
    public void test35() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "9\nA0 București 1234567891234 1234567891238\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "Chipescu Ciprian a votat pentru Dumitru Florin Ionescu";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Votare EROARE vot multiplu")
    public void test36() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "9\nA0 București 1234567891234 1234567891238\n"
                        + "9\nA0 București 1234567891234 1234567891238\n"
                        + end)
                        .getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "FRAUDĂ: Votantul cu CNP-ul 1234567891234 a încercat să comită o fraudă. Votul a fost anulat";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Votare EROARE votantul nu este inregistrat in circumscripție")
    public void test37() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "9\nA0 București2 1234567891234 1234567891238\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "FRAUDĂ: Votantul cu CNP-ul 1234567891234 a încercat să comită o fraudă. Votul a fost anulat";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Votare EROARE circumscripția nu exista")
    public void test38() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "9\nA0 București22 1234567891234 1234567891238\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "EROARE: Nu există o circumscripție cu numele București22";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Votare EROARE votantul nu exista")
    public void test39() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "9\nA0 București 1234567891230 1234567891238\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "EROARE: Nu există un votant cu CNP-ul 1234567891230";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Votare EROARE candidatul nu exista")
    public void test40() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "9\nA0 București 1234567891234 1234567891231\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "EROARE: Nu există un candidat cu CNP-ul 1234567891231";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Votare EROARE id alegeri invalid")
    public void test41() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "9\nA1 București 1234567891234 1234567891238\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "EROARE: Nu există alegeri cu acest id";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    // 10. Oprire alegeri
    // <id_alegeri>
    // Această comandă va întoarce următoarele:
    // Caz Răspuns
    // Succes. S-au terminat alegerile <nume_alegeri>
    // Id alegeri invalid. EROARE: Nu există alegeri cu acest id
    // Alegerile nu sunt în stagiul ÎN_CURS. EROARE: Nu este perioada de votare

    @Test

    @DisplayName("Oprire alegeri Succes")
    public void test42() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "10\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "S-au terminat alegerile Alegeri Electorale 2025";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Oprire alegeri EROARE id alegeri invalid")
    public void test43() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "10\nA1\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "EROARE: Nu există alegeri cu acest id";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    String setup40 = setup3;
    // se fac cateva voturi
    String setup41 = "9\nA0 București 1234567891234 1234567891238\n"
            + "9\nA0 București 1234567891235 1234567891239\n"
            + "9\nA0 București2 1234567891236 1234567891238\n"
            + "9\nA0 București2 1234567891237 1234567891239\n"
            + "9\nA0 București3 1234567891238 1234567891238\n"
            + "9\nA0 București3 1234567891239 1234567891239\n"
            + "9\nA0 București3 1234567891230 1234567891238\n"
            // și fraude
            + "9\nA0 București 1234567891234 1234567891238\n"
            + "9\nA0 București 1234567891234 1234567891238\n";
    String setup4 = setup40 + setup41;

    // terminam alegerile
    String setup42 = "10\nA0\n";

    @Test

    @DisplayName("Raport voturi per circumscripție Succes")
    public void test44() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup4 + setup42 + "11\nA0 București\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "Raport voturi București:";
        String expected2 = "Dumitru Florin Ionescu2 1234567891239 - 1";
        String expected3 = "Dumitru Florin Ionescu 1234567891238 - 1";
        if (output.contains(expected) && output.contains(expected2) && output.contains(expected3)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Raport voturi per circumscripție GOL")
    public void test45() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + setup42 + "11\nA0 București3\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "GOL: Lumea nu își exercită dreptul de vot în București3";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Raport voturi per circumscripție EROARE circumscripția nu exista")
    public void test46() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup4 + setup42 + "11\nA0 București22\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "EROARE: Nu există o circumscripție cu numele București22";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Raport voturi per circumscripție EROARE id alegeri invalid")
    public void test47() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup4 + "11\nA1 București\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "EROARE: Nu există alegeri cu acest id";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Raport voturi național Succes")
    public void test48() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup4 + setup42 + "12\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "Raport voturi România:";
        String expected2 = "Dumitru Florin Ionescu2 1234567891239 - 3";
        String expected3 = "Dumitru Florin Ionescu 1234567891238 - 4";
        if (output.contains(expected) && output.contains(expected2) && output.contains(expected3)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Raport voturi național GOL")
    public void test49() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + setup42 + "12\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "GOL: Lumea nu își exercită dreptul de vot în România";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Raport voturi național EROARE id alegeri invalid")
    public void test50() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup4 + "12\nA1\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "EROARE: Nu există alegeri cu acest id";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    String setup50 = setup4 + setup42;

    // 13. Analiză detaliată per circumscripție
    // <id_alegeri> <nume_circumscripție>
    // EX: A1 Argeș
    // !! Se afișează informații analitice despre alegerile din circumscripția
    // precizată, sub următorul format (toate acestea se vor scrie pe O SINGURĂ
    // linie):
    // În <nume_circumscripție> au fost <nr_voturi_circumscripție> voturi din
    // <nr_voturi_național>. Adică <procentaj>%. Cele mai multe voturi au fost
    // strânse de <CNP> <nume>. Acestea constituie <procentaj>% din voturile
    // circumscripției.

    // !! La procentaje, se va afișa doar partea întreagă.
    // Această comandă va întoarce următoarele:
    // Caz Răspuns
    // Succes. În <nume_circumscripție> au fost <nr_voturi_circumscripție> voturi
    // din <nr_voturi_național>. Adică <procentaj>%. Cele mai multe voturi au fost
    // strânse de <CNP> <nume>. Acestea constituie <procentaj>% din voturile
    // circumscripției.
    // Nu sunt voturi GOL: Lumea nu iși exercită dreptul de vot în
    // <nume_circumscripție>
    // Nu a fost găsit obiectul EROARE: Nu există o circumscripție cu numele
    // <nume_circumscripție>
    // Alegerile nu se află în stagiul corepunzător EROARE: Încă nu s-a terminat
    // votarea
    // Id alegeri invalid. EROARE: Nu există alegeri cu acest id

    @Test

    @DisplayName("Analiză detaliată per circumscripție Succes")
    public void test51() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup50 + "13\nA0 București\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "În București au fost 2 voturi din 7. Adică 28%. Cele mai multe voturi au fost strânse de 1234567891239 Dumitru Florin Ionescu2. Acestea constituie 50% din voturile circumscripției.";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Analiză detaliată per circumscripție GOL")
    public void test52() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + setup42 + "13\nA0 București3\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "GOL: Lumea nu își exercită dreptul de vot în București3";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    // 14. Analiză detaliată pe plan național
    // <id_alegeri>
    // !! Lista va fi ordonată alfabetic, după numele regiunii
    // !! Totalul voturilor dintr-o regiune se va face adunând voturile din fiecare
    // circumscripție.
    // !! Se afișează informații analitice despre alegerile din România, sub
    // următorul format (toate acestea se vor scrie pe LINII SEPARATE), conform:

    // În România au fost <nr_voturi> voturi.
    // În <regiune1> au fost <nr_voturi_regiune1> voturi din <nr_voturi_național>.
    // Adică <procentaj>%. Cele mai multe voturi au fost strânse de <CNP> <nume>.
    // Acestea constituie <procentaj>% din voturile regiunii.
    // ...
    // În <regiuneN> au fost <nr_voturi_regiuneN> voturi din <nr_voturi_național>.
    // Adică <procentaj>%. Cele mai multe voturi au fost strânse de <CNP> <nume>.
    // Acestea constituie <procentaj>% din voturile regiunii.

    // Această comandă va întoarce următoarele:
    // Caz Răspuns
    // Succes. În România au fost <nr_voturi> voturi.
    // În <regiune1> au fost <nr_voturi_regiune1> voturi din <nr_voturi_național>.
    // Adică <procentaj>%. Cele mai multe voturi au fost strânse de <CNP> <nume>.
    // Acestea constituie <procentaj>% din voturile regiunii.
    // ...
    // În <regiuneN> au fost <nr_voturi_regiuneN> voturi din <nr_voturi_național>.
    // Adică <procentaj>%. Cele mai multe voturi au fost strânse de <CNP> <nume>.
    // Acestea constituie <procentaj>% din voturile regiunii.
    // Nu sunt voturi GOL: Lumea nu iși exercită dreptul de vot în România.
    // Alegerile nu se află în stagiul corepunzător EROARE: Încă nu s-a terminat
    // votarea
    // Id alegeri invalid. EROARE: Nu există alegeri cu acest id

    @Test

    @DisplayName("Analiză detaliată pe plan național Succes")
    public void test53() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup50 + "14\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "În România au fost 7 voturi.";
        String expected2 = "În Muntenia2 au fost 3 voturi din 7. Adică 42%. Cele mai multe voturi au fost strânse de 1234567891238 Dumitru Florin Ionescu. Acestea constituie 66% din voturile regiunii.";
        String expected3 = "În Muntenia au fost 4 voturi din 7. Adică 57%. Cele mai multe voturi au fost strânse de 1234567891239 Dumitru Florin Ionescu2. Acestea constituie 50% din voturile regiunii.";
        if (output.contains(expected) && output.contains(expected2) && output.contains(expected3)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Analiză detaliată pe plan național GOL")
    public void test54() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + setup42 + "14\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "GOL: Lumea nu își exercită dreptul de vot în România";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test

    @DisplayName("Analiză detaliată pe plan național EROARE id alegeri invalid")
    public void test55() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup4 + "14\nA1\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "EROARE: Nu există alegeri cu acest id";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    // 15. Rapoarte fraude
    // <id_alegeri>
    // !! Se afișează o listă cu toate fraudele comise. Ordinea de listare este LIFO
    // (Last In, First Out); ultimul venit, primul servit. Formatul este:
    // În <nume_circumscripție>: <CNP> <nume>

    // Caz Răspuns
    // Succes. Fraude comise:
    // În <nume_circumscripție>: <CNP> <nume>
    // În <nume_circumscripție>: <CNP> <nume>
    // …
    // În <nume_circumscripție>: <CNP> <nume>
    // Nu sunt fraude. GOL: Românii sunt cinstiți
    // Alegerile nu se află în stagiul corepunzător EROARE: Încă nu s-a terminat
    // votarea
    // Id alegeri invalid. EROARE: Nu există alegeri cu acest id

    // 16. Șterge alegeri
    // <id_alegeri>
    // !! Se șterg alegerile corespunzătoare și toate datele aferente.
    // Caz Răspuns
    // Succes. S-au șters alegerile <nume_alegeri>.
    // Id alegeri invalid. EROARE: Nu există alegeri cu acest id

    // 17. Listare alegeri
    // Comanda nu are parametrii.
    // Caz Răspuns
    // Succes. Alegeri:
    // <id_alegeri> <nume_alegeri>
    // …
    // <id_alegeri> <nume_alegeri>
    // Nu sunt alegeri. GOL: Nu sunt alegeri
    // 18. Ieșire
    // Comanda nu are parametrii.

    // Comitem cateva voturi multiple
    String setup60 = setup4 + setup42;

    @Test
    @DisplayName("Rapoarte fraude Succes")
    public void test56() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup60 + "15\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "Fraude comise:";
        String expected2 = "În București: 1234567891234 Chipescu Ciprian";
        if (output.contains(expected) && output.contains(expected2)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    @DisplayName("Rapoarte fraude GOL")
    public void test57() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + setup42 + "15\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "GOL: Românii sunt cinstiți";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    @DisplayName("Șterge alegeri Succes")
    public void test58() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup60 + "16\nA0\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "S-au șters alegerile Alegeri Electorale 2025";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    @DisplayName("Șterge alegeri EROARE id alegeri invalid")
    public void test59() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup60 + "16\nA1\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "EROARE: Nu există alegeri cu acest id";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    @DisplayName("Listare alegeri Succes")
    public void test60() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup60 + "17\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "Alegeri:";
        String expected2 = "A0 Alegeri Electorale 2025";
        if (output.contains(expected) && output.contains(expected2)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }

    @Test
    @DisplayName("Listare alegeri GOL")
    public void test61() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                (setup3 + "16\nA0\n17\n" + end).getBytes());
        App app = new App(in);
        app.run();
        String output = outputStreamCaptor.toString().trim();
        String expected = "GOL: Nu sunt alegeri";
        if (output.contains(expected)) {
            assertTrue(true);
        } else {
            fail(output);
        }
    }
}
