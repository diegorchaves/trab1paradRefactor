import java.time.LocalDate;

public class Presenca {
    LocalDate data;
    String cpf;
    String nomeExercicio;
    double cargaExercicio;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeExercicio() {
        return nomeExercicio;
    }

    public void setNomeExercicio(String nomeExercicio) {
        this.nomeExercicio = nomeExercicio;
    }

    public double getCargaExercicio() {
        return cargaExercicio;
    }

    public void setCargaExercicio(double cargaExercicio) {
        this.cargaExercicio = cargaExercicio;
    }

    public String toString() {
        return("Voce treinou no dia " + data + " o exercicio " + nomeExercicio +
                " com carga " + cargaExercicio + " kg.");
    }
}
