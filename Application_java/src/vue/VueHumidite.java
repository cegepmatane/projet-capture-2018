package vue;

import controleur.Controleur;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class VueHumidite extends Scene {
    Pane panneau;
    Controleur controleur;

    public VueHumidite() {
        super(new Pane(), 1200, 700);

        this.panneau = (Pane) this.getRoot();

        controleur = Controleur.getInstance();
    }
}
