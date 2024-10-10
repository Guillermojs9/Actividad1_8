package actividad1_8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Auxiliar {

    public static ArrayList<Persona> readPersonasCSV(Path path) {
        ArrayList<Persona> personas = new ArrayList<>();
        try {
            BufferedReader br = Files.newBufferedReader(path);
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                String campos[] = linea.split(",");
                String firstName = campos[0].trim();
                String lastName = campos[1].trim();
                String email = campos[2].trim();
                String gender = campos[3].trim();
                String country = campos[4].trim();
                Persona p = new Persona(firstName, lastName, email, gender, country);
                personas.add(p);
            }
            br.close();
            for (int i = 0; i < personas.size(); i++) {
                System.out.println(personas.get(i));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Personal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Personal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personas;
    }

    public static ArrayList<Persona> readPersonasXML(Path path) {
        ArrayList<Persona> personas = new ArrayList<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(path.toFile());
            Element raiz = doc.getDocumentElement();
            NodeList listFirstName = raiz.getElementsByTagName("firstName");
            NodeList listLastName = raiz.getElementsByTagName("lastName");
            NodeList listEmail = raiz.getElementsByTagName("email");
            NodeList listGender = raiz.getElementsByTagName("gender");
            NodeList listCountry = raiz.getElementsByTagName("country");
            for (int i = 0; i < listFirstName.getLength(); i++) {
                String firstName = listFirstName.item(i).getFirstChild().getNodeValue();
                String lastName = listLastName.item(i).getFirstChild().getNodeValue();
                String email = listEmail.item(i).getFirstChild().getNodeValue();
                String gender = listGender.item(i).getFirstChild().getNodeValue();
                String country = listCountry.item(i).getFirstChild().getNodeValue();
                Persona p = new Persona(firstName, lastName, email, gender, country);
                personas.add(p);
            }
        } catch (IOException ex) {
            Logger.getLogger(Actividad1_8.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Actividad1_8.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personas;
    }
}
