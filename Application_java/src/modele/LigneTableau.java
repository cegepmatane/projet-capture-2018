package modele;

public class LigneTableau {
    double moyene;
    double maximum;
    double minimum;

    public LigneTableau(){}

    public LigneTableau(double moyene, double maximum, double minimum) {
        this.moyene = moyene;
        this.maximum = maximum;
        this.minimum = minimum;
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
}
