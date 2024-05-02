import java.sql.Connection;

public class ManipuladorTabela {
    protected ConexaoBanco conexao;

    public ManipuladorTabela() {
        this.conexao = ConexaoBanco.getInstance();
    }

    protected Connection getConexao() {
        return conexao.getConexao();
    }
}
