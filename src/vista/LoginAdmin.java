package vista;

import persistencia.ConexionDB;
import javax.swing.*;
import java.sql.*;

public class LoginAdmin extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JButton btnIngresar;
    private JButton btnCancelar;
    private JPanel rootPanel;

    public LoginAdmin() {
        setTitle("Acceso Administrador");
        setContentPane(rootPanel);
        setSize(350, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnIngresar.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            String clave = new String(txtClave.getPassword()).trim();

            String sql = "SELECT * FROM administrador WHERE usuario = ? AND contraseña = ?";

            try (Connection conn = ConexionDB.conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, usuario);
                stmt.setString(2, clave);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    new PanelAdmin().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Credenciales inválidas.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error de conexión: " + ex.getMessage());
            }
        });

        btnCancelar.addActionListener(e -> {
            new Inicio().setVisible(true);
            dispose();
        });
    }
}
