package vista;

import controlador.ControladorCurso;
import modelo.Curso;

import javax.swing.*;

public class CursosForm extends JFrame {
    private JTextField txtNombre;
    private JTextField txtHorario;
    private JButton btnAgregar;
    private JButton btnLimpiar;
    private JButton btnRegresar;
    private JPanel rootPanel;

    public CursosForm() {
        setTitle("Gestión de Cursos");
        setContentPane(rootPanel);
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String horario = txtHorario.getText().trim();
            if (!nombre.isEmpty() && !horario.isEmpty()) {
                Curso nuevo = new Curso(nombre, horario);
                ControladorCurso.agregarCurso(nuevo);
                JOptionPane.showMessageDialog(this, "Curso agregado.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Completa ambos campos.");
            }
        });

        btnLimpiar.addActionListener(e -> limpiarCampos());

        btnRegresar.addActionListener(e -> {
            new PanelAdmin().setVisible(true);
            dispose();
        });
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtHorario.setText("");
    }
}
