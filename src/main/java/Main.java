import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Aluno> alunoList = new ArrayList<>();
        List<Plano> planoList = new ArrayList<>();
        List<Exercicio> exercicioList = new ArrayList<>();
        List<Musculo> musculoList = new ArrayList<>();

        Impressao impressao = new Impressao();
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        Connection conexao = conexaoBanco.getConexao();

        ManipuladorAlunos manipuladorAlunos = new ManipuladorAlunos(conexao);
        ManipuladorPlanos manipuladorPlanos = new ManipuladorPlanos(conexao);
        ManipuladorMusculos manipuladorMusculos = new ManipuladorMusculos(conexao);
        ManipuladorExercicios manipuladorExercicios = new ManipuladorExercicios(conexao);
        musculoList = manipuladorMusculos.buscarListaMusculos();

        Scanner entrada = new Scanner(System.in);
        Integer opcao = 0;

        do {
            System.out.println("Digite a opcao desejada: ");
            System.out.println("0 - Encerrar programa.");
            System.out.println("1 - Cadastrar aluno.");
            System.out.println("2 - Remover aluno.");
            System.out.println("3 - Alterar aluno.");
            System.out.println("4 - Buscar aluno.");
            System.out.println("5 - Cadastrar plano.");
            System.out.println("6 - Remover Plano.");
            System.out.println("7 - Alterar Plano.");
            System.out.println("8 - Buscar Plano.");
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
                    impressao.imprimirLista(alunoList);
                    System.out.println("Digite o CPF do aluno que deseja remover:");
                    String cpf = entrada.nextLine();
                    manipuladorAlunos.removerAluno(cpf);
                    break;
                case 3:
                    Boolean encontrou = false;
                    alunoList = manipuladorAlunos.buscarListaAlunos();
                    impressao.imprimirLista(alunoList);
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
                case 5:
                    Plano plano = new Plano();
                    plano.getDadosPlano();
                    manipuladorPlanos.inserirPlano(plano);
                    break;
                case 6:
                    planoList = manipuladorPlanos.buscarListaPlanos();
                    impressao.imprimirLista(planoList);
                    System.out.println("Digite o codigo do plano que deseja remover:");
                    Integer codigo = entrada.nextInt();
                    manipuladorPlanos.removerPlano(codigo);
                    break;
                case 7:
                    encontrou = false;
                    planoList = manipuladorPlanos.buscarListaPlanos();
                    impressao.imprimirLista(planoList);
                    System.out.println("Digite o codigo do plano que deseja alterar:");
                    codigo = entrada.nextInt();
                    for (Plano plano1 : planoList) {
                        if (plano1.getCodigo().equals(codigo)) {
                            manipuladorPlanos.alterarPlano(codigo);
                            encontrou = true;
                            System.out.println("Plano alterado com sucesso.");
                        }
                    }
                    if (encontrou == false)
                        System.out.println("Plano nao encontrado.");
                    break;
                case 8:
                    encontrou = false;
                    planoList = manipuladorPlanos.buscarListaPlanos();
                    System.out.println("Digite o codigo do plano que deseja buscar:");
                    Integer buscaCodigo = entrada.nextInt();
                    for (Plano plano1 : planoList) {
                        if (plano1.getCodigo().equals(buscaCodigo) ) {
                            System.out.println("Plano encontrado, dados: ");
                            System.out.println(plano1.toString());
                            encontrou = true;
                        }
                    }
                    if(encontrou == false)
                        System.out.println("Plano não encontrado.");
                    break;
            }
        } while(opcao != 0);
    }
}
