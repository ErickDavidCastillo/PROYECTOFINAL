package vista;

import controlador.ControladorCurso;
import controlador.ControladorInscripcion;
import modelo.Curso;
import modelo.Inscripcion;

import javax.swing.*;
import java.util.ArrayList;

public class UsuarioForm extends JFrame {
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEdad;
    private JTextField txtTelefono;
    private JComboBox<Curso> comboCursos;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnRegresar;
    private JPanel rootPanel;

    public UsuarioForm() {
        setTitle("Formulario de Inscripción");
        setContentPane(rootPanel);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        inicializarCursos();

        btnGuardar.addActionListener(e -> guardarInscripcion());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnRegresar.addActionListener(e -> {
            new Inicio().setVisible(true);
            dispose();
        });
    }

    private void inicializarCursos() {
        comboCursos.removeAllItems();
        ArrayList<Curso> cursos = ControladorCurso.obtenerCursos();
        for (Curso curso : cursos) {
            comboCursos.addItem(curso);
        }
    }

    private void guardarInscripcion() {
        try {
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            int edad = Integer.parseInt(txtEdad.getText().trim());
            String telefono = txtTelefono.getText().trim();
            Curso curso = (Curso) comboCursos.getSelectedItem();

            if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || curso == null) {
                JOptionPane.showMessageDialog(this, "Completa todos los campos.");
                return;
            }

            Inscripcion inscripcion = new Inscripcion(nombre, apellido, edad, telefono, curso);
            ControladorInscripcion.agregarInscripcion(inscripcion);
            JOptionPane.showMessageDialog(this, "Inscripción guardada.");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Edad inválida.");
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtTelefono.setText("");
        comboCursos.setSelectedIndex(0);
    }
}
