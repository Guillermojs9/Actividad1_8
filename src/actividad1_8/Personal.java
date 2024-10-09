package actividad1_8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public Persona getPersona(int index) {
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
}
