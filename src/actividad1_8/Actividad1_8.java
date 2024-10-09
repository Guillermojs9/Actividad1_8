package actividad1_8;

import java.io.File;

public class Actividad1_8 {
    
    public static void main(String[] args) {
        Personal personas = new Personal();
        File ficheroCsv = new File("ficheros/personal.csv");
        personas.addPersonasCSV(ficheroCsv);
        for (int i = 0; i < personas.size(); i++) {
            System.out.println(personas.getPersona(i));
        }
        
    }
    
}
