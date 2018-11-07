package vue;

import accesseur.HumiditeDAO;
import controleur.Controleur;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modele.LigneTableau;

public class VueHumidite extends Scene {
    Pane panneau;
    VBox info;
    VBox general;
    GridPane grille;

    Controleur controleur;
    ChoiceBox<String> boiteChoix;
    TableView<LigneTableau> tableau;


    HumiditeDAO humiditeDAO = new HumiditeDAO();

    public VueHumidite() {
        super(new Pane(), 1060, 650);

        super.getStylesheets().add("style/style.css");

        humiditeDAO.parseXML();
        this.panneau = (Pane) this.getRoot();

        info = new VBox();
        general = new VBox();
        grille = new GridPane();
        controleur = Controleur.getInstance();

        boiteChoix = new ChoiceBox<String>();
        boiteChoix.getItems().add("Jours");
        boiteChoix.getItems().add("Semaine");
        boiteChoix.getItems().add("Mois");
        boiteChoix.getSelectionModel().selectFirst();


        //TODO LISTENER CHOICE BOX
        /*boiteChoix.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            }
        });*/

        this.tableau = new TableView();

        tableau.setEditable(false);

        TableColumn colonedate = new TableColumn("Date");
        colonedate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colonedate.setResizable(false);
        colonedate.setMinWidth(240);

        TableColumn coloneMoyenne = new TableColumn("Moyenne");
        coloneMoyenne.setCellValueFactory(new PropertyValueFactory<>("moyene"));
        coloneMoyenne.setResizable(false);
        coloneMoyenne.setMinWidth(240);

        TableColumn coloneMin = new TableColumn("Minimum");
        coloneMin.setCellValueFactory(new PropertyValueFactory<>("minimum"));
        coloneMin.setResizable(false);
        coloneMin.setMinWidth(240);

        TableColumn coloneMax = new TableColumn("Maximum");
        coloneMax.setCellValueFactory(new PropertyValueFactory<>("maximum"));
        coloneMax.setResizable(false);
        coloneMax.setMinWidth(240);

        tableau.setMinWidth(960);
        tableau.setId("tableau-humidite");
        tableau.getColumns().addAll(colonedate,coloneMoyenne, coloneMin, coloneMax);

        for (LigneTableau ligne: humiditeDAO.listerHumidite().recupererLignesPourTableau()) {
            tableau.getItems().add(ligne);
        }


        //grille.setVgap(50);
        general.setPadding(new Insets(50));
        grille.setVgap(21);
        grille.add(new Label("Vue Humiditée :"),0,0);
        grille.add(new Label("Echantillonage :"),0,1);
        grille.add(boiteChoix,1,1);
        info.getChildren().add(tableau);

        general.getChildren().add(grille);
        general.getChildren().add(tableau);

        panneau.getChildren().add(general);
    }
}
