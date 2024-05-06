import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManipuladorPlanos {

    private Connection conexao;

    public ManipuladorPlanos(Connection conexao) {
        this.conexao = conexao;
    }

    public List<Plano> buscarListaPlanos() {
        String sql = "SELECT * FROM planos";
        List<Plano> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                Plano plano = new Plano();
                plano.setNome(resultado.getString("nome"));
                plano.setCodigo(resultado.getInt("codigo"));
                plano.setValorMensal(resultado.getDouble("valor"));
                retorno.add(plano);
            }
        } catch(SQLException e) {
            System.out.println("Erro ao tentar listar os planos.");
        }
        return retorno;
    }
    public void inserirPlano(Plano plano) {
        String sql = "INSERT INTO planos(nome, valor) VALUES (?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, plano.getNome());
            stmt.setDouble(2, plano.getValorMensal());
            stmt.execute();
            System.out.println("Plano inserido com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao tentar inserir o plano." );
        }
    }
    public void alterarPlano(Integer codigo) {
        String sql = "UPDATE planos SET nome=?, valor=? WHERE codigo =?";
        try {
            Plano plano = new Plano();
            plano.getDadosPlano();

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, plano.getNome());
            stmt.setDouble(2, plano.getValorMensal());
            stmt.setInt(3, codigo);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao tentar alterar o plano.");
        }
    }
    public void removerPlano(Integer codigo) {
        String sql = "DELETE FROM planos WHERE codigo = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            Integer linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas > 0)
                System.out.println("Plano removido com sucesso.");
            else
                System.out.println("Codigo incorreto.");
        } catch (SQLException e) {
            System.out.println("Erro ao tentar excluir o plano.");
        }
    }


}
