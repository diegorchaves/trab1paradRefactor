

public class Musculo {
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

    public Musculo getMusculo() {
        return this;
    }

    public String toString() {
        return("Codigo: " + codigo +"\tNome: "+nome);
    }
}