package actividad1_8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
}
