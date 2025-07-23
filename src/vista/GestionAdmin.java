package vista;

import persistencia.ConexionDB;

import javax.swing.*;
import java.sql.*;

public class GestionAdmin extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JPasswordField txtConfirmar;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnBorrar;
    private JButton btnRegresar;
    private JPanel rootPanel;

    public GestionAdmin() {
        setTitle("Gestionar Administrador");
        setContentPane(rootPanel);
        setSize(400, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnGuardar.addActionListener(e -> guardarAdministrador());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnBorrar.addActionListener(e -> borrarAdministrador());
        btnRegresar.addActionListener(e -> {
            new PanelAdmin().setVisible(true);
            dispose();
        });
    }

    private void guardarAdministrador() {
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword()).trim();
        String confirmar = new String(txtConfirmar.getPassword()).trim();

        if (usuario.isEmpty() || clave.isEmpty() || confirmar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.");
            return;
        }

        if (!clave.equals(confirmar)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.");
            return;
        }

        String sql = "INSERT INTO administrador (usuario, contraseña) VALUES (?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, clave);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Administrador guardado.");
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }

    private void borrarAdministrador() {
        String usuario = txtUsuario.getText().trim();
        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa el nombre del usuario a borrar.");
            return;
        }

        String sql = "DELETE FROM administrador WHERE usuario = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado.");
            } else {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
            }
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        txtUsuario.setText("");
        txtClave.setText("");
        txtConfirmar.setText("");
    }
}
