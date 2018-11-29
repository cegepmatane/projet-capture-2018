package controleur;

import accesseur.HumiditeDAO;
import vue.NavigateurDesVues;
import vue.VueHumidite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import static accesseur.Connection.LOGGER;
import static accesseur.Connection.URL_BASE;

public class Controleur {
    private NavigateurDesVues navigateurDesVues;
    private VueHumidite vueHumidite;
    private HumiditeDAO humiditeDAO = new HumiditeDAO();

    private Controleur() {
    }

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


        try {
            String echantillonnage = navigateurDesVues.getVueHumidite().getBoiteChoix().getValue();
            String dateDebut = navigateurDesVues.getVueHumidite().getDateChoixDebut().getValue().toString();
            LocalDate dateFin = navigateurDesVues.getVueHumidite().getDateChoixFin().getValue();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateDebutFormat = null;
            Date dateFinFormat = null;
            Date dateAjourdhuiFormat = null;


            dateDebutFormat = new Date(sdf.parse(dateDebut).getTime());
            LOGGER.log(Level.INFO, "TimeStamp date 1 : " + dateDebutFormat);
            dateFinFormat = new Date(sdf.parse(dateFin.toString()).getTime());
            LOGGER.log(Level.INFO, "TimeStamp date 2 : " + dateFinFormat);

            dateAjourdhuiFormat = new Date(sdf.parse(LocalDate.now().toString()).getTime());



            if (LocalDate.now().isBefore(dateFin)) {
                url += "/" + echantillonnage + "/" + (dateDebutFormat.getTime() / 1000) + "/" + (dateAjourdhuiFormat.getTime() / 1000);
                vueHumidite.getDateChoixFin().setValue(LocalDate.now());
                LOGGER.log(Level.WARNING, "La date de fin selectionnez est après posterieur a Aujourd'hui!");
            }else if(dateDebut == null || dateFin == null){

            }else{
                url += "/" + echantillonnage + "/" + (dateDebutFormat.getTime() / 1000) + "/" + (dateFinFormat.getTime() / 1000);
            }
            navigateurDesVues.getVueHumidite().actualiserTableau(humiditeDAO.listerHumiditeSelonURL(url));

            LOGGER.log(Level.INFO, "Get XML depuis URL : "+ url.toString());
        } catch (ParseException e) {
            //e.printStackTrace();
            LOGGER.log(Level.SEVERE, "ERREUR INPUT DATE FORMAT");
        } catch (NullPointerException z){
            LOGGER.log(Level.SEVERE, "ERREUR INPUT DATE VIDE");
        }

    }
}
