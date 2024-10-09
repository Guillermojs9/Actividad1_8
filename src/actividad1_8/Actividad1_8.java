package actividad1_8;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Actividad1_8 {
    
    public static void main(String[] args) {
        
        Path path = Paths.get("C:\\Users\\Mañana\\Documents\\NetBeansProjects\\Actividad1_8\\ficheros\\personal.csv");
        ArrayList<Persona> personas = Auxiliar.readPersonasCSV(path);
        for (int i = 0; i < personas.size(); i++) {
            System.out.println(personas.get(i));
        }
    }
}
