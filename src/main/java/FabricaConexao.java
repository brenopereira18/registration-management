import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FabricaConexao {
    private static Connection connection;

    public static void toConnect() {
        try {
            if (connection==null) {
                String url = "jdbc:postgresql://localhost:5432/project-mavendb";
                Properties props = new Properties();
                props.setProperty("user", "postgres");
                props.setProperty("password", "2001");
                connection = DriverManager.getConnection(url, props);
                System.out.println("Conexão realizada com sucesso.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
