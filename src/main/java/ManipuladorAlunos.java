import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                // Puxa a data do banco de dados no formato (yyyy-MM-dd)
                String dataDoBanco = resultado.getString("nascimento");
                // Converte a data para o formato (dd/MM/yyyy)
                String dataDoBancoFormatada = converterDataParaFormatoDesejado(dataDoBanco);

                aluno.setDataNascimento(dataDoBancoFormatada);
                aluno.setCartao(resultado.getString("numerocartao"));
                retorno.add(aluno);
            }
        } catch(SQLException e) {
            System.out.println("Erro ao tentar listar os alunos.");
        }
        return retorno;
    }

    private String converterDataParaFormatoDesejado(String dataDoBanco) {
        // Extrai o ano, mês e dia da string de data do banco de dados
        String[] partesData = dataDoBanco.split("-");
        String ano = partesData[0];
        String mes = partesData[1];
        String dia = partesData[2];

        // Montar a string de data no formato desejado (dd/MM/yyyy)
        return dia + "/" + mes + "/" + ano;
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
            System.out.println("Aluno inserido com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao tentar inserir o aluno." );
        }
    }

    public void alterarAluno(String cpf) {
        String sql = "UPDATE alunos SET nome=?, nascimento=?, numerocartao=? WHERE cpf =?";
        try {
            Aluno aluno = new Aluno();
            aluno.getDadosAlunoSemCpf(aluno);
            String dataString = aluno.getDataNascimento().toString();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, dataString);
            stmt.setString(3, aluno.getCartao());
            stmt.setString(4, cpf);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao tentar alterar o aluno.");
        }
    }

    public void removerAluno(String cpf) {
        String sql = "DELETE FROM alunos WHERE cpf = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            int linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas > 0)
                System.out.println("Aluno removido com sucesso.");
            else
                System.out.println("CPF incorreto.");
        } catch (SQLException e) {
            System.out.println("Erro ao tentar excluir o aluno.");
        }
    }
}
