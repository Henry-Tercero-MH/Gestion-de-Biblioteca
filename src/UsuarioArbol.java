import java.util.ArrayList;
import java.util.List;

public class UsuarioArbol {
    private NodoArbol raiz;

    public UsuarioArbol() {
        raiz = null;
    }

    public void insertar(Usuario usuario) {
        if (raiz == null) {
            raiz = new NodoArbol(usuario);
        } else {
            raiz.insertar(usuario);
            raiz.toString();
        }
    }
    public Usuario buscar(String nombre) {
        return buscarRecursivo(raiz, nombre);
    }

    private Usuario buscarRecursivo(NodoArbol nodo, String nombre) {
        if (nodo == null) {
            return null;
        }

        if (nodo.usuario.getNombre().equals(nombre)) {
            return nodo.usuario;
        } else if (nombre.compareTo(nodo.usuario.getNombre()) < 0) {
            return buscarRecursivo(nodo.izquierdo, nombre);
        } else {
            return buscarRecursivo(nodo.derecho, nombre);
        }
    }

    // Método público para iniciar el recorrido inOrder
    public List<Usuario> inOrder() {
        List<Usuario> usuariosInOrder = new ArrayList<>();
        inOrderRecursivo(raiz, usuariosInOrder);
        return usuariosInOrder;
    }

    // Método privado recursivo para el recorrido inOrder
    private void inOrderRecursivo(NodoArbol nodo, List<Usuario> usuariosInOrder) {
        if (nodo != null) {
            inOrderRecursivo(nodo.izquierdo, usuariosInOrder);
            usuariosInOrder.add(nodo.usuario);
            inOrderRecursivo(nodo.derecho, usuariosInOrder);
        }
    }


    // Otros métodos para buscar, eliminar, etc. según las necesidades
}
class NodoArbol {
    Usuario usuario;
    NodoArbol izquierdo;
    NodoArbol derecho;

    public NodoArbol(Usuario usuario) {
        this.usuario = usuario;
        izquierdo = null;
        derecho = null;
    }

    public void insertar(Usuario nuevoUsuario) {
        if (nuevoUsuario.getNombre().compareTo(usuario.getNombre()) < 0) {
            if (izquierdo == null) {
                izquierdo = new NodoArbol(nuevoUsuario);
            } else {
                izquierdo.insertar(nuevoUsuario);
            }
        } else if (nuevoUsuario.getNombre().compareTo(usuario.getNombre()) > 0) {
            if (derecho == null) {
                derecho = new NodoArbol(nuevoUsuario);
            } else {
                derecho.insertar(nuevoUsuario);
            }
        } else {
            // Manejar el caso en el que el usuario ya existe
            // Por ejemplo, podrías lanzar una excepción o actualizar los datos del usuario existente
        }
    }

}

