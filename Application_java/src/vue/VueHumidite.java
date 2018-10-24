package vue;

import controleur.Controleur;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import modele.LigneTableau;

public class VueHumidite extends Scene {
    Pane panneau;
    Controleur controleur;
    GridPane grille;

    TableView<LigneTableau> tableau;

    public VueHumidite() {
        super(new Pane(), 1060, 650);

        super.getStylesheets().add("style/style.css");

        this.panneau = (Pane) this.getRoot();

        grille = new GridPane();
        grille.setPadding(new Insets(50));

        controleur = Controleur.getInstance();

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
        tableau.getColumns().addAll(coloneMoyenne, coloneMin, coloneMax);

        LigneTableau ligne = new LigneTableau(34, 55.4, 1.2);
        tableau.getItems().add(ligne);

        grille.add(new Label("Vue Humiditée :"), 0, 0);
        grille.setVgap(50);
        grille.add(tableau, 0, 1);

        panneau.getChildren().add(grille);
    }
}
