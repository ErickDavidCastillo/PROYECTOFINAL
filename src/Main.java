package vista;

import persistencia.ConexionDB;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = ConexionDB.conectar();
        if (conn != null) {
            System.out.println("¡Conectado correctamente!");
        } else {
            System.out.println("No se pudo conectar.");
        }

        // Abrir interfaz principal
        new Inicio().setVisible(true);
    }
}
