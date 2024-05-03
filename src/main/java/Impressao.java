import java.util.List;

public class Impressao {
    public void imprimirLista(List lista) {
        for(Object objeto : lista) {
            System.out.println(objeto.toString());
        }
    }
}
