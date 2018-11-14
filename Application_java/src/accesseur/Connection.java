package accesseur;

import java.util.logging.Logger;

public interface Connection {
    public final static Logger LOGGER = Logger.getLogger("Log");

    final public static String URL_BASE = "http://54.39.145.59/projet-capture-serveur-php/humidites";

    final public static String CHAMP_MOYENNE = "moyenne";
    final public static String CHAMP_MAX = "max";
    final public static String CHAMP_MIN = "min";
    final public static String CHAMP_NOMBRE = "nombreHumiditeReference";
    final public static String CHAMP_DATE = "date";
}
