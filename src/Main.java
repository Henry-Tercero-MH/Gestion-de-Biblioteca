import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        iniciar();
    }

    public static void iniciar() {
        MetodosCRUD metodosCRUD = new MetodosCRUD();
        metodosCRUD.cargarUsuariosDesdeJson();
        metodosCRUD.cargarLibrosDesdeJson();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println(".:: Menú Principal ::.");
            System.out.println("1. Buscar libro");
            System.out.println("2. Solicitar préstamo");
            System.out.println("3. Devolver libro");
            System.out.println("4. Ver información de usuario");
            System.out.println("5. Agregar usuario");
            System.out.println("6. Agregar libro");
            System.out.println("7. Consultar todos los libros");
            System.out.println("0. Salir");
            System.out.print("Ingrese su opción: ");

            opcion = Validaciones.leerEntero(scanner);

            switch (opcion) {
                case 1:
                 //buscar libro
                    ArbolAVL arbolAVL = new ArbolAVL();
                    System.out.println(   arbolAVL.librosPopulares());
                    metodosCRUD.buscarLibro(scanner);
                    break;

                case 2:
                    // Lógica para solicitar un préstamo
                    System.out.println("Bienvenido, valide su usuario");
                    String nombre = Validaciones.validarCampoNoVacio(scanner);

                    metodosCRUD.prestarLibro(scanner);
                    break;
                case 3:
                    // Lógica para devolver un libro
                    System.out.println("Digita el titulo del libro");
                    String titulo= Validaciones.validarCampoNoVacio(scanner);
                    System.out.println("Digite su usuario: ");
                    String usuario= Validaciones.validarCampoNoVacio(scanner);
                    metodosCRUD.devolverLibro(scanner,titulo,usuario);
                    break;
                case 4:

                    // Lógica para ver la información del usuario
                    metodosCRUD.buscarInformacionUsuario(scanner);
                    break;
                case 5:
                    metodosCRUD.agregarUsuario(scanner); // Llamar al método para agregar un usuario
                    break;
                case 6:
                    metodosCRUD.agregarLibro(scanner);
                    break;
                case 7:
                    metodosCRUD.cargarLibrosDesdeJson();
                    List<Libro> librosCargados = metodosCRUD.obtenerListaLibros();
                    if (librosCargados != null && !librosCargados.isEmpty()) {
                        System.out.println("Libros cargados desde el archivo JSON:");
                        for (Libro libro : librosCargados) {
                            System.out.println(libro);
                        }
                    } else {
                        System.out.println(" se encontraron libros en el archivo JSON.");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo de la biblioteca...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}
