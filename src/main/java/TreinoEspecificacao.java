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



    public Integer getDadosTreinoEspecificacao(List<Exercicio> exercicioList,
                                            HashMap<Integer, String> hashMapMusculos) {
        int series, repMin, repMax, descanso;
        Scanner entrada = new Scanner(System.in);
        int opcao2;
        Boolean encontrouCodExe = false;
        double carga;
            encontrouCodExe = false;
            for (Exercicio exercicio : exercicioList) {
                System.out.println(exercicio.toString(hashMapMusculos));
            }
            System.out.println("Digite o codigo do exercicio que quer inserir no treino (0 para sair): ");
            opcao2 = entrada.nextInt();
            entrada.nextLine();
            if(opcao2 == 0)
                return opcao2;
            for(Exercicio exercicio : exercicioList) {
                if(exercicio.getCodigo() == opcao2) {
                    this.setCodigoExercicio(opcao2);
                    encontrouCodExe = true;
                    break;
                }
            }
            if(encontrouCodExe == false) {
                System.out.println("Codigo do exercicio nao encontrado.");
                return opcao2;
            }
            System.out.println("Digite o numero de series");
            series = entrada.nextInt();
            this.setSeries(series);
            entrada.nextLine();
            System.out.println("Digite o numero de repeticoes minimas: ");
            repMin = entrada.nextInt();
            this.setRepMin(repMin);
            entrada.nextLine();
            System.out.println("Numero de repeticoes maximas: ");
            repMax = entrada.nextInt();
            this.setRepMax(repMax);
            System.out.println("Digite a carga: ");
            carga = entrada.nextDouble();
            this.setCarga(carga);
            System.out.println("Digite o descanso: ");
            descanso = entrada.nextInt();
            this.setDescanso(descanso);

            return opcao2;
    }

}
