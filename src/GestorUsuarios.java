import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class GestorUsuarios {
    private static final String NOMBRE_ARCHIVO_JSON = "src/Usuarios.json";
    private static final Gson gson = new Gson();

    public static List<Usuario> leerArchivoJson() {
        try (FileReader reader = new FileReader(NOMBRE_ARCHIVO_JSON)) {
            Type listType = new TypeToken<List<Usuario>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void guardarUsuariosEnJson(List<Usuario> usuarios) {
        try (FileWriter writer = new FileWriter(NOMBRE_ARCHIVO_JSON)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo JSON: " + e.getMessage());
        }
    }
}
