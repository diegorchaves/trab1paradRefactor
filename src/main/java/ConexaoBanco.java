import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    private static ConexaoBanco instancia = null;

    private static final String URL = "jdbc:postgresql://localhost/academia";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    private Connection conexao;

    private void conexaoBanco() {
        try {
            Class.forName("org.postgresql.Driver");
            this.conexao = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch(SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao conectar ao banco de dados.");
        }
    }

    public static ConexaoBanco getInstance() {
        if (instancia == null) {
            instancia = new ConexaoBanco();
        }
        return instancia;
    }

    public Connection getConexao() {
        return conexao;
    }

    public void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão.");
            }
        }
    }
}
