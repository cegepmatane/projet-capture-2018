package modele;

import java.time.LocalDateTime;

public class Humidite {
    private int id;
    private double valeur;
    private LocalDateTime date;

    public Humidite(int id, double valeur, LocalDateTime date) {
        this.id = id;
        this.valeur = valeur;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
