import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ManipuladorRelatorios {

    private Connection conexao;

    public ManipuladorRelatorios(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserirRelatorioTreino (LocalDate data, String cpf, String exercicio, double carga) {
        String sql = "INSERT INTO relatorios(data, cpf, exercicio, carga) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setObject(1, data);
            stmt.setString(2, cpf);
            stmt.setString(3, exercicio);
            stmt.setDouble(4, carga);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir entrada no relatorio." + e);
        }
    }
}
