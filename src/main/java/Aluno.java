import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Aluno {

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String cartao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            this.dataNascimento = LocalDate.parse(dataNascimento, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Erro ao fazer o parse da data de nascimento.");
        }
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", cartao='" + cartao + '\'' +
                '}';
    }

    public void getDadosAluno() {
        String nome, cpf, cartao, dataNascimento;

        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o nome do aluno: ");
        nome = entrada.nextLine();
        System.out.println("Digite o cpf do aluno: ");
        cpf = entrada.nextLine();
        System.out.println("Digite a data de nascimento no formato dd/MM/yyyy: ");
        dataNascimento = entrada.nextLine();
        System.out.println("Digite o numero do cartao: ");
        cartao = entrada.nextLine();
        this.setNome(nome);
        this.setCpf(cpf);
        this.setCartao(cartao);
        this.setDataNascimento(dataNascimento);
    }

    public void getDadosAlunoSemCpf(Aluno aluno) {
        String nome, cartao, dataNascimento;

        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o nome do aluno: ");
        nome = entrada.nextLine();
        System.out.println("Digite a data de nascimento no formato dd/MM/yyyy: ");
        dataNascimento = entrada.nextLine();
        System.out.println("Digite o numero do cartao: ");
        cartao = entrada.nextLine();
        aluno.setNome(nome);
        aluno.setCartao(cartao);
        aluno.setDataNascimento(dataNascimento);
    }
}
