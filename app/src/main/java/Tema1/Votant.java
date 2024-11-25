package Tema1;
import java.util.ArrayList;

public class Votant extends Persoana{
    private String neindemanatic;

    public Votant(){
    }

    public Votant(String nume, int varsta, String CNP, String neindemanatic){
        super(nume, varsta, CNP);
        setNeindemanatic(neindemanatic);
    }

    public void setNeindemanatic(String neindemanatic){
        this.neindemanatic = neindemanatic;
    }
    public String getNeindemanatic(){
        return neindemanatic;
    }
}
