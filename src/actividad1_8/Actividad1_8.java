package actividad1_8;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Actividad1_8 {

    public static void main(String[] args) {
        Path path = Paths.get("C:\\Users\\Mañana\\Documents\\NetBeansProjects\\Actividad1_8\\ficheros\\personal.xml");
        ArrayList<Persona> personas = Auxiliar.readPersonasXML(path);
        Auxiliar.writeCSVPersonas(personas);

    }
}
