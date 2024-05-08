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
            while (resultado.next()) {
                TreinoCadastrado treinoCadastrado = new TreinoCadastrado();
                treinoCadastrado.setNome(resultado.getString("nome"));
                treinoCadastrado.setCodigo(resultado.getInt("codigo"));
                retorno.add(treinoCadastrado);
            }
        } catch (SQLException e) {
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

    public void removerTreino(Integer codigo, ManipuladorTreinoEspecificacao manipuladorTreinoEspecificacao) {
        String sql = "DELETE FROM treinoscadastrados WHERE codigo = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                manipuladorTreinoEspecificacao.removerTreino(codigo);
                System.out.println("Treino removido com sucesso.");
            } else
                System.out.println("codigo incorreto.");
        } catch (SQLException e) {
            System.out.println("Erro ao tentar excluir o treino.");
        }
    }

    public void alterarTreino(Integer codigo) {
        String sql = "UPDATE treinoscadastrados SET nome=? WHERE codigo =?";
        try {
            TreinoCadastrado treinoCadastrado = new TreinoCadastrado();
            treinoCadastrado.getDadosTreinoCadastrado();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, treinoCadastrado.getNome());
            stmt.setInt(2, codigo);

            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao tentar alterar o Treino.");
        }
    }
}
