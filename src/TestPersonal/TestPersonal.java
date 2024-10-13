package TestPersonal;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TestPersonal {
    
    public static final int SALIR = 0;
    public static final int FILTRAR_PERSONAS_POR_PAIS = 1;
    public static final int BORRAR_PERSONA_POR_EMAIL = 2;
    public static final Personal PERSONAS = new Personal();
    
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            boolean salir = false;
            Scanner teclado = new Scanner(System.in);
            String nombreFichero = LeerDatosTeclado.leerString("Introduzca el nombre del fichero:");
            gestionFichero(nombreFichero);
            do {
                imprimirMenu();
                int opcion = LeerDatosTeclado.leerInt("Introduzca una opción");
                switch (opcion) {
                    case SALIR:
                        salir = true;
                        break;
                    case FILTRAR_PERSONAS_POR_PAIS:
                        String pais = LeerDatosTeclado.leerString("Introduzca el nombre de un país");
                        imprimirPersonasPorPais(pais);
                        break;
                    case BORRAR_PERSONA_POR_EMAIL:
                        break;
                    default:
                        System.out.println("Esa opción no existe, vuelva a intentarlo");
                }
            } while (!salir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void imprimirMenu() {
        System.out.println("""
                           0. Salir de la aplicación 
                           1. Filtrar personas por país 
                           2. Borrar persona por su email 
                           """);
    }
    
    public static Personal gestionFichero(String nombreFichero) {
        String[] campos = nombreFichero.split("\\.");
        String extension = campos[1];
        File f = new File("./ficheros/" + nombreFichero);
        switch (extension) {
            case "csv":
                PERSONAS.addPersonasCSV(f);
                break;
            case "json":
                PERSONAS.addPersonasJSON(f);
                break;
            case "xml":
                PERSONAS.addPersonasXML(f);
                break;
        }
        return PERSONAS;
    }
    
    public static void imprimirPersonasPorPais(String pais) {
        ArrayList<Persona> personasPorPais = PERSONAS.personasPais(pais);
        personasPorPais.forEach(System.out::println);
        System.out.println();
    }
}
