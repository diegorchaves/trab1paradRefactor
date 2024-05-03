import java.util.List;

public class Impressao {
    public void imprimirListaAlunos(List<Aluno> lista) {
        for(Aluno aluno : lista) {
            System.out.println(aluno.toString());
        }
    }
}
