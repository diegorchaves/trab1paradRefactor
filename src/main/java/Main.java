import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        MusculosDisponiveis musculosDisponiveis = new MusculosDisponiveis();
        HashMap<Integer, String> hashMapMusculos = musculosDisponiveis.getHashMapMusculos();

        List<Aluno> alunoList = new ArrayList<>();
        List<Plano> planoList = new ArrayList<>();
        List<Exercicio> exercicioList = new ArrayList<>();
        List<PlanoAtivo> planoAtivoList = new ArrayList<>();
        List<TreinoCadastrado> treinoList = new ArrayList<>();

        Impressao impressao = new Impressao();
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        Connection conexao = conexaoBanco.getConexao();

        ManipuladorAlunos manipuladorAlunos = new ManipuladorAlunos(conexao);
        ManipuladorPlanos manipuladorPlanos = new ManipuladorPlanos(conexao);
        ManipuladorExercicios manipuladorExercicios = new ManipuladorExercicios(conexao);
        ManipuladorPlanosAtivos manipuladorPlanosAtivos = new ManipuladorPlanosAtivos(conexao);
        ManipuladorTreinosCadastrados manipuladorTreinosCadastrados = new ManipuladorTreinosCadastrados(conexao);
        ManipuladorTreinoEspecificacao manipuladorTreinoEspecificacao = new ManipuladorTreinoEspecificacao(conexao);
        ManipuladorRelatorios manipuladorRelatorios = new ManipuladorRelatorios(conexao);

        int contadorTreinos = 1;

        Scanner entrada = new Scanner(System.in);
        Integer opcao = 0;

        do {
            alunoList = manipuladorAlunos.buscarListaAlunos();
            planoList = manipuladorPlanos.buscarListaPlanos();
            exercicioList = manipuladorExercicios.buscarListaExercicios();
            planoAtivoList = manipuladorPlanosAtivos.buscarListaPlanosAtivos();
            treinoList = manipuladorTreinosCadastrados.buscarListaTreinos();

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
            System.out.println("9 - Cadastrar exercício.");
            System.out.println("10 - Remover exercício.");
            System.out.println("11 - Alterar exercício.");
            System.out.println("12 - Buscar exercício.");
            System.out.println("13 - Associar plano");
            System.out.println("14 - Imprimir planos ativos.");
            System.out.println("15 - Cadastrar novo treino.");
            System.out.println("16 - Iniciar Treino.");
            opcao = entrada.nextInt();
            entrada.nextLine();
            switch (opcao) {

                case 1:
                    Aluno aluno = new Aluno();
                    aluno.getDadosAluno();
                    manipuladorAlunos.inserirAluno(aluno);
                    break;
                case 2:
                    impressao.imprimirLista(alunoList);
                    System.out.println("Digite o CPF do aluno que deseja remover:");
                    String cpf = entrada.nextLine();
                    manipuladorAlunos.removerAluno(cpf);
                    break;
                case 3:
                    Boolean encontrou = false;
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
                    if (!encontrou)
                        System.out.println("Aluno nao encontrado.");
                    break;
                case 4:
                    encontrou = false;
                    System.out.println("Digite o nome ou cpf do aluno que deseja buscar:");
                    String busca = entrada.nextLine();
                    for (Aluno aluno1 : alunoList) {
                        if (aluno1.getCpf().equals(busca) || aluno1.getNome().equals(busca)) {
                            System.out.println("Aluno encontrado, dados: ");
                            System.out.println(aluno1);
                            encontrou = true;
                        }
                    }
                    if(!encontrou)
                        System.out.println("Aluno não encontrado.");
                    break;
                case 5:
                    Plano plano = new Plano();
                    plano.getDadosPlano();
                    manipuladorPlanos.inserirPlano(plano);
                    break;
                case 6:
                    impressao.imprimirLista(planoList);
                    System.out.println("Digite o codigo do plano que deseja remover:");
                    Integer codigo = entrada.nextInt();
                    manipuladorPlanos.removerPlano(codigo);
                    break;
                case 7:
                    encontrou = false;
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
                    if (!encontrou)
                        System.out.println("Plano nao encontrado.");
                    break;
                case 8:
                    encontrou = false;
                    System.out.println("Digite o codigo do plano que deseja buscar:");
                    Integer buscaCodigo = entrada.nextInt();
                    for (Plano plano1 : planoList) {
                        if (plano1.getCodigo().equals(buscaCodigo) ) {
                            System.out.println("Plano encontrado, dados: ");
                            System.out.println(plano1);
                            encontrou = true;
                        }
                    }
                    if(!encontrou)
                        System.out.println("Plano não encontrado.");
                    break;
                case 9:
                    Exercicio exercicio = new Exercicio();
                    exercicio.getDadosExercicio(hashMapMusculos);
                    manipuladorExercicios.inserirExercicio(exercicio);
                    break;
                case 10:
                    impressao.imprimirLista(exercicioList);
                    System.out.println("Digite o codigo do exercicio que deseja remover:");
                    codigo = entrada.nextInt();
                    manipuladorExercicios.removerExercicio(codigo);
                    break;
                case 11:
                    encontrou = false;
                    impressao.imprimirLista(exercicioList);
                    System.out.println("Digite o codigo do exercicio que deseja alterar:");
                    codigo = entrada.nextInt();
                    for (Exercicio exercicio1 : exercicioList) {
                        if (exercicio1.getCodigo().equals(codigo)) {
                            manipuladorExercicios.alterarExercicio(codigo, hashMapMusculos);
                            encontrou = true;
                            System.out.println("Exercicio alterado com sucesso.");
                        }
                    }
                    if (!encontrou)
                        System.out.println("Exercicio não encontrado.");
                    break;
                case 12:
                    encontrou = false;
                    System.out.println("Digite o código do exercicio que deseja buscar:");
                    buscaCodigo = entrada.nextInt();
                    for (Exercicio exercicio1 : exercicioList) {
                        if (exercicio1.getCodigo().equals(buscaCodigo) ) {
                            System.out.println("Exercicio encontrado, dados: ");
                            System.out.println(exercicio1.toString(hashMapMusculos));
                            encontrou = true;
                        }
                    }
                    if(!encontrou)
                        System.out.println("Exercicio não encontrado.");
                    break;
                case 13:
                    PlanoAtivo planoAtivo = new PlanoAtivo();
                    String alunoLocal;
                    String planoLocal;
                    encontrou = false;

                    impressao.imprimirLista(alunoList);
                    System.out.println("Informe o nome ou cpf do aluno: ");
                    alunoLocal = entrada.nextLine();

                    for(Aluno aluno1 : alunoList){
                        if(aluno1.getCpf().equals(alunoLocal) || aluno1.getNome().equals(alunoLocal)){
                            encontrou = true;
                            alunoLocal = aluno1.getCpf();
                        }
                    }
                    if (!encontrou){
                        System.out.println("Aluno não encontrado");
                        break;
                    }

                    encontrou = false;
                    impressao.imprimirLista(planoList);
                    System.out.println("Informe o codigo do plano: ");
                    planoLocal = entrada.nextLine();

                    for(Plano plano1 : planoList){
                        if(plano1.getCodigo().toString().equals(planoLocal)){
                            encontrou = true;
                            planoLocal = plano1.getNome();
                        }
                    }
                    if (!encontrou){
                        System.out.println("Plano não encontrado");
                        break;
                    }

                    planoAtivo.getDadosPlanoAtivo(planoLocal, alunoLocal);

                    manipuladorPlanosAtivos.assinarPlano(planoAtivo);
                    break;
                case 14:
                    impressao.imprimirLista(planoAtivoList);
                    break;
                case 15:

                    TreinoCadastrado treinoCadastrado = new TreinoCadastrado();
                    treinoCadastrado.getDadosTreinoCadastrado();
                    contadorTreinos = manipuladorTreinosCadastrados.inserirTreinoCadastrado(treinoCadastrado);
                    treinoCadastrado.setCodigo(contadorTreinos);

                    TreinoEspecificacao treinoEspecificacao = new TreinoEspecificacao();
                    treinoEspecificacao.setCodigo(contadorTreinos);
                    treinoEspecificacao.getDadosTreinoEspecificacao(exercicioList,
                            impressao, manipuladorTreinoEspecificacao, hashMapMusculos);


                    break;
                case 16:
                    String cpfLocal = "erro";
                    impressao.imprimirLista(alunoList);
                    System.out.println("Digite o CPF do aluno: ");
                    cpfLocal = entrada.nextLine();
                    for(Aluno aluno1 : alunoList) {
                        if(aluno1.getCpf().equals(cpfLocal))
                            System.out.println("Aluno encontrado.");
                    }
                    if(cpfLocal.equals("erro")) {
                        System.out.println("CPF incorreto.");
                    }
                    else {
                        impressao.imprimirLista(treinoList);
                        System.out.println("Digite o código do treino que deseja iniciar");
                        codigo = entrada.nextInt();
                        Integer treinoLocal = 0;

                        for(TreinoCadastrado treino : treinoList){
                            if(treino.getCodigo() == codigo){
                                treinoLocal = treino.getCodigo();
                            }
                        }
                        if (treinoLocal == 0){
                            System.out.println("Treino não encontrado");
                            break;
                        }

                        if(manipuladorTreinoEspecificacao.treinando(treinoLocal, exercicioList, cpfLocal,
                                manipuladorRelatorios)){
                            System.out.println("Treino Finalizado com sucesso!");
                        }else{
                            System.out.println("Treino Finalizado com erros!");
                        }
                    }

                    break;
            }
        } while(opcao != 0);
    }
}
