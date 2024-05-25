import java.util.*;

public class Libro  {
    private String titulo;
    private String autor;
    private String genero;
    private String añoPublicacion;
    private boolean disponible;
    private Usuario usuarioPrestamo;
    private int contadorPrestamos;
    private Date fechaDevolucionEsperada;
    private int contadorConsultas;
    // Método para incrementar el contador de consultas

    public int getContadorConsultas() {
        return contadorConsultas;
    }

    public void setContadorConsultas(int contadorConsultas) {
        this.contadorConsultas = contadorConsultas;
    }

    public void incrementarContadorConsultas() {
        this.contadorConsultas++;
    }
    public Libro() {

    }

    public int getContadorPrestamos() {
        return contadorPrestamos;
    }

    public void setContadorPrestamos(int contadorPrestamos) {
        this.contadorPrestamos = contadorPrestamos;
    }

    public Date getFechaDevolucionEsperada() {
        return fechaDevolucionEsperada;
    }

    public void setFechaDevolucionEsperada(Date fecha) {
        this.fechaDevolucionEsperada = fecha;
    }

    public void incrementarContadorPrestamos() {
        contadorPrestamos++;
    }

    public Libro(String titulo, String autor, String genero, String añoPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.añoPublicacion = añoPublicacion;
        this.disponible = true; // Por defecto, el libro está disponible al crearlo
        this.usuarioPrestamo = null; // Inicialmente no tiene un usuario que lo haya prestado
    }

    // Getters y setters para los atributos del libro

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(String añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Usuario getUsuarioPrestamo() {
        return usuarioPrestamo;
    }

    public void setUsuarioPrestamo(Usuario usuarioPrestamo) {
        this.usuarioPrestamo = usuarioPrestamo;
    }
    public void reiniciarEstado() {
        this.disponible = true;
        this.usuarioPrestamo = null;
        this.fechaDevolucionEsperada = null;
    }


    @Override
    public String toString() {
        String usuarioString = (usuarioPrestamo != null) ? usuarioPrestamo.toString() : "Disponibles";
        return "DATOS: " +
                "TITULO: " + titulo +
                " AUTOR: " + autor +
                " GENERO: " + genero  +
                " AÑO DE PUBLICACION: " + añoPublicacion +
                " USUARIO: " + usuarioString;
    }


}
