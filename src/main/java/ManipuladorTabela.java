public class ManipuladorTabela {
    private ConexaoBanco conexao;

    public ManipuladorTabela() {
        this.conexao = ConexaoBanco.getInstance();
    }
}
