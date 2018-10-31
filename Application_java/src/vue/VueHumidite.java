package vue;

import accesseur.HumiditeDAO;
import controleur.Controleur;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
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

        /*boiteChoix.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                System.out.println(box.getItems().get((Integer) number2));
            }
        });*/

        this.tableau = new TableView();

        tableau.setEditable(false);

        TableColumn coloneMoyenne = new TableColumn("Moyenne");
        coloneMoyenne.setCellValueFactory(new PropertyValueFactory<>("moyene"));
        coloneMoyenne.setResizable(false);
        coloneMoyenne.setMinWidth(320);

        TableColumn coloneMin = new TableColumn("Minimum");
        coloneMin.setCellValueFactory(new PropertyValueFactory<>("minimum"));
        coloneMin.setResizable(false);
        coloneMin.setMinWidth(320);

        TableColumn coloneMax = new TableColumn("Maximum");
        coloneMax.setCellValueFactory(new PropertyValueFactory<>("maximum"));
        coloneMax.setResizable(false);
        coloneMax.setMinWidth(320);

        tableau.setMinWidth(960);
        tableau.setId("tableau-humidite");
        tableau.getColumns().addAll(coloneMoyenne, coloneMin, coloneMax);

        /*LigneTableau ligne = new LigneTableau(34, 55.4, 1.2);
        tableau.getItems().add(ligne);*/


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
