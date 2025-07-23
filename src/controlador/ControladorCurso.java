package controlador;

import modelo.Curso;
import persistencia.ConexionDB;

import java.sql.*;
import java.util.ArrayList;

public class ControladorCurso {

    public static void agregarCurso(Curso curso) {
        String sql = "INSERT INTO curso (nombre, horario) VALUES (?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getHorario());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al agregar curso: " + e.getMessage());
        }
    }

    public static void eliminarCurso(String nombreCurso) {
        String sql = "DELETE FROM curso WHERE nombre = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreCurso);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar curso: " + e.getMessage());
        }
    }

    public static ArrayList<Curso> obtenerCursos() {
        ArrayList<Curso> listaCursos = new ArrayList<>();
        String sql = "SELECT id, nombre, horario FROM curso";

        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String horario = rs.getString("horario");
                listaCursos.add(new Curso(id, nombre, horario)); // Constructor con ID
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener cursos: " + e.getMessage());
        }

        return listaCursos;
    }
}
