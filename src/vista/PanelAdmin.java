package vista;

import controlador.ControladorCurso;
import controlador.ControladorInscripcion;
import modelo.Curso;
import modelo.Inscripcion;

import javax.swing.*;
import java.util.ArrayList;

public class PanelAdmin extends JFrame {
    private JButton btnVerInscripciones;
    private JButton btnEliminarInscripcion;
    private JButton btnAgregarCurso;
    private JButton btnEliminarCurso;
    private JButton btnVerCursos;
    private JButton btnRegresar;
    private JPanel rootPanel;

    public PanelAdmin() {
        setTitle("Panel de Administración");
        setContentPane(rootPanel);
        setSize(400, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnVerInscripciones.addActionListener(e -> mostrarInscripciones());

        btnEliminarInscripcion.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre completo del inscrito a eliminar:");
            if (nombre != null && !nombre.trim().isEmpty()) {
                ControladorInscripcion.eliminarInscripcion(nombre.trim());
                JOptionPane.showMessageDialog(this, "Inscripción eliminada.");
            }
        });

        btnAgregarCurso.addActionListener(e -> {
            new CursosForm().setVisible(true);
            dispose();
        });

        btnEliminarCurso.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre del curso a eliminar:");
            if (nombre != null && !nombre.trim().isEmpty()) {
                ControladorCurso.eliminarCurso(nombre.trim());
                JOptionPane.showMessageDialog(this, "Curso eliminado.");
            }
        });

        btnVerCursos.addActionListener(e -> {
            ArrayList<Curso> lista = ControladorCurso.obtenerCursos();
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay cursos registrados.");
                return;
            }
            StringBuilder sb = new StringBuilder("Lista de Cursos:\n\n");
            for (Curso c : lista) {
                sb.append("- ").append(c.getNombre()).append(" (").append(c.getHorario()).append(")\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        });

        btnRegresar.addActionListener(e -> {
            new Inicio().setVisible(true);
            dispose();
        });
    }

    private void mostrarInscripciones() {
        ArrayList<Inscripcion> lista = ControladorInscripcion.obtenerInscripciones();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay inscripciones registradas.");
            return;
        }
        StringBuilder sb = new StringBuilder("Lista de Inscripciones:\n\n");
        for (Inscripcion i : lista) {
            sb.append(i.getDatos()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }
}
