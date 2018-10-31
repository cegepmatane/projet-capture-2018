package modele;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Humidite {
    private int id;
    private double moyene, maximum, minimum;
    private LocalDateTime date;

    public static final DateTimeFormatter formatMinute = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm") ;
    public static final DateTimeFormatter formatJour = DateTimeFormatter.ofPattern("MM/dd/yyyy") ;
    public static final DateTimeFormatter formatSemaine = DateTimeFormatter.ofPattern("MM/dd/yyyy") ;
    public static final DateTimeFormatter formatMois = DateTimeFormatter.ofPattern("MM/yyyy") ;

    public Humidite(int id, double moyene, double maximum, double minimum, LocalDateTime date) {
        this.id = id;
        this.moyene = moyene;
        this.maximum = maximum;
        this.minimum = minimum;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoyene() {
        return moyene;
    }

    public void setMoyene(double moyene) {
        this.moyene = moyene;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
