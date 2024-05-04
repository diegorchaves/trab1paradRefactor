import java.util.HashMap;
import java.util.List;

public class Impressao {
    public void imprimirLista(List lista) {
        for(Object objeto : lista) {
            System.out.println(objeto.toString());
        }
    }
    public void imprimirHashMap(HashMap<Integer, String> hashMap) {
        for (Integer key : hashMap.keySet()) {
            System.out.println("Chave: " + key + ", Valor: " + hashMap.get(key));
        }
    }
}
