package actividad1_8;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Personal {

    ArrayList<Persona> personas;

    public Personal() {
        personas = new ArrayList<>();
    }

    public void addPersona(Persona p) {
        this.personas.add(p);
    }

    public ArrayList<Persona> personasPais(String pais) {
        if (pais == null) {
            throw new IllegalArgumentException("El pais no puede ser null");
        }
        ArrayList<Persona> personasPorPais = new ArrayList<>();
        for (Persona p : this.personas) {
            if (p.getCountry().equals(pais)) {
                personasPorPais.add(p);
            }
        }
        return personasPorPais;
    }

    public boolean deletePersona(String email) {
        if (email == null) {
            throw new IllegalArgumentException("El email no puede ser null");
        }
        for (Persona p : this.personas) {
            if (p.getEmail().equals(email)) {
                this.personas.remove(p);
                return true;
            }
        }
        return false;
    }

    public Persona get(int index) {
        return this.personas.get(index);
    }

    public int size() {
        return this.personas.size();
    }

    public void addPersonasCSV(File f) {
        try {
            BufferedReader br;
            br = new BufferedReader(new FileReader(f));
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
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Personal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Personal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addPersonasXML(File f) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(f);
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
                this.addPersona(p);
            }
        } catch (SAXException ex) {
            Logger.getLogger(Actividad1_8.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Actividad1_8.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Actividad1_8.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addPersonasJSON(File f) {
        try {
            Gson gson = new Gson();
            Reader reader;
            reader = Files.newBufferedReader(Paths.get(f.getPath()));
            List<Persona> personas = Arrays.asList(gson.fromJson(reader, Persona[].class));
            for (Persona p : personas) {
                this.personas.add(p);
            }
        } catch (IOException ex) {
            Logger.getLogger(Personal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
