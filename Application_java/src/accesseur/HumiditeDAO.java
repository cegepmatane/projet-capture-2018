package accesseur;

import modele.Humidite;
import modele.Humidites;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class HumiditeDAO {
    public Humidites listerHumidite(){
        Humidites listeHumidite = new Humidites();
        listeHumidite.add(new Humidite(0,12.5,20,6, LocalDateTime.now()));
        listeHumidite.add(new Humidite(1,23.1,40.1,-1.3, LocalDateTime.now()));
        listeHumidite.add(new Humidite(2,5,10,0, LocalDateTime.now()));

        return listeHumidite;
    }
}
