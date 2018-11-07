package accesseur;

import modele.Humidite;
import modele.Humidites;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

import static accesseur.Connection.*;


public class HumiditeDAO {

    private Humidites listeHumidite = new Humidites();

    public Humidites listerHumidite() {

        listeHumidite.add(new Humidite(0, 12.5, 20, 6, LocalDateTime.now()));
        listeHumidite.add(new Humidite(1, 23.1, 40.1, -1.3, LocalDateTime.now()));
        listeHumidite.add(new Humidite(2, 5, 10, 0, LocalDateTime.now()));

        return listeHumidite;
    }

    public void parseXML() {

        try
        {
            DocumentBuilderFactory f =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            Document doc = b.parse(URL_BASE);

            doc.getDocumentElement().normalize();
            Element racine = doc.getDocumentElement();
            System.out.println ("Root element: " +
                    doc.getDocumentElement().getNodeName());

            //System.out.println(description(racine,""));
            NodeList listeNoeud = doc.getElementsByTagName("humidite");

            for (int iterateur = 0; iterateur < listeNoeud.getLength(); iterateur++) {

                Node noeud = listeNoeud.item(iterateur);

                System.out.println("\nCurrent Element :" + noeud.getNodeName());

                if (noeud.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) noeud;

                    System.out.println("Moyenne : " + eElement.getElementsByTagName(CHAMP_MOYENNE).item(0).getTextContent());
                    System.out.println("Max : " + eElement.getElementsByTagName(CHAMP_MAX).item(0).getTextContent());
                    System.out.println("Min : " + eElement.getElementsByTagName(CHAMP_MIN).item(0).getTextContent());
                    System.out.println("Nombre d'humidite : " + eElement.getElementsByTagName(CHAMP_NOMBRE).item(0).getTextContent());
                    System.out.println("Date : " + eElement.getElementsByTagName(CHAMP_DATE).item(0).getTextContent());

                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private String description(Node n, String tab){
        String str = new String();
        //Nous nous assurons que le nœud passé en paramètre est une instance d'Element
        //juste au cas où il s'agisse d'un texte ou d'un espace, etc.
        if(n instanceof Element){

            //Nous sommes donc bien sur un élément de notre document
            //Nous castons l'objet de type Node en type Element
            Element element = (Element)n;

            //Nous pouvons récupérer le nom du nœud actuellement parcouru
            //grâce à cette méthode, nous ouvrons donc notre balise
            str += "<" + n.getNodeName();

            //nous contrôlons la liste des attributs présents
            if(n.getAttributes() != null && n.getAttributes().getLength() > 0){

                //nous pouvons récupérer la liste des attributs d'un élément
                NamedNodeMap att = n.getAttributes();
                int nbAtt = att.getLength();

                //nous parcourons tous les nœuds pour les afficher
                for(int j = 0; j < nbAtt; j++){
                    Node noeud = att.item(j);
                    //On récupère le nom de l'attribut et sa valeur grâce à ces deux méthodes
                    str += " " + noeud.getNodeName() + "=\"" + noeud.getNodeValue() + "\" ";
                }
            }

            //nous refermons notre balise car nous avons traité les différents attributs
            str += ">";

            //La méthode getChildNodes retournant le contenu du nœud + les nœuds enfants
            //Nous récupérons le contenu texte uniquement lorsqu'il n'y a que du texte, donc un seul enfant
            if(n.getChildNodes().getLength() == 1)
                str += n.getTextContent();

            //Nous allons maintenant traiter les nœuds enfants du nœud en cours de traitement
            int nbChild = n.getChildNodes().getLength();
            //Nous récupérons la liste des nœuds enfants
            NodeList list = n.getChildNodes();
            String tab2 = tab + "\t";

            //nous parcourons la liste des nœuds
            for(int i = 0; i < nbChild; i++){

                Node n2 = list.item(i);

                //si le nœud enfant est un Element, nous le traitons
                if (n2 instanceof Element){
                    //appel récursif à la méthode pour le traitement du nœud et de ses enfants
                    str += "\n " + tab2 + description(n2, tab2);
                }
            }

            //Nous fermons maintenant la balise
            if(n.getChildNodes().getLength() < 2)
                str += "</" + n.getNodeName() + ">";
            else
                str += "\n" + tab +"</" + n.getNodeName() + ">";
        }

        return str;
    }
}
