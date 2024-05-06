import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManipuladorPlanosAtivos {

    private Connection conexao;

    public ManipuladorPlanosAtivos(Connection conexao) {
        this.conexao = conexao;
    }
    public List<PlanoAtivo> buscarListaPlanosAtivos() {
        String sql = "SELECT * FROM planosativos";
        List<PlanoAtivo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                java.sql.Date inicio = (Date) resultado.getObject("inicio");
                java.sql.Date fim = (Date) resultado.getObject("fim");
                PlanoAtivo planoAtivo = new PlanoAtivo();
                planoAtivo.setPlano(resultado.getString("plano"));
                planoAtivo.setAlunoCpf(resultado.getString("alunocpf"));
                planoAtivo.setInicio(inicio.toLocalDate());
                planoAtivo.setFim(fim.toLocalDate());
                retorno.add(planoAtivo);
            }
        } catch(SQLException e) {
            System.out.println("Erro ao tentar listar os planos ativos.");
        }
        return retorno;
    }
    public void assinarPlano(PlanoAtivo planoAtivo){
        String sql = "INSERT INTO planosativos(plano, alunocpf, inicio, fim) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, planoAtivo.getPlano());
            stmt.setString(2, planoAtivo.getAlunoCpf());
            stmt.setObject(3, planoAtivo.getInicio());
            stmt.setObject(4, planoAtivo.getFim());
            stmt.execute();
            System.out.println("Plano assinado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao tentar assinar o plano." + e );
        }
    }

}
