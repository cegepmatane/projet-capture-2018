package modele;

import java.util.ArrayList;

public class Humidites extends ArrayList<Humidite> {

    public ArrayList<LigneTableau> recupererLignesPourTableau(){
        ArrayList<LigneTableau> tabLigne = new ArrayList<LigneTableau>();
        for (Humidite humidite : this){
            tabLigne.add(new LigneTableau(humidite.getMoyene(),humidite.getMaximum(),humidite.getMinimum()));
        }

        return tabLigne;
    }

}
