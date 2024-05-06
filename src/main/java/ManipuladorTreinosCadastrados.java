import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManipuladorTreinosCadastrados {

    private Connection conexao;

    public List<TreinoCadastrado> buscarListaTreinos() {
        String sql = "SELECT * FROM treinoscadastrados";
        List<TreinoCadastrado> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                TreinoCadastrado treinoCadastrado = new TreinoCadastrado();
                treinoCadastrado.setNome(resultado.getString("nome"));
                treinoCadastrado.setCodigo(resultado.getInt("codigo"));
                retorno.add(treinoCadastrado);
            }
        } catch(SQLException e) {
            System.out.println("Erro ao tentar listar os Treinos cadastrados.");
        }
        return retorno;
    }
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
