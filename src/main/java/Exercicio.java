import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Exercicio {
    private String nome;
    private Integer codigo;
    private List<Integer> musculosAtivados = new ArrayList<>();

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

    public void addMusculosAtivados (Integer codigo) {
        musculosAtivados.add(codigo);
    }

    public void setMusculosAtivados(List<Integer> musculosAtivados) {
        this.musculosAtivados = musculosAtivados;
    }

    public String toString(HashMap<Integer, String> hashMapMusculos) {
        return "Exercicio{" +
                "nome='" + nome + '\'' +
                ", codigo=" + codigo +
                ", musculosAtivados=" + imprimeHashMap(hashMapMusculos, musculosAtivados) +
                '}';
    }

    public StringBuilder imprimeHashMap(HashMap<Integer, String> hashMapMusculos, List<Integer> musculosAtivados) {
        StringBuilder retorno = new StringBuilder();
        for (Integer key : hashMapMusculos.keySet()) {
            if(musculosAtivados.contains(key))
                retorno.append(hashMapMusculos.get(key));
        }
        return retorno;
    }

    public List<Integer> getMusculosAtivados() {
        return musculosAtivados;
    }

    public void getDadosExercicio(HashMap<Integer, String> hashMap) {
        Scanner entrada = new Scanner(System.in);
        int opcao;
        System.out.println("Digite o nome do exercicio:");
        this.setNome(entrada.nextLine());
        do {
            System.out.println("Selecione os musculos ativados (0 para sair): ");
            for (Integer key : hashMap.keySet()) {
                System.out.println("Chave: " + key + ", Valor: " + hashMap.get(key));
            }
            opcao = entrada.nextInt();
            entrada.nextLine();
            if(opcao > 0 && opcao < 13 && !(this.musculosAtivados.contains(opcao)))
                this.musculosAtivados.add(opcao);
        } while(opcao != 0);

    }

    public void imprimeExercicio(Exercicio exercicio)
    {
        System.out.println("Codigo: " + exercicio.getCodigo() + " - " + exercicio.getNome());
    }
}