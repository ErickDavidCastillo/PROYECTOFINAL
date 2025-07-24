package controlador;

import modelo.Curso;
import modelo.Inscripcion;
import persistencia.ConexionDB;

import java.sql.*;
import java.util.ArrayList;

public class ControladorInscripcion {

    public static void agregarInscripcion(Inscripcion inscripcion) {
        String sql = "INSERT INTO inscripcion (nombre, apellido, edad, telefono, curso_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, inscripcion.getNombre());
            stmt.setString(2, inscripcion.getApellido());
            stmt.setInt(3, inscripcion.getEdad());
            stmt.setString(4, inscripcion.getTelefono());
            stmt.setInt(5, inscripcion.getCurso().getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al guardar inscripción: " + e.getMessage());
        }
    }

    public static void eliminarInscripcion(String nombreCompleto) {
        String sql = "DELETE FROM inscripcion WHERE CONCAT(nombre, ' ', apellido) = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreCompleto);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar inscripción: " + e.getMessage());
        }
    }

    public static ArrayList<Inscripcion> obtenerInscripciones() {
        ArrayList<Inscripcion> lista = new ArrayList<>();
        String sql = """
            SELECT i.nombre, i.apellido, i.edad, i.telefono,
                   c.id AS curso_id, c.nombre AS curso_nombre, c.horario
            FROM inscripcion i
            JOIN curso c ON i.curso_id = c.id
        """;

        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int edad = rs.getInt("edad");
                String telefono = rs.getString("telefono");

                int cursoId = rs.getInt("curso_id");
                String cursoNombre = rs.getString("curso_nombre");
                String horario = rs.getString("horario");

                Curso curso = new Curso(cursoId, cursoNombre, horario);
                Inscripcion ins = new Inscripcion(nombre, apellido, edad, telefono, curso);
                lista.add(ins);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener inscripciones: " + e.getMessage());
        }

        return lista;
    }
}
