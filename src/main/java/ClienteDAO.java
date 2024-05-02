import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO() {
        try {
            Class.forName("org.postgresql.Driver");
            String DATABASE_URL = "jdbc:postgresql://localhost/academia";
            String usuario = "postgres";
            String senha = "123456";
            this.connection = DriverManager.getConnection(DATABASE_URL, usuario, senha);
        } catch(ClassNotFoundException | SQLException e) {
            System.out.println("Não foi possível conectar no banco de dados.");
        }
    }

    public List<Aluno> listarAlunos() {
        String sql = "SELECT * FROM alunos";
        List<Aluno> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(resultado.getInt("id"));
                aluno.setNome(resultado.getString("nome"));
                aluno.setCpf(resultado.getString("cpf"));
                aluno.setDataNascimento(resultado.getObject("nascimento"));
                aluno.setCartao(resultado.getString("cartao"));
                retorno.add(aluno);
            }
        } catch(SQLException e) {
            System.out.println("Erro ao tentar listar os alunos.");
        }
        return retorno;
    }
}
