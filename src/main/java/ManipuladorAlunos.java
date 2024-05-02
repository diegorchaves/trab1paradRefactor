import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManipuladorAlunos extends ManipuladorTabela{
    private Connection conexao = ConexaoBanco.getInstance().getConexao();
    public ManipuladorAlunos() {
        super();
    }

    public List<Aluno> listarAlunos() {
        String sql = "SELECT * FROM alunos";
        List<Aluno> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(resultado.getInt("id"));
                aluno.setNome(resultado.getString("nome"));
                aluno.setCpf(resultado.getString("cpf"));
                aluno.setDataNascimento(resultado.getString("nascimento"));
                aluno.setCartao(resultado.getString("numerocartao"));
                retorno.add(aluno);
            }
        } catch(SQLException e) {
            System.out.println("Erro ao tentar listar os alunos.");
        }
        return retorno;
    }

    public void inserirAluno(Aluno aluno) {
        String sql = "INSERT INTO alunos(nome, cpf, nascimento, numerocartao) VALEUS (?, ?, ?, ?)";
        String dataString = aluno.getDataNascimento().toString();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, dataString);
            stmt.setString(4, aluno.getCartao());
        } catch (SQLException e) {
            System.out.println("Erro ao tentar inserir o aluno.");
        }
    }

    public void alterarAluno(Aluno aluno) {
        String sql = "UPDATE alunos SET nome=?, cpf=?, nascimento=?, numerocartao=? WHERE id =?";
        String dataString = aluno.getDataNascimento().toString();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, dataString);
            stmt.setString(4, aluno.getCartao());
            stmt.setInt(4, aluno.getId());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao tentar alterar o aluno.");
        }
    }

    public void removerAluno(Integer id) {
        String sql = "REMOVE FROM alunos WHERE id=?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao tentar excluir o aluno.");
        }
    }
}
