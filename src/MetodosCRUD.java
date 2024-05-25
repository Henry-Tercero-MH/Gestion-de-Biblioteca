import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class MetodosCRUD {
    private static final String NOMBRE_ARCHIVO_JSON = "src/LibrosExistentes.json"; // Ruta relativa consistente
   private static final Gson gson = new Gson();
    private static UsuarioArbol arbolUsuarios = new UsuarioArbol();
    private  static ArbolAVL arbolIntereses = new ArbolAVL();
    GestorLibros gestorLibros = new GestorLibros();
    private static final double TARIFA_MULTA_POR_DIA = 5;
    private LibroArbol arbolLibros;

    public MetodosCRUD() {
        this.arbolLibros = new LibroArbol();
        this.arbolUsuarios = new UsuarioArbol();
        this.arbolIntereses = new ArbolAVL();
    }
    public void cargarUsuariosDesdeJson() {
        List<Usuario> usuarios = GestorUsuarios.leerArchivoJson();
        if (usuarios != null) {
            for (Usuario usuario : usuarios) {
                arbolUsuarios.insertar(usuario);
            }
        }
    }

    public void guardarUsuariosEnJson() {
        List<Usuario> usuarios = arbolUsuarios.inOrder();
        GestorUsuarios.guardarUsuariosEnJson(usuarios);
    }

    public void cargarLibrosDesdeJson() {
        try {
            // Lee el JSON desde el archivo
            FileReader reader = new FileReader(NOMBRE_ARCHIVO_JSON);

            // Convierte el JSON a una lista de objetos Libro
            Type listType = new TypeToken<List<Libro>>() {}.getType();
            List<Libro> libros = gson.fromJson(reader, listType);

            // Inserta los libros en el árbol de libros
                for (Libro libro : libros) {
                arbolLibros.insertar(libro);
            }

            // Cierra el lector
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarUsuario(Scanner scanner) {
        System.out.println("Ingrese un nombre o correo:");
        String nombre = Validaciones.validarCorreo(scanner);

        System.out.println("Ingrese una contraseña:");
        String contraseña =Validaciones.ingresarContrasenaValida(scanner);

        // Verificar si el usuario ya existe
        Usuario usuarioExistente = arbolUsuarios.buscar(nombre);
        if (usuarioExistente != null) {
            System.out.println("El usuario ya existe.");
            return; // No se agrega el usuario si ya existe
        }

        // Crear el nuevo usuario
        Usuario nuevoUsuario = new Usuario(nombre, contraseña);

        // Insertar el nuevo usuario en el árbol
        arbolUsuarios.insertar(nuevoUsuario);

        // Obtener la lista actual de usuarios del archivo JSON
        List<Usuario> listaUsuarios = GestorUsuarios.leerArchivoJson();

        // Agregar el nuevo usuario a la lista existente
        if (listaUsuarios == null) {
            listaUsuarios = new ArrayList<>();
        }
        listaUsuarios.add(nuevoUsuario);

        // Guardar los cambios en el archivo JSON
        GestorUsuarios.guardarUsuariosEnJson(listaUsuarios);

        // Mostrar mensaje de éxito
        System.out.println("Usuario agregado correctamente.");
        System.out.println(nuevoUsuario.toString());
    }


    public void agregarLibro(Scanner scanner) {
        System.out.println("Ingrese el título del libro:");
        String titulo = Validaciones.validarCampoNoVacio(scanner);
        scanner.nextLine();
        System.out.println("Ingrese el autor del libro:");
        String autor = Validaciones.validarCampoNoVacio(scanner);
        System.out.println("Ingrese el género del libro:");
        String genero = Validaciones.validarCampoNoVacio(scanner);
        System.out.println("Ingrese el año de publicación del libro:");
        String añoPublicacion = Validaciones.validarCampoNoVacio(scanner);

        Libro nuevoLibro = new Libro(titulo, autor, genero, añoPublicacion);
        arbolLibros.insertar(nuevoLibro);
        GestorLibros.guardarJson(nuevoLibro);

        System.out.println("Libro agregado correctamente.");
        System.out.println(nuevoLibro.toString());

        guardarLibrosEnJson();
    }

    List<Libro> obtenerListaLibros() {
        return arbolLibros.inOrder();
    }

    private void guardarLibrosEnJson() {
        List<Libro> libros = obtenerListaLibros();
        String jsonLibros = gson.toJson(libros);

        try (FileWriter writer = new FileWriter(NOMBRE_ARCHIVO_JSON)) {
            writer.write(jsonLibros);
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo JSON: " + e.getMessage());
        }
    }

    public void buscarLibro(Scanner scanner) {
        System.out.println("Ingrese el título o autor del libro:");
        String tituloLibro = Validaciones.validarCampoNoVacio(scanner).toLowerCase();

        Libro libroEncontrado = arbolLibros.buscarLibro(tituloLibro);
        arbolIntereses.registrarConsulta(libroEncontrado);
        if (libroEncontrado != null) {
            System.out.println("Libro encontrado:");
            System.out.println(libroEncontrado.toString());
        } else {
            System.out.println("El libro '" + tituloLibro + "' no se encuentra en la biblioteca.");
        }
    }


    public void buscarInformacionUsuario(Scanner scanner) {
        Usuario usuario = new Usuario();
        System.out.println("Digite el nombre del usuario:");
        String nombreUsuario = Validaciones.validarCampoNoVacio(scanner);

        // Buscar el usuario en el árbol de usuarios
        Usuario usuarioEncontrado = arbolUsuarios.buscar(nombreUsuario);

        // Verificar si se encontró el usuario
        if (usuarioEncontrado != null) {
            // Mostrar la información del usuario
            System.out.println("Información del usuario:");
            System.out.println(usuarioEncontrado.toString());

        } else {
            System.out.println("El usuario no se encontró en la base de datos.");
        }
    }


    public void prestarLibro(Scanner scanner) {
        cargarUsuariosDesdeJson();
        System.out.println("Ingrese el título del libro que desea prestar:");
        String tituloLibro = Validaciones.validarCampoNoVacio(scanner);
        Libro libro = arbolLibros.buscarLibro(tituloLibro);
        // Insertar el título y género del libro en el árbol AVL de intereses del usuario
        arbolIntereses.registrarPrestamo(libro );
        System.out.println("__________________________________________________");
        System.out.println("Sugerencias");
        arbolIntereses.librosPopulares();
        System.out.println("__________________________________________________");
arbolIntereses.librosPopulares();


        if (libro != null) {
            if (libro.isDisponible()) {
                System.out.println("El libro está disponible para préstamo.");
                System.out.println("Ingrese el nombre del usuario al que desea prestar el libro:");
                String nombreUsuario = Validaciones.validarCampoNoVacio(scanner);

                Usuario usuario = arbolUsuarios.buscar(nombreUsuario);
                if (usuario != null) {
                    libro.setDisponible(false);
                    libro.setUsuarioPrestamo(usuario);

                    Calendar calendar = Calendar.getInstance();
                    Date fechaPrestamo = calendar.getTime();
                    calendar.add(Calendar.MINUTE, 2);
                    Date fechaDevolucionEsperada = calendar.getTime();
                    libro.setFechaDevolucionEsperada(fechaDevolucionEsperada);
                    libro.incrementarContadorPrestamos();
                    // Agregar el libro a la lista de libros prestados del usuario

                    System.out.println("¡El préstamo se ha realizado con éxito!");
                    System.out.println(libro.toString());
                    System.out.println("Libros prestados al usuario:");
                    guardarLibrosEnJson();
                } else {
                    System.out.println("Usuario no encontrado.");
                }
            } else {
                System.out.println("El libro no está disponible en este momento. Está en préstamo.");
            }
        } else {
            System.out.println("El libro no está disponible en la biblioteca.");
        }
    }

    public void generarInformeLibrosPopulares() {
        List<Libro> librosPrestados = arbolLibros.inOrder();
        Map<Libro, Long> conteoPrestamosPorLibro = librosPrestados.stream()
                .collect(Collectors.groupingBy(libro -> libro, Collectors.counting()));
        List<Map.Entry<Libro, Long>> librosOrdenadosPorPrestamos = conteoPrestamosPorLibro.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toList());

        System.out.println("Libros más populares:");
        int contador = 1;
        for (Map.Entry<Libro, Long> entry : librosOrdenadosPorPrestamos) {
            Libro libro = entry.getKey();
            Long cantidadPrestamos = entry.getValue();
            System.out.println(contador + ". " + libro.getTitulo() + " - Préstamos: " + cantidadPrestamos);
            contador++;
        }
    }

    public void devolverLibro(Scanner scanner, String tituloLibro, String nombreUsuario) {
        Libro libro = arbolLibros.buscarLibro(tituloLibro);

        if (libro != null && libro.getUsuarioPrestamo() != null && libro.getUsuarioPrestamo().getNombre().equals(nombreUsuario)) {
            libro.setDisponible(true);
            Date fechaActual = new Date();
            Date fechaDevolucionEsperada = libro.getFechaDevolucionEsperada();

            if (fechaActual != null && fechaDevolucionEsperada != null) {
                if (fechaActual.after(fechaDevolucionEsperada)) {
                    long diferenciaTiempo = fechaActual.getTime() - fechaDevolucionEsperada.getTime();
                    long diasRetraso = diferenciaTiempo / (5 * 60 * 60 * 24);
                    double multa = diasRetraso * TARIFA_MULTA_POR_DIA;
                    System.out.println(diferenciaTiempo);
                    System.out.println(diasRetraso);

                    System.out.println("Se ha excedido la fecha de devolución esperada.");
                    System.out.println("Se ha generado una multa de: Q." + multa);
                } else {
                    System.out.println("Libro devuelto correctamente.");
                }
            } else {
                System.out.println("Error: La fecha actual o la fecha de devolución esperada es nula.");
            }

            libro.reiniciarEstado(); // Reiniciar el estado del libro
            guardarLibrosEnJson();
        } else {
            System.out.println("El libro no ha sido prestado por el usuario especificado.");
        }
    }


        public void sugerirLibrosPorIntereses(Usuario usuario) {
            System.out.println("Sugerencias de libros basadas en tus intereses:");
            List<String> generosFavoritos = usuario.getGenerosFavoritos();
            boolean haySugerencias = false;

            for (Libro libro : arbolLibros.inOrder()) {
                if (generosFavoritos.contains(libro.getGenero())) {
                    System.out.println(libro.getTitulo() + " - " + libro.getGenero());
                    haySugerencias = true;
                }
            }

            if (!haySugerencias) {
                System.out.println("Lo siento, no hay sugerencias de libros para tus intereses.");
            }
        }

}