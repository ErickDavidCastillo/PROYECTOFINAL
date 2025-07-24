package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://bxroumhh7twmq4mhsult-mysql.services.clever-cloud.com:3306/bxroumhh7twmq4mhsult?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "ujcmibexiima4psl";
    private static final String CLAVE = "2rBrF45e1xqt0RztooAF";

    public static Connection conectar() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Conexión exitosa a MySQL (Clever Cloud)");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return conn;
    }
}
