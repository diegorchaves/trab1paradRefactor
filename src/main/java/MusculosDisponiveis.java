import java.util.HashMap;

public class MusculosDisponiveis {
    private HashMap<Integer, String> hashMapMusculos;
    public MusculosDisponiveis() {
        hashMapMusculos = new HashMap<>();
        hashMapMusculos.put(1, "Deltoide ");
        hashMapMusculos.put(2, "Peitoral ");
        hashMapMusculos.put(3, "Biceps ");
        hashMapMusculos.put(4, "Triceps ");
        hashMapMusculos.put(5, "Obliquos ");
        hashMapMusculos.put(6, "Abdominal ");
        hashMapMusculos.put(7, "Quadriceps ");
        hashMapMusculos.put(8, "Trapezio ");
        hashMapMusculos.put(9, "Dorsal ");
        hashMapMusculos.put(10, "Gluteo ");
        hashMapMusculos.put(11, "Isquiotibial ");
        hashMapMusculos.put(12, "Gemeos ");
    }

    public HashMap<Integer, String> getHashMapMusculos() {
        return hashMapMusculos;
    }
}
