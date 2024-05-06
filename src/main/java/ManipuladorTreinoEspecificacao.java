import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManipuladorTreinoEspecificacao {
    private Connection conexao;

    public ManipuladorTreinoEspecificacao(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserirTreinoEspecificacao(TreinoEspecificacao treinoEspecificacao) {
        String sql = "INSERT INTO treinosespecificacoes (codigo, codigoexercicio, series, " +
                "repmin, repmax, carga, descanso) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, treinoEspecificacao.getCodigo());
            stmt.setInt(2, treinoEspecificacao.getCodigoExercicio());
            stmt.setInt(3, treinoEspecificacao.getSeries());
            stmt.setInt(4, treinoEspecificacao.getRepMin());
            stmt.setInt(5, treinoEspecificacao.getRepMax());
            stmt.setDouble(6, treinoEspecificacao.getCarga());
            stmt.setInt(7, treinoEspecificacao.getDescanso());
            stmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Impossivel inserir treino.");
        }
    }
}
