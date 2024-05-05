import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManipuladorExercicios {

    private Connection conexao;

    public ManipuladorExercicios(Connection conexao) {
        this.conexao = conexao;
    }

    public List<Exercicio> buscarListaExercicios() {
        String sql = "SELECT * FROM exercicios";
        List<Exercicio> retorno = new ArrayList<>();

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                Exercicio exercicio = new Exercicio();
                exercicio.setNome(resultado.getString("nome"));
                exercicio.setCodigo(resultado.getInt("codigo"));
                Integer[] arrayInt = (Integer[]) resultado.getArray("musculosativados").getArray();
                exercicio.setMusculosAtivados(Arrays.asList(arrayInt));
                retorno.add(exercicio);
            }
        } catch(SQLException e) {
            System.out.println("Erro ao tentar listar os exercicios.");
        }
        return retorno;
    }

    public void inserirExercicio(Exercicio exercicio) throws SQLException {
        String sql = "INSERT INTO exercicios(nome, musculosativados) VALUES (?, ?)";
        Integer[] arrayInt = exercicio.getMusculosAtivados().toArray(new Integer[0]);
        Array array = conexao.createArrayOf("INTEGER", arrayInt);
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, exercicio.getNome());
            stmt.setArray(2, array);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao tentar inserir o exercicio. " + e);
        }
    }

    public void alterarExercicio(Integer codigo,  HashMap<Integer, String> hashMap) {
        String sql = "UPDATE exercicios SET nome=?, musculosativados=? WHERE codigo =?";
        try {
            Exercicio exercicio = new Exercicio();
            exercicio.getDadosExercicio(hashMap);

            Integer[] arrayInt = exercicio.getMusculosAtivados().toArray(new Integer[0]);
            Array array = conexao.createArrayOf("INTEGER", arrayInt);

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, exercicio.getNome());
            stmt.setArray(2, array);
            stmt.setInt(3, codigo);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao tentar alterar o plano.");
        }
    }
    public void removerExercicio(Integer codigo) {
        String sql = "DELETE FROM exercicios WHERE codigo = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            Integer linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas > 0)
                System.out.println("Exercicio removido com sucesso.");
            else
                System.out.println("Codigo incorreto.");
        } catch (SQLException e) {
            System.out.println("Erro ao tentar excluir o exercicio.");
        }
    }

}
