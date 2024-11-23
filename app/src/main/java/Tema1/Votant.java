package Tema1;
public class Votant extends Persoana{
    boolean neindemanatic = false;

    public Votant(String nume, int varsta, String CNP){
        super(nume, varsta, CNP);
    }
    public boolean getNeindemanatic(){
        return neindemanatic;
    }
    public void validareDate(){

    }
}
