import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {
    // Método para validar una contraseña
    public static String ingresarContrasenaValida(Scanner scanner) {
        boolean contrasenaValida = false;
        String contrasena = "";

        while (!contrasenaValida) {
            System.out.println("Ingrese una contraseña:");
            contrasena = scanner.nextLine();

            if (validarContrasena(contrasena)) {
                contrasenaValida = true;
            } else {
                System.out.println("La contraseña no es válida. Por favor, vuelva a intentarlo.");
            }
        }

        return contrasena;
    }
    public static boolean validarContrasena(String contrasena) {
        // Debe tener al menos 8 caracteres
        if (contrasena.length() < 8) {
            return false;
        }

        // Debe contener al menos una letra minúscula
        Pattern pattern = Pattern.compile("[a-z]");
        Matcher matcher = pattern.matcher(contrasena);
        if (!matcher.find()) {
            return false;
        }

        // Debe contener al menos una letra mayúscula
        pattern = Pattern.compile("[A-Z]");
        matcher = pattern.matcher(contrasena);
        if (!matcher.find()) {
            return false;
        }

        // Debe contener al menos un dígito
        pattern = Pattern.compile("[0-9]");
        matcher = pattern.matcher(contrasena);
        if (!matcher.find()) {
            return false;
        }

        // Debe contener al menos un carácter especial
        pattern = Pattern.compile("[!@#$%^&*()-_=+\\\\|[{]};:'\",<.>/?]");
        matcher = pattern.matcher(contrasena);
        if (!matcher.find()) {
            return false;
        }

        // La contraseña cumple con todos los requisitos
        return true;
    }

    public static String validarCorreo(Scanner scanner) {
        boolean correoValido = false;
        String correo = "";

        while (!correoValido) {
            System.out.println("Ingrese su dirección de correo electrónico:");
            correo = scanner.nextLine();

            if (validarFormatoCorreo(correo)) {
                correoValido = true;
            } else {
                System.out.println("El formato del correo electrónico no es válido. Por favor, vuelva a intentarlo.");
            }
        }

        return correo;
    }

    public static boolean validarFormatoCorreo(String correo) {
        // Expresión regular para validar el formato de un correo electrónico
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return correo.matches(regex);
    }
    public static String validarCampoNoVacio(Scanner scanner) {
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.println("El campo no puede quedar vacío. Por favor, ingrese un valor:");
            input = scanner.nextLine().trim();
        }
        return input;
    }
    public static int leerEntero(Scanner scanner) {
        int numero = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println("Ingrese un número entero:");
            try {
                numero = scanner.nextInt();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero.");
                scanner.next(); // Limpiar el búfer de entrada
            }
        }

        return numero;
    }

}
