package actividad1_8;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Actividad1_8 {
    
    public static void main(String[] args) {
        Path path = Paths.get("./ficheros/personal.json");
        ArrayList<Persona> personas = Auxiliar.readPersonasJSON(path);
        personas.forEach(System.out::println);
    }
}
