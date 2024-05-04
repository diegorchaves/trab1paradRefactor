import java.util.List;
import java.util.Scanner;

public class Exercicio {
    private String nome;
    private Integer codigo;
    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String toString() {
        return("Codigo: " + codigo +"\tNome: "+nome);
    }

    public void getDadosExercicio(List<Integer> listaMusculos){
        Scanner entrada = new Scanner(System.in);
        Impressao impressao = new Impressao();
        Integer codigoLocal = 0;

        System.out.println("Informe o nome do Exercicio: ");
        this.setNome(entrada.nextLine());

    }
}