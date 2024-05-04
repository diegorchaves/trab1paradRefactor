import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManipuladorMusculos {
    private Connection conexao;

    public ManipuladorMusculos(Connection conexao) {
        this.conexao = conexao;
    }

    public List<Musculo> buscarListaMusculos() {
        String sql = "SELECT * FROM musculos";
        List<Musculo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                Musculo musculo = new Musculo();
                musculo.setNome(resultado.getString("nome"));
                musculo.setCodigo(resultado.getInt("codigo"));
                retorno.add(musculo);
            }
        } catch(SQLException e) {
            System.out.println("Erro ao tentar listar os musculos.");
        }
        return retorno;
    }
}
