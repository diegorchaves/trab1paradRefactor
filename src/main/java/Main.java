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
        List<TreinoEspecificacao> treinoEspecificacaoList = new ArrayList<>();

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

        int contadorTreinos = 0;
        Integer tipoLogin = null;
        Scanner entrada = new Scanner(System.in);
        Integer opcao = 0;
        Integer opcao1 = 0;

        do{
            System.out.println("Realizar Login");
            System.out.println("0 - Encerrar.");
            System.out.println("1 - Aluno.");
            System.out.println("2 - Instrutor.");
            tipoLogin = entrada.nextInt();
            entrada.nextLine();

            if(tipoLogin == 1){ //LOGIN DE ALUNO
                do {
                    alunoList = manipuladorAlunos.buscarListaAlunos();
                    planoList = manipuladorPlanos.buscarListaPlanos();
                    exercicioList = manipuladorExercicios.buscarListaExercicios();
                    planoAtivoList = manipuladorPlanosAtivos.buscarListaPlanosAtivos();
                    treinoList = manipuladorTreinosCadastrados.buscarListaTreinos();
                    treinoEspecificacaoList = manipuladorTreinoEspecificacao.buscarListaTreinos();

                    System.out.println("Digite a opcao desejada: ");
                    System.out.println("0 - Sair.");
                    System.out.println("1 - Alunos.");
                    System.out.println("2 - Treinos.");
                    System.out.println("3 - Relatorios.");
                    opcao = entrada.nextInt();
                    entrada.nextLine();
                    switch (opcao){
                        case 1:

                            do{
                                alunoList = manipuladorAlunos.buscarListaAlunos();
                                System.out.println("0 - Voltar .");
                                System.out.println("1 - Cadastrar aluno.");
                                System.out.println("2 - Remover aluno.");
                                System.out.println("3 - Alterar aluno.");
                                System.out.println("4 - Buscar aluno.");
                                opcao1 = entrada.nextInt();
                                entrada.nextLine();

                                switch (opcao1) {

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
                                }

                            }while(opcao1 != 0);

                            break;

                        case 2:

                            do{
                                alunoList = manipuladorAlunos.buscarListaAlunos();
                                treinoList = manipuladorTreinosCadastrados.buscarListaTreinos();
                                treinoEspecificacaoList = manipuladorTreinoEspecificacao.buscarListaTreinos();
                                exercicioList = manipuladorExercicios.buscarListaExercicios();
                                System.out.println("0 - Voltar .");
                                System.out.println("1 - Iniciar Treino.");

                                opcao1 = entrada.nextInt();
                                entrada.nextLine();

                                switch (opcao1){

                                    case 1:
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
                                            Integer codigo = entrada.nextInt();
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

                            }while(opcao1 != 0);
                            break;
                        case 3:
                            do{
                                alunoList = manipuladorAlunos.buscarListaAlunos();
                                treinoList = manipuladorTreinosCadastrados.buscarListaTreinos();
                                exercicioList = manipuladorExercicios.buscarListaExercicios();
                                System.out.println("0 - Voltar .");
                                System.out.println("1 - Ver presencas.");
                                System.out.println("2 - Ver evolucao de cargas.");
                                opcao1 = entrada.nextInt();
                                entrada.nextLine();

                                switch (opcao1){
                                    case 1:
                                        String cpfAluno = "erro";
                                        impressao.imprimirLista(alunoList);
                                        System.out.println("Digite o CPF do aluno: ");
                                        cpfAluno = entrada.nextLine();
                                        for(Aluno aluno1 : alunoList) {
                                            if(aluno1.getCpf().equals(cpfAluno))
                                                System.out.println("Aluno encontrado.");
                                        }
                                        if(cpfAluno.equals("erro")) {
                                            System.out.println("CPF incorreto.");
                                        }
                                        else {
                                            manipuladorRelatorios.buscarPresencas(cpfAluno);
                                        }

                                        break;

                                    case 2:
                                        impressao.imprimirLista(alunoList);
                                        System.out.println("Digite o CPF do aluno: ");
                                        cpfAluno = entrada.nextLine();
                                        for(Aluno aluno1 : alunoList) {
                                            if(aluno1.getCpf().equals(cpfAluno))
                                                System.out.println("Aluno encontrado.");
                                        }
                                        if(cpfAluno.equals("erro")) {
                                            System.out.println("CPF incorreto.");
                                        }
                                        else {

                                            int codigo1;
                                            for (Exercicio exercicio1 : exercicioList) {
                                                exercicio1.imprimeExercicio(exercicio1);
                                            }
                                            System.out.println("Digite o codigo do exercicio que deseja consultar:");
                                            codigo1 = entrada.nextInt();
                                            for (Exercicio exercicio1 : exercicioList) {
                                                if (exercicio1.getCodigo().equals(codigo1)) {
                                                    manipuladorRelatorios.evolucaoCargas(exercicio1.getNome(), cpfAluno);
                                                }
                                            }
                                        }

                                        break;
                                }

                            }while(opcao1 != 0);
                            break;

                    }
                } while(opcao != 0);
            }else if(tipoLogin == 2){ //LOGIN INSTRUTOR
                do {
                    alunoList = manipuladorAlunos.buscarListaAlunos();
                    planoList = manipuladorPlanos.buscarListaPlanos();
                    exercicioList = manipuladorExercicios.buscarListaExercicios();
                    planoAtivoList = manipuladorPlanosAtivos.buscarListaPlanosAtivos();
                    treinoList = manipuladorTreinosCadastrados.buscarListaTreinos();
                    treinoEspecificacaoList = manipuladorTreinoEspecificacao.buscarListaTreinos();

                    System.out.println("Digite a opcao desejada: ");
                    System.out.println("0 - Sair.");
                    System.out.println("1 - Planos.");
                    System.out.println("2 - Exercicios.");
                    System.out.println("3 - Treinos.");

                    opcao = entrada.nextInt();
                    entrada.nextLine();
                    switch (opcao){

                        case 1:

                            do{
                                alunoList = manipuladorAlunos.buscarListaAlunos();
                                planoList = manipuladorPlanos.buscarListaPlanos();
                                planoAtivoList = manipuladorPlanosAtivos.buscarListaPlanosAtivos();
                                System.out.println("0 - Voltar .");
                                System.out.println("1 - Cadastrar plano.");
                                System.out.println("2 - Remover plano.");
                                System.out.println("3 - Alterar plano.");
                                System.out.println("4 - Listar planos.");
                                System.out.println("5 - Assinar plano");
                                System.out.println("6 - Imprimir planos ativos.");
                                opcao1 = entrada.nextInt();
                                entrada.nextLine();

                                switch (opcao1){
                                    case 1:
                                        Plano plano = new Plano();
                                        plano.getDadosPlano();
                                        manipuladorPlanos.inserirPlano(plano);
                                        break;
                                    case 2:
                                        impressao.imprimirLista(planoList);
                                        System.out.println("Digite o codigo do plano que deseja remover:");
                                        Integer codigo = entrada.nextInt();
                                        manipuladorPlanos.removerPlano(codigo);
                                        break;
                                    case 3:
                                        Boolean encontrou = false;
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
                                    case 4:
                                        encontrou = false;
                                        for (Plano plano1 : planoList) {
                                            System.out.println(plano1);
                                            encontrou = true;
                                        }
                                        if(!encontrou)
                                            System.out.println("Sem planos.");
                                        break;
                                    case 5:
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
                                    case 6:
                                        impressao.imprimirLista(planoAtivoList);
                                        break;
                                }

                            }while(opcao1 != 0);
                            break;
                        case 2:
                            do{
                                exercicioList = manipuladorExercicios.buscarListaExercicios();
                                System.out.println("0 - Voltar .");
                                System.out.println("1 - Cadastrar exercício.");
                                System.out.println("2 - Remover exercício.");
                                System.out.println("3 - Alterar exercício.");
                                System.out.println("4 - Buscar exercício.");
                                opcao1 = entrada.nextInt();
                                entrada.nextLine();

                                switch (opcao1){
                                    case 1:
                                        Exercicio exercicio = new Exercicio();
                                        exercicio.getDadosExercicio(hashMapMusculos);
                                        manipuladorExercicios.inserirExercicio(exercicio);
                                        break;
                                    case 2:
                                        for (Exercicio exercicio1 : exercicioList) {
                                            exercicio1.imprimeExercicio(exercicio1);
                                        }
                                        System.out.println("Digite o codigo do exercicio que deseja remover:");
                                        Integer codigo = entrada.nextInt();
                                        manipuladorExercicios.removerExercicio(codigo);
                                        break;
                                    case 3:
                                        Boolean encontrou = false;
                                        for (Exercicio exercicio1 : exercicioList) {
                                            exercicio1.imprimeExercicio(exercicio1);
                                        }
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
                                    case 4:
                                        encontrou = false;
                                        System.out.println("Digite o código do exercicio que deseja buscar:");
                                        Integer buscaCodigo = entrada.nextInt();
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
                                }

                            }while(opcao1 != 0);
                            break;
                        case 3:

                            do{
                                alunoList = manipuladorAlunos.buscarListaAlunos();
                                treinoList = manipuladorTreinosCadastrados.buscarListaTreinos();
                                treinoEspecificacaoList = manipuladorTreinoEspecificacao.buscarListaTreinos();
                                exercicioList = manipuladorExercicios.buscarListaExercicios();
                                System.out.println("0 - Voltar .");
                                System.out.println("1 - Cadastrar novo treino.");
                                System.out.println("2 - Remover Treino.");
                                System.out.println("3 - Alterar nome Treino.");
                                System.out.println("4 - Alterar exercicios Treino.");
                                opcao1 = entrada.nextInt();
                                entrada.nextLine();

                                switch (opcao1){
                                    case 1:
                                        Integer opcao2 = 0;
                                        TreinoCadastrado treinoCadastrado = new TreinoCadastrado();
                                        treinoCadastrado.getDadosTreinoCadastrado();
                                        contadorTreinos = manipuladorTreinosCadastrados.inserirTreinoCadastrado(treinoCadastrado);
                                        treinoCadastrado.setCodigo(contadorTreinos);

                                        TreinoEspecificacao treinoEspecificacao = new TreinoEspecificacao();
                                        treinoEspecificacao.setCodigo(contadorTreinos);
                                        do{
                                            opcao2 = treinoEspecificacao.getDadosTreinoEspecificacao(exercicioList,
                                                    hashMapMusculos);
                                        }while(opcao2 != 0);

                                        manipuladorTreinoEspecificacao.inserirTreinoEspecificacao(treinoEspecificacao);

                                        break;

                                    case 2:
                                        impressao.imprimirLista(treinoList);
                                        System.out.println("Digite o codigo do treino que deseja remover:");
                                        Integer codigo = entrada.nextInt();
                                        manipuladorTreinosCadastrados.removerTreino(codigo, manipuladorTreinoEspecificacao);
                                        break;
                                    case 3:
                                        Boolean encontrou = false;
                                        impressao.imprimirLista(treinoList);
                                        // POR ENQUANTO SÓ ALTERA NOME
                                        System.out.println("Digite o codigo do treino que deseja alterar:");
                                        codigo = entrada.nextInt();
                                        for (TreinoCadastrado treinoCadastrado1 : treinoList) {
                                            if (treinoCadastrado1.getCodigo() == codigo) {
                                                manipuladorTreinosCadastrados.alterarTreino(codigo);
                                                encontrou = true;
                                                System.out.println("Treino alterado com sucesso.");
                                            }
                                        }
                                        if (!encontrou)
                                            System.out.println("Treino nao encontrado.");
                                        break;
                                    case 4:
                                        encontrou = false;
                                        impressao.imprimirLista(treinoList);
                                        System.out.println("Digite o codigo do treino que deseja alterar:");
                                        codigo = entrada.nextInt();
                                        for (TreinoEspecificacao treinoEspecificacao1 : treinoEspecificacaoList) {
                                            if(treinoEspecificacao1.getCodigo() == codigo){
                                                encontrou = true;
                                                for(Exercicio exercicio1 : exercicioList){
                                                    if(exercicio1.getCodigo() == treinoEspecificacao1.getCodigoExercicio()){
                                                        System.out.println("Exercicio: " + exercicio1.getNome() );
                                                    }
                                                }

                                                System.out.println("Deseja alterar esse exercício? 1 (Sim) 0 (Não)");
                                                Integer alterar = entrada.nextInt();

                                                if(alterar == 1){
                                                    TreinoEspecificacao treinoEspecificacaoLocal = new TreinoEspecificacao();
                                                    treinoEspecificacaoLocal.getDadosTreinoEspecificacao(exercicioList, hashMapMusculos);
                                                    manipuladorTreinoEspecificacao.alterarExerciciosTreino(codigo, treinoEspecificacaoLocal);
                                                    System.out.println("Exercicio do treino alterado com sucesso.");
                                                }
                                            }

                                        }
                                        if (!encontrou)
                                            System.out.println("Treino nao encontrado.");
                                        break;
                                }

                            }while(opcao1 != 0);
                            break;
                    }
                } while(opcao != 0);
            } else if(tipoLogin != 0){
                System.out.println("Opcao invalida.");
            }
        }while(tipoLogin != 0);

    }
}
