import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

                retorno.add(exercicio);
            }
        } catch(SQLException e) {
            System.out.println("Erro ao tentar listar os exercicios.");
        }
        return retorno;
    }

}
