import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GrafoDirigido<T> implements Grafo<T> {
    private List<Vertice<T>> vertices;

    public GrafoDirigido() {
        this.vertices = new ArrayList<>();
    }

    // Método auxiliar privado para buscar el objeto Vertice por su ID
    private Vertice<T> buscarVertice(int id) {
        for (Vertice<T> v : vertices) {
            if (v.getId() == id) return v;
        }
        return null;
    }

    @Override
    public void agregarVertice(int verticeId) {
        if (!contieneVertice(verticeId)) {
            vertices.add(new Vertice<>(verticeId));
        }
    }

    @Override
    public void agregarArco(int v1, int v2, T etiqueta) {
        Vertice<T> origen = buscarVertice(v1);
        Vertice<T> destino = buscarVertice(v2);
        
        // El arco solo se agrega si ambos vértices existen
        if (origen != null && destino != null) {
            origen.agregarArco(v2, etiqueta);
        }
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return buscarVertice(verticeId) != null;
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        Vertice<T> v = buscarVertice(verticeId);
        if (v != null) {
            return v.getAdyacentes().iterator();
        }
        return new ArrayList<Integer>().iterator(); // Devuelve iterador vacío si no existe
    }

    @Override
    public int cantidadVertices() {
        return this.vertices.size();
    }

	@Override
	public void borrarVertice(int verticeId) {
		// 1. Borramos el objeto Vertice de nuestra lista
		// Usamos removeIf para encontrarlo y sacarlo
		this.vertices.removeIf(v -> v.getId() == verticeId);
	
		// 2. IMPORTANTE: Recorrer todos los DEMÁS vértices 
		// y pedirles que borren cualquier arco que vaya hacia 'verticeId'
		for (Vertice<T> v : vertices) {
			v.borrarArco(verticeId); 
		}
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		Vertice<T> origen = buscarVertice(verticeId1);
		if (origen != null) {
			origen.borrarArco(verticeId2);
		}
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		Vertice<T> origen = buscarVertice(verticeId1);
		
		// si el origen existe
		if (origen != null) {
			// pedimos al vértice que busque si tiene un arco hacia el destino
			return origen.buscarArco(verticeId2) != null;
		}
		
		return false;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		Vertice<T> origen = buscarVertice(verticeId1);
		if(origen!=null){
			Arco<T> arco = origen.buscarArco(verticeId2);
			return arco;
		}
		return null;
	}

	@Override
	public int cantidadArcos() {
		int total = 0;
		for (Vertice<T> v : vertices) {
			// Accedemos a la lista de arcos de cada vértice y sumamos su tamaño
			total += v.getArcos().size();
		}
		return total;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		List<Integer> ids = new ArrayList<>();
		for (Vertice<T> v : vertices) {
			ids.add(v.getId());
		}
		return ids.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		List<Arco<T>> todosLosArcos = new ArrayList<>();
		for(Vertice<T> v : vertices){
			todosLosArcos.addAll(v.getArcos());
		}
		return todosLosArcos.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		Vertice<T> origen = buscarVertice(verticeId);
		
		if (origen != null) {
			return origen.getArcos().iterator();
		}
		
		// Si NO existe mejor es devolver un iterador vacío 
		// en lugar de null para que el "for" que lo use no falle.
		return new ArrayList<Arco<T>>().iterator();
	}
    
   
}