import java.sql.Connection;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ConexaoBanco conexaoBanco = new ConexaoBanco();
        Connection conexao = conexaoBanco.getConexao();
        ManipuladorAlunos manipuladorAlunos = new ManipuladorAlunos(conexao);

        Aluno novoAluno = new Aluno();
        novoAluno.setNome("Pedro");
        novoAluno.setCpf("0301230");
        novoAluno.setCartao("132367123");
        novoAluno.setDataNascimento("07/01/2012");

        manipuladorAlunos.inserirAluno(novoAluno);

        List<Aluno> alunoList = manipuladorAlunos.buscarListaAlunos();

        for (Aluno aluno : alunoList) {
            System.out.println(aluno.toString());
        }

    }
}
