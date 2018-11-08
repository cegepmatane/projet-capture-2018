package controleur;

import accesseur.HumiditeDAO;
import vue.NavigateurDesVues;
import vue.VueHumidite;

import java.util.List;

import static accesseur.Connection.URL_BASE;

public class Controleur {
    private NavigateurDesVues navigateurDesVues;
    private VueHumidite vueHumidite;
    private HumiditeDAO humiditeDAO = new HumiditeDAO();

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

        this.vueHumidite.afficher(humiditeDAO.listerToutesHumidite());
    }

    public void notifierActualiser() {
        String url = URL_BASE;

        String echantillonnage = navigateurDesVues.getVueHumidite().getBoiteChoix().getValue();
        String dateDebut = navigateurDesVues.getVueHumidite().getDateChoixDebut().getValue().toString();
        String dateFin = navigateurDesVues.getVueHumidite().getDateChoixFin().getValue().toString();

        url += "/"+echantillonnage;

        System.out.println(url);

        navigateurDesVues.getVueHumidite().afficher(humiditeDAO.listerHumiditeSelonURL(url));
    }
}
