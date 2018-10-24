package controleur;

import vue.NavigateurDesVues;
import vue.VueHumidite;

import java.util.List;

public class Controleur {
    private NavigateurDesVues navigateurDesVues;
    private VueHumidite vueHumidite;

    private Controleur() {}

    private static Controleur instance = null;

    public static Controleur getInstance() {
        if (null == instance) {
            instance = new Controleur();
        }
        return instance;
    }
    public void activerVues(NavigateurDesVues navigateurDesVues) {

        this.navigateurDesVues = navigateurDesVues;

        this.vueHumidite = this.navigateurDesVues.getVueHumidite();
    }
}
