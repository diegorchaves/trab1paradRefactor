import java.util.Scanner;

public class TreinoCadastrado {
    private Integer codigo;
    private String nome;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void getDadosTreinoCadastrado() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Informe o nome do treino: ");
        this.setNome(entrada.nextLine());
    }

    @Override
    public String toString() {
        return "Treino{" +
                "nome='" + nome + '\'' +
                "codigo='" + codigo + '\'' +
                '}';
    }

}






