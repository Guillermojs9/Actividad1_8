package actividad1_8;

import java.io.File;

public class Actividad1_8 {

    public static void main(String[] args) {
        File f = new File("./ficheros/personal.json");
        Personal personas = new Personal();
        personas.addPersonasJSON(f);
        for (int i = 0; i < personas.size(); i++) {
            System.out.println(personas.get(i));
        }
    }
}
