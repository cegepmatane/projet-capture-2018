package modele;

public class LigneTableau {
    private double moyene,maximum,minimum;
    private String date;
    private int nombre;

    public LigneTableau(){}

    public LigneTableau(double moyene, double maximum, double minimum, String date, int nombre) {
        this.moyene = moyene;
        this.maximum = maximum;
        this.minimum = minimum;
        this.date = date;
        this.nombre = nombre;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
}
