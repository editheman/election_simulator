package Tema1;
import java.util.ArrayList;

public class Votant extends Persoana{
    private String neindemanatic;
    private boolean aVotat = false;

    public Votant(){
    }

    public Votant(String nume, int varsta, String cnp, String neindemanatic, boolean aVotat){
        super(nume, varsta, cnp);
        setNeindemanatic(neindemanatic);
        setaVotat(aVotat);
    }

    public void setaVotat(boolean aVotat){
        this.aVotat = aVotat;
    }
    public void setNeindemanatic(String neindemanatic){
        this.neindemanatic = neindemanatic;
    }

    public boolean getaVotat(){
        return aVotat;
    }
    public String getNeindemanatic(){
        return neindemanatic;
    }
}