import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManipuladorAlunos {

    private Connection conexao;

    public ManipuladorAlunos(Connection conexao) {
        this.conexao = conexao;
    }

    public List<Aluno> buscarListaAlunos() {
        String sql = "SELECT * FROM alunos";
        List<Aluno> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                Aluno aluno = new Aluno();
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
        String sql = "INSERT INTO alunos(nome, nascimento, numerocartao, cpf) VALUES (?, ?, ?, ?)";
        String dataString = aluno.getDataNascimento().toString();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, dataString);
            stmt.setString(3, aluno.getCartao());
            stmt.setString(4, aluno.getCpf());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao tentar inserir o aluno.");
        }
    }

    public void alterarAluno(Aluno aluno) {
        String sql = "UPDATE alunos SET nome=?, nascimento=?, numerocartao=? WHERE cpf =?";
        String dataString = aluno.getDataNascimento().toString();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, dataString);
            stmt.setString(3, aluno.getCartao());
            stmt.setString(4, aluno.getCpf());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao tentar alterar o aluno.");
        }
    }

    public void removerAluno(String cpf) {
        String sql = "REMOVE FROM alunos WHERE cpf=?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao tentar excluir o aluno.");
        }
    }
}
