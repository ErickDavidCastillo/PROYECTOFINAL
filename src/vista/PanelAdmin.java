package vista;

import javax.swing.*;

public class PanelAdmin extends JFrame {
    private JButton btnVerInscripciones;
    private JButton btnEliminarInscripcion;
    private JButton btnAgregarCurso;
    private JButton btnEliminarCurso;
    private JButton btnVerCursos;
    private JButton btnRegresar;
    private JButton btnGestionAdmin;
    private JPanel rootPanel;

    public PanelAdmin() {
        setTitle("Panel de Administración");
        setContentPane(rootPanel);
        setSize(420, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnVerInscripciones.addActionListener(e -> {
            var lista = controlador.ControladorInscripcion.obtenerInscripciones();
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay inscripciones registradas.");
                return;
            }

            StringBuilder sb = new StringBuilder("Lista de Inscripciones:\n\n");
            for (var ins : lista) {
                sb.append(ins.getDatos()).append("\n");
            }

            JOptionPane.showMessageDialog(this, sb.toString());
        });

        btnEliminarInscripcion.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre completo del inscrito a eliminar:");
            if (nombre != null && !nombre.trim().isEmpty()) {
                controlador.ControladorInscripcion.eliminarInscripcion(nombre.trim());
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
                controlador.ControladorCurso.eliminarCurso(nombre.trim());
                JOptionPane.showMessageDialog(this, "Curso eliminado.");
            }
        });

        btnVerCursos.addActionListener(e -> {
            var lista = controlador.ControladorCurso.obtenerCursos();
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay cursos registrados.");
                return;
            }

            StringBuilder sb = new StringBuilder("Lista de Cursos:\n\n");
            for (var c : lista) {
                sb.append("- ").append(c.getNombre()).append(" (").append(c.getHorario()).append(")\n");
            }

            JOptionPane.showMessageDialog(this, sb.toString());
        });

        btnGestionAdmin.addActionListener(e -> {
            new GestionAdmin().setVisible(true);
            dispose();
        });

        btnRegresar.addActionListener(e -> {
            new Inicio().setVisible(true);
            dispose();
        });
    }
}
