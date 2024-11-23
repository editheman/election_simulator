package Tema1;
public class Circumscriptie {
    public String nume;
    public String regiune;

    public Circumscriptie(String nume, String regiune) {
        setNume(nume);
        setRegiune(regiune);
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getNume() {
        return nume;
    }
    public void setRegiune(String regiune) {
        this.regiune = regiune;
    }
    public String getRegiune() {
        return regiune;
    }
}
