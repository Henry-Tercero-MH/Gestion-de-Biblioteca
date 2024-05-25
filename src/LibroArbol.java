import java.util.ArrayList;
import java.util.List;

public class LibroArbol {
    private NodoArbolLibro raiz;

    // Método para realizar un recorrido en orden (in-order traversal)
    public List<Libro> inOrder() {
        List<Libro> librosOrdenados = new ArrayList<>();
        inOrderRecursivo(raiz, librosOrdenados);
        return librosOrdenados;
    }

    // Método auxiliar recursivo para el recorrido en orden
    private void inOrderRecursivo(NodoArbolLibro nodo, List<Libro> librosOrdenados) {
        if (nodo != null) {
            inOrderRecursivo(nodo.izquierdo, librosOrdenados);
            librosOrdenados.add(nodo.libro); // Agregar el libro actual a la lista
            inOrderRecursivo(nodo.derecho, librosOrdenados);
        }
    }

    public LibroArbol() {
        raiz = null;
    }

    public void insertar(Libro libro) {
        if (raiz == null) {
            raiz = new NodoArbolLibro(libro);
        } else {
            raiz.insertar(libro);
        }
    }
    public Libro buscarLibro(String titulo) {
        return buscarLibroRecursivo(raiz, titulo);
    }

    private Libro buscarLibroRecursivo(NodoArbolLibro nodo, String titulo) {
        if (nodo == null) {
            return null; // Si el nodo actual es nulo, el libro no se encuentra en el árbol
        }

        if (nodo.libro.getTitulo().equalsIgnoreCase(titulo) || nodo.libro.getAutor().equalsIgnoreCase(titulo)) {
            return nodo.libro; // Si el título o el autor coinciden, se ha encontrado el libro
        } else {
            Libro encontradoIzquierda = buscarLibroRecursivo(nodo.izquierdo, titulo); // Buscar en el subárbol izquierdo
            Libro encontradoDerecha = buscarLibroRecursivo(nodo.derecho, titulo); // Buscar en el subárbol derecho
            return (encontradoIzquierda != null) ? encontradoIzquierda : encontradoDerecha;
        }
    }



    // Otros métodos para buscar, eliminar, etc. según las necesidades
}

class NodoArbolLibro {
    Libro libro;
    NodoArbolLibro izquierdo;
    NodoArbolLibro derecho;

    public NodoArbolLibro(Libro libro) {
        this.libro = libro;
        izquierdo = null;
        derecho = null;
    }

    public void insertar(Libro nuevoLibro) {
        if (nuevoLibro.getTitulo().compareTo(libro.getTitulo()) < 0) {
            if (izquierdo == null) {
                izquierdo = new NodoArbolLibro(nuevoLibro);
            } else {
                izquierdo.insertar(nuevoLibro);
            }
        } else if (nuevoLibro.getTitulo().compareTo(libro.getTitulo()) > 0) {
            if (derecho == null) {
                derecho = new NodoArbolLibro(nuevoLibro);
            } else {
                derecho.insertar(nuevoLibro);
            }
        } else {
            // El libro ya existe, puedes manejar este caso según tus necesidades
        }
    }
}
