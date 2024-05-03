import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

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
            opcao = entrada.nextInt();
            entrada.nextLine();
            switch (opcao) {
                case 1:
                    Aluno aluno = new Aluno();
                    aluno.getDadosAluno(aluno);
                    manipuladorAlunos.inserirAluno(aluno);
                    break;
                case 2:
                    System.out.println("Digite o CPF do aluno que deseja remover:");
                    String cpf = entrada.nextLine();
                    manipuladorAlunos.removerAluno(cpf);
                    break;
            }
        } while(opcao != 0);
    }
}
