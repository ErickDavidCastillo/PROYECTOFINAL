package vista;

import javax.swing.*;

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

            if (usuario.equals("admin") && clave.equals("1234")) {
                new PanelAdmin().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales inválidas.");
            }
        });

        btnCancelar.addActionListener(e -> {
            new Inicio().setVisible(true);
            dispose();
        });
    }
}
