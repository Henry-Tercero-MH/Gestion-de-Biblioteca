import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GestorLibros {
    private static final String NOMBRE_ARCHIVO_JSON = "src/LibrosExistentes.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void guardarJson(Libro nuevoLibro) {
        List<Libro> libros = leerArchivoJson();
        if (libros == null) {
            libros = new ArrayList<>();
        }
        libros.add(nuevoLibro); // Agregar el nuevo libro a la lista existente
        guardarLibrosEnJson(libros);
    }

    public static List<Libro> leerArchivoJson() {
        try (FileReader reader = new FileReader(NOMBRE_ARCHIVO_JSON)) {
            Type listType = new TypeToken<List<Libro>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            // Manejamos específicamente el problema de lectura del archivo JSON
            System.out.println("No se pudo leer el archivo JSON: " + e.getMessage());
            return new ArrayList<>(); // Devolvemos una lista vacía en caso de error
        }
    }

    private static void guardarLibrosEnJson(List<Libro> libros) {
        try (FileWriter writer = new FileWriter(NOMBRE_ARCHIVO_JSON)) {
            // Escribimos la lista completa de libros en el archivo JSON
            gson.toJson(libros, writer);
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo JSON: " + e.getMessage());
        }
    }


}
