import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManipuladorTreinosCadastrados {

    private Connection conexao;

    public ManipuladorTreinosCadastrados(Connection conexao) {
        this.conexao = conexao;
    }

    public Integer inserirTreinoCadastrado(TreinoCadastrado treinoCadastrado) {
        String sql = "INSERT INTO treinoscadastrados (nome) VALUES (?) RETURNING codigo";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, treinoCadastrado.getNome());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Impossivel inserir treino." + e);
            return null;
        }
        return null;
    }
}
