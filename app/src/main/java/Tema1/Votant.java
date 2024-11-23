package Tema1;
import java.util.ArrayList;

public class Votant extends Persoana{
    boolean neindemanatic = false;

    public Votant(){
        ArrayList<Votant> votanti = new ArrayList<>();
    }

    public Votant(String nume, int varsta, String CNP){
        super(nume, varsta, CNP);
    }

    public void setNeindemanatic(boolean neindemanatic){
        this.neindemanatic = neindemanatic;
    }
    public boolean getNeindemanatic(){
        return neindemanatic;
    }
}
