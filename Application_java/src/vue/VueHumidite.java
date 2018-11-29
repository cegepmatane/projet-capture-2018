package vue;

import accesseur.HumiditeDAO;
import controleur.Controleur;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modele.Humidites;
import modele.LigneTableau;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class VueHumidite extends Scene {
    Pane panneau;
    VBox info;
    VBox general;
    GridPane grille;

    Controleur controleur;
    ChoiceBox<String> boiteChoix;
    TableView<LigneTableau> tableau;

    DatePicker dateChoixDebut;
    DatePicker dateChoixFin;
    ScrollBar scrollBar;
    Button actionActualiser;

    HumiditeDAO humiditeDAO = new HumiditeDAO();

    public VueHumidite() {
        super(new Pane(), 1060, 700);
        super.getStylesheets().add("style/style.css");

        this.panneau = (Pane) this.getRoot();
        info = new VBox();
        general = new VBox();
        grille = new GridPane();
        controleur = Controleur.getInstance();
    }

    public void afficher(Humidites listeHumidite) {
        this.grille.getChildren().clear();
        this.info.getChildren().clear();
        this.general.getChildren().clear();
        this.panneau.getChildren().clear();

        boiteChoix = new ChoiceBox<String>();
        boiteChoix.getItems().add("seconde");
        boiteChoix.getItems().add("minute");
        boiteChoix.getItems().add("heure");
        boiteChoix.getItems().add("jour");
        boiteChoix.getItems().add("semaine");
        boiteChoix.getItems().add("mois");
        boiteChoix.getItems().add("annee");
        boiteChoix.getSelectionModel().selectFirst();

        dateChoixDebut = new DatePicker();
        dateChoixFin = new DatePicker();
        /*dateChoixDebut.setValue(LocalDate.of(2009,3,8));
        dateChoixFin.setValue(LocalDate.now());*/

        actionActualiser = new Button("Actualiser");
        actionActualiser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controleur.notifierActualiser();
            }
        });

        actualiserTableau(listeHumidite);


        //grille.setVgap(50);
        initPanneaux(listeHumidite.size() == 0);
    }

    private void initPanneaux(boolean estVide){
        grille.getChildren().clear();
        general.getChildren().clear();
        panneau.getChildren().clear();

        general.setPadding(new Insets(50));
        grille.setVgap(21);
        grille.add(new Label("Vue Humiditée :"), 0, 0);
        grille.add(new Label("Echantillonage :"), 0, 1);
        grille.add(boiteChoix, 1, 1);
        grille.add(new Label("Date de début :"), 0, 2);
        grille.add(dateChoixDebut, 1, 2);
        grille.add(new Label("Date de fin :"), 0, 3);
        grille.add(dateChoixFin, 1, 3);
        grille.add(actionActualiser, 1, 4);

        general.getChildren().add(grille);

        if (estVide){
            general.getChildren().add(new Label("La periode choisie ne contient aucune donnée."));
        }else{
            general.setSpacing(20);
            general.getChildren().add(tableau);
        }
        LOGGER.log(Level.INFO, "Affichage mise a jour.");
        panneau.getChildren().add(general);
    }

    public void actualiserTableau(Humidites listeHumidite){
        info.getChildren().clear();
        this.tableau = new TableView();

        tableau.setEditable(false);

        TableColumn coloneDate = new TableColumn("Date");
        coloneDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        coloneDate.setResizable(false);
        coloneDate.setMinWidth(192);

        TableColumn coloneMoyenne = new TableColumn("Moyenne");
        coloneMoyenne.setCellValueFactory(new PropertyValueFactory<>("moyene"));
        coloneMoyenne.setResizable(false);
        coloneMoyenne.setMinWidth(192);

        TableColumn coloneMin = new TableColumn("Minimum");
        coloneMin.setCellValueFactory(new PropertyValueFactory<>("minimum"));
        coloneMin.setResizable(false);
        coloneMin.setMinWidth(192);

        TableColumn coloneMax = new TableColumn("Maximum");
        coloneMax.setCellValueFactory(new PropertyValueFactory<>("maximum"));
        coloneMax.setResizable(false);
        coloneMax.setMinWidth(192);

        TableColumn coloneNombre = new TableColumn("Nombre de valeur calculé");
        coloneNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        coloneNombre.setResizable(false);
        coloneNombre.setMinWidth(192);

        tableau.setMinWidth(960);
        tableau.setId("tableau-humidite");
        tableau.getColumns().addAll(coloneDate, coloneMoyenne, coloneMin, coloneMax, coloneNombre);

        for (LigneTableau ligne : listeHumidite.recupererLignesPourTableau()) {
            tableau.getItems().add(ligne);
        }

        info.getChildren().add(tableau);

        initPanneaux(listeHumidite.size() == 0);
    }



    public ChoiceBox<String> getBoiteChoix() {
        return boiteChoix;
    }

    public TableView<LigneTableau> getTableau() {
        return tableau;
    }

    public DatePicker getDateChoixDebut() {
        return dateChoixDebut;
    }

    public DatePicker getDateChoixFin() {
        return dateChoixFin;
    }

    public Button getActionActualiser() {
        return actionActualiser;
    }


}
