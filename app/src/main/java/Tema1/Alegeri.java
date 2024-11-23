package Tema1;

import java.util.ArrayList;

public class Alegeri {
    private String id;
    private String nume;
    private int statut = -1;
    public ArrayList<Alegeri> alegeri = new ArrayList<Alegeri>();

    public Alegeri(String id, String nume) {
        setId(id);
        setNume(nume);
    }
    public Alegeri() {}

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getNume() {
        return nume;
    }

    public int getStatut() {
        return statut;
    }

    public String startAlegeri(String id) {
        for (Alegeri alegere : alegeri) {
            if(alegere.getId().equals(id)) {
                if(statut == -1) {
                    statut = 0;
                    return "Au pornit alegerile" + getNume();
                } else {
                    return "EROARE: Alegerile deja au început";
                }
            }
        }
        return "EROARE: Nu există alegeri cu acest id";
    }

    public String
}
