package vista;

import javax.swing.*;

public class Inicio extends JFrame {
    private JButton btnAdmin;
    private JButton btnUsuario;
    private JPanel rootPanel;

    public Inicio() {
        setTitle("Bienvenido al Curso Vacacional");
        setContentPane(rootPanel);           // rootPanel es el panel principal generado por el .form
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Lógica de botones
        btnAdmin.addActionListener(e -> {
            new LoginAdmin().setVisible(true);
            dispose();
        });

        btnUsuario.addActionListener(e -> {
            new UsuarioForm().setVisible(true);
            dispose();
        });
    }
}
