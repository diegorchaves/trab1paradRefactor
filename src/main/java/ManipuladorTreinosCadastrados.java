import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManipuladorTreinosCadastrados {

    private Connection conexao;

    public ManipuladorTreinosCadastrados(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserirTreinoCadastrado(TreinoCadastrado treinoCadastrado, int contadorTreinos) {
        String sql = "INSERT INTO treinoscadastrados (codigo, nome) VALUES (?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, contadorTreinos);
            stmt.setString(2, treinoCadastrado.getNome());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Impossivel inserir treino.");
        }
    }
}
