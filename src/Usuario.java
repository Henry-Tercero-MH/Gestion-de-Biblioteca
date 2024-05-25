import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private String contraseña;
    private List<Libro> librosPrestados;
    private List<String> generosFavoritos;

    public Usuario() {

    }

    public void agregarGeneroFavorito(String genero) {
        generosFavoritos.add(genero);
    }

    public Usuario(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.librosPrestados = new ArrayList<>(); // Inicialmente, el usuario no tiene libros prestados
    }

    // Getters y setters para los atributos del usuario

    public String getNombre() {
        return nombre;
    }

    public List<String> getGenerosFavoritos() {
        return generosFavoritos;
    }

    public void setGenerosFavoritos(List<String> generosFavoritos) {
        this.generosFavoritos = generosFavoritos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    public void setLibrosPrestados(List<Libro> librosPrestados) {
        this.librosPrestados = librosPrestados;
    }

    // Métodos para agregar, remover y obtener libros prestados al usuario

    public void agregarLibroPrestado(Libro libro) {
        librosPrestados.add(libro);
    }
    public void imprimirLibrosPrestados() {
        if (librosPrestados != null) {
            System.out.println("Libros Prestados:");
            for (Libro libro : librosPrestados) {
                System.out.println(libro.toString());
            }
        } else {
            System.out.println("No hay libros prestados.");
        }
    }

    public void removerLibroPrestado(Libro libro) {
        librosPrestados.remove(libro);
    }

    public boolean tieneLibroPrestado(Libro libro) {
        return librosPrestados.contains(libro);
    }

    @Override
    public String toString() {
        return "Usuario: "+getNombre() +
                " Libros Prestados:" + librosPrestados;
    }
}
