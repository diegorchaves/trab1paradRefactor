import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PlanoAtivo {
    private String plano;
    private String alunoCpf;
    private LocalDate inicio;
    private LocalDate fim;

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public String getAlunoCpf() {
        return alunoCpf;
    }

    public void setAlunoCpf(String alunoCpf) {
        this.alunoCpf = alunoCpf;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public String toString() {
        return("Plano: "+plano+"\tCPF: "+alunoCpf+"\tInicio: "+inicio + "\tFim: " + fim);
    }

    public void getDadosPlanoAtivo(String plano, String alunoCpf){
        Scanner entrada = new Scanner(System.in);
        LocalDate inicioLocal = LocalDate.now();
        LocalDate fimLocal = inicioLocal.plusDays(30);


        this.setPlano(plano);
        this.setAlunoCpf(alunoCpf);
        this.setInicio(inicioLocal);
        this.setFim(fimLocal);

    }

}
