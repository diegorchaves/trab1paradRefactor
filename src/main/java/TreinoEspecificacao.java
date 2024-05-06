import java.util.List;
import java.util.Scanner;
import java.util.HashMap;


public class TreinoEspecificacao {
    private int codigo;
    private int codigoExercicio;
    private int series;
    private int repMin;
    private int repMax;
    private double carga;
    private int descanso;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoExercicio() {
        return codigoExercicio;
    }

    public void setCodigoExercicio(int codigoExercicio) {
        this.codigoExercicio = codigoExercicio;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepMin() {
        return repMin;
    }

    public void setRepMin(int repMin) {
        this.repMin = repMin;
    }

    public int getRepMax() {
        return repMax;
    }

    public void setRepMax(int repMax) {
        this.repMax = repMax;
    }

    public double getCarga() {
        return carga;
    }

    public void setCarga(double carga) {
        this.carga = carga;
    }

    public int getDescanso() {
        return descanso;
    }

    public void setDescanso(int descanso) {
        this.descanso = descanso;
    }

    public void getDadosTreinoEspecificacao(TreinoEspecificacao treinoEspecificacao, List<Exercicio> exercicioList, Impressao impressao,
                                            ManipuladorTreinoEspecificacao manipuladorTreinoEspecificacao,
                                            HashMap<Integer, String> hashMapMusculos) {
        int series, repMin, repMax, descanso;
        Scanner entrada = new Scanner(System.in);
        int opcao2;
        double carga;
        do {
            for (Exercicio exercicio : exercicioList) {
                System.out.println(exercicio.toString(hashMapMusculos));
            }
            System.out.println("Digite o codigo do exercicio que quer inserir no treino (0 para sair): ");
            opcao2 = entrada.nextInt();
            if(opcao2 == 0)
                break;
            if (exercicioList.contains(opcao2))
                treinoEspecificacao.setCodigoExercicio(opcao2);
            System.out.println("Digite o numero de series");
            series = entrada.nextInt();
            treinoEspecificacao.setSeries(series);
            entrada.nextLine();
            System.out.println("Digite o numero de repeticoes minimas: ");
            repMin = entrada.nextInt();
            treinoEspecificacao.setRepMin(repMin);
            entrada.nextLine();
            System.out.println("Numero de repeticoes maximas: ");
            repMax = entrada.nextInt();
            treinoEspecificacao.setRepMax(repMax);
            System.out.println("Digite a carga: ");
            carga = entrada.nextDouble();
            treinoEspecificacao.setCarga(carga);
            System.out.println("Digite o descanso: ");
            descanso = entrada.nextInt();
            treinoEspecificacao.setDescanso(descanso);
            manipuladorTreinoEspecificacao.inserirTreinoEspecificacao(treinoEspecificacao);
        } while(opcao2 != 0);
    }
}
