package Tema1;
abstract class Persoana {
    protected String CNP;
    protected String nume;
    protected int varsta;

    public Persoana() {}
    public Persoana(String nume, int varsta, String CNP) {
        this.nume = nume;
        this.varsta = varsta;
        this.CNP = CNP;
    }
    public String getCNP() {
        return CNP;
    }
    public String getNume() {
        return nume;
    }

    public int getVarsta() {
        return varsta;
    }
//    protected void validareDate(String nume, int varsta, String CNP) {
//        if(CNP.length() != 13)
//            System.out.println("EROARE: CNP invalid");
//        for(char ch : CNP.toCharArray()){
//            if(!Character.isDigit(ch)) {
//                System.out.println("EROARE: CNP invalid");
//                break;
//            }
//        }
//        if(varsta < 18){
//            System.out.println("EROARE: Vârstă invalidă");
//        }
//
//    }

}
