import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Aluno> alunoList = new ArrayList<>();
        Impressao impressao = new Impressao();
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        Connection conexao = conexaoBanco.getConexao();
        ManipuladorAlunos manipuladorAlunos = new ManipuladorAlunos(conexao);
        Scanner entrada = new Scanner(System.in);
        Integer opcao = 0;

        do {
            System.out.println("Digite a opcao desejada: ");
            System.out.println("0 - Encerrar programa.");
            System.out.println("1 - Cadastrar aluno.");
            System.out.println("2 - Remover aluno.");
            System.out.println("3 - Alterar aluno.");
            System.out.println("4 - Buscar aluno.");
            opcao = entrada.nextInt();
            entrada.nextLine();
            switch (opcao) {

                case 1:
                    Aluno aluno = new Aluno();
                    aluno.getDadosAluno(aluno);
                    manipuladorAlunos.inserirAluno(aluno);
                    break;
                case 2:
                    alunoList = manipuladorAlunos.buscarListaAlunos();
                    impressao.imprimirListaAlunos(alunoList);
                    System.out.println("Digite o CPF do aluno que deseja remover:");
                    String cpf = entrada.nextLine();
                    manipuladorAlunos.removerAluno(cpf);
                    break;
                case 3:
                    Boolean encontrou = false;
                    alunoList = manipuladorAlunos.buscarListaAlunos();
                    impressao.imprimirListaAlunos(alunoList);
                    System.out.println("Digite o CPF do aluno que deseja alterar:");
                    cpf = entrada.nextLine();
                    for (Aluno aluno1 : alunoList) {
                        if (aluno1.getCpf().equals(cpf)) {
                            manipuladorAlunos.alterarAluno(cpf);
                            encontrou = true;
                            System.out.println("Aluno alterado com sucesso.");
                        }
                    }
                    if (encontrou == false)
                        System.out.println("Aluno nao encontrado.");
                    break;
                case 4:
                    encontrou = false;
                    alunoList = manipuladorAlunos.buscarListaAlunos();
                    System.out.println("Digite o nome ou cpf do aluno que deseja buscar:");
                    String busca = entrada.nextLine();
                    for (Aluno aluno1 : alunoList) {
                        if (aluno1.getCpf().equals(busca) || aluno1.getNome().equals(busca)) {
                            System.out.println("Aluno encontrado, dados: ");
                            System.out.println(aluno1.toString());
                            encontrou = true;
                        }
                    }
                    if(encontrou == false)
                        System.out.println("Aluno não encontrado.");
                    break;
            }
        } while(opcao != 0);
    }
}
