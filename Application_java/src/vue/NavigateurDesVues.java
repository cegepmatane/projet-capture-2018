package vue;

import controleur.Controleur;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class NavigateurDesVues extends Application {

    private VueHumidite vueHumidite;

    private Controleur controleur;

    public NavigateurDesVues() {
        this.vueHumidite = new VueHumidite();
    }

    private Stage stade = null;

    @Override
    public void start(Stage stade) throws Exception {
        this.stade = stade;

        stade.setScene(this.vueHumidite);
        stade.getIcons().add(new Image("water_drop.png"));
        stade.setTitle("Humidite Matane");
        stade.setMinWidth(1060);
        stade.setMinHeight(650);

        stade.show();

        controleur = Controleur.getInstance();
        controleur.activerVues(this);

        //ACEUILL
        this.naviguerVersVueHumidite();
    }

    public void naviguerVersVueHumidite() {
        stade.setScene(vueHumidite);
        stade.show();
    }

    public VueHumidite getVueHumidite() {
        return vueHumidite;
    }
}