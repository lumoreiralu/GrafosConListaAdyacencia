import java.util.ArrayList;
import java.util.List;

public class Vertice<T> {
    private int id;
    private List<Arco<T>> arcos; 

    public Vertice(int id) {
        this.id = id;
        this.arcos = new ArrayList<>();
    }

    public int getId() { 
        return id; 
    }

    public void agregarArco(int destino, T etiqueta) {
        // agregamos si no existe ya un arco a ese destino
        if (buscarArco(destino) == null) {
            this.arcos.add(new Arco<>(this.id, destino, etiqueta));
        }
    }

    public void borrarArco(int destino) {
        // Usamos el iterador que ya trae la ArrayList de Java
        java.util.Iterator<Arco<T>> it = this.arcos.iterator();
        while (it.hasNext()) {
            Arco<T> a = it.next();
            if (a.getVerticeDestino() == destino) {
                it.remove(); // Este remove() SÍ funciona porque es el de Java
            }
        }
    }

    public Arco<T> buscarArco(int destino) {
        for (Arco<T> a : arcos) {
            if (a.getVerticeDestino() == destino) 
                return a;
        }
        return null;
    }

    public List<Arco<T>> getArcos() {
        return arcos;
    }

    public List<Integer> getAdyacentes() {
        List<Integer> adyacentes = new ArrayList<>();
        for (Arco<T> a : arcos) {
            adyacentes.add(a.getVerticeDestino());
        }
        return adyacentes;
    }
}