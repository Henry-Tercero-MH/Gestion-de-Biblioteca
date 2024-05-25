import java.util.List;
import java.util.ArrayList;

public class ArbolAVL {
    NodoAVL raiz;
    private static final int UMbral = 3; // Umbral para determinar libros populares

    public ArbolAVL() {
        this.raiz = null;
    }

    // Método para insertar un libro en el árbol AVL
    public void insertar(Libro libro) {
        this.raiz = insertarRecursivo(this.raiz, libro);
    }

    // Método auxiliar para la inserción recursiva
    private NodoAVL insertarRecursivo(NodoAVL nodo, Libro libro) {
        if (nodo == null) {
            return new NodoAVL(libro);
        }

        int comparacion = libro.getTitulo().compareTo(nodo.libro.getTitulo());

        if (comparacion < 0) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, libro);
        } else if (comparacion > 0) {
            nodo.derecho = insertarRecursivo(nodo.derecho, libro);
        } else {
            // El libro ya existe, incrementar el contador de préstamos
            nodo.libro.incrementarContadorPrestamos();
        }

        // Actualizar la altura del nodo
        nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));

        // Realizar el balanceo del árbol
        return balancear(nodo);
    }

    private int obtenerAltura(NodoAVL nodo) {
        return nodo == null ? 0 : nodo.altura;
    }

    private NodoAVL balancear(NodoAVL nodo) {
        // Implementar la lógica de balanceo del árbol AVL (rotaciones)
        return nodo;
    }

    public void registrarPrestamo(Libro libro) {
        this.raiz = registrarPrestamoRecursivo(this.raiz, libro);
    }

    private NodoAVL registrarPrestamoRecursivo(NodoAVL nodo, Libro libro) {
        if (nodo == null) {
            return new NodoAVL(libro);
        }

        int comparacion = libro.getTitulo().compareTo(nodo.libro.getTitulo());

        if (comparacion < 0) {
            nodo.izquierdo = registrarPrestamoRecursivo(nodo.izquierdo, libro);
        } else if (comparacion > 0) {
            nodo.derecho = registrarPrestamoRecursivo(nodo.derecho, libro);
        } else {
            // El libro ya existe, incrementar el contador de préstamos
            nodo.libro.incrementarContadorPrestamos();
        }

        // Actualizar la altura del nodo
        nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));

        // Realizar el balanceo del árbol
        return balancear(nodo);
    }

    public List<Libro> librosPopulares() {
        List<Libro> librosPopulares = new ArrayList<>();
        recopilarLibrosPopulares(this.raiz, librosPopulares);
        if (!librosPopulares.isEmpty()) {
            System.out.println("Libros populares:");
            for (Libro libro : librosPopulares) {
                System.out.println(libro); // Suponiendo que tienes implementado el método toString() en la clase Libro
            }
        } else {
            System.out.println("No hay libros populares en este momento.");
        }
        return librosPopulares;
    }

    private void recopilarLibrosPopulares(NodoAVL nodo, List<Libro> librosPopulares) {
        if (nodo != null) {
            recopilarLibrosPopulares(nodo.izquierdo, librosPopulares);
            if (nodo.libro.getContadorConsultas() > UMbral) { // Ajusta el umbral según tus necesidades
                librosPopulares.add(nodo.libro);
            }
            recopilarLibrosPopulares(nodo.derecho, librosPopulares);
        }
    }

    public void registrarConsulta(Libro libro) {
        registrarConsultaRecursivo(this.raiz, libro);
    }

    private void registrarConsultaRecursivo(NodoAVL nodo, Libro libro) {
        if (nodo != null) {
            int comparacion = libro.getTitulo().compareTo(nodo.libro.getTitulo());
            if (comparacion < 0) {
                registrarConsultaRecursivo(nodo.izquierdo, libro);
            } else if (comparacion > 0) {
                registrarConsultaRecursivo(nodo.derecho, libro);
            } else {
                // El libro fue encontrado, incrementar el contador de consultas
                nodo.libro.incrementarContadorConsultas();
            }
        }
    }
}

class NodoAVL {
    Libro libro;
    int altura;
    NodoAVL izquierdo;
    NodoAVL derecho;

    public NodoAVL(Libro libro) {
        this.libro = libro;
        this.altura = 1;
    }
}
