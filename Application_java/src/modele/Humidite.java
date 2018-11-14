package modele;

public class Humidite {
    private int nombre;
    private double moyene, maximum, minimum;
    private String date;

    public Humidite(int nombre, double moyene, double maximum, double minimum, String date) {
        this.nombre = nombre;
        this.moyene = moyene;
        this.maximum = maximum;
        this.minimum = minimum;
        this.date = date;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
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

    @Override
    public String toString() {
        return "Humidite{" +
                "nombre=" + nombre +
                ", moyene=" + moyene +
                ", maximum=" + maximum +
                ", minimum=" + minimum +
                ", date='" + date + '\'' +
                '}';
    }
}
