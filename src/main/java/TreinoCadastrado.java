import java.util.Scanner;

public class TreinoCadastrado {
    private int codigo;
    private String nome;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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

}






