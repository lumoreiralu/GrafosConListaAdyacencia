Implementación de Estructura de Datos: Grafos en Java

- Este proyecto contiene una implementación de la estructura de datos Grafo utilizando Listas de Adyacencia. 
El diseño permite trabajar tanto con grafos dirigidos como no dirigidos, soportando arcos etiquetados mediante tipos genéricos.

Características
- Tipado Genérico: Los arcos pueden almacenar etiquetas de cualquier tipo (objetos, números, strings).

- Polimorfismo: Implementación de Grafos Dirigidos y No Dirigidos compartiendo una base lógica común.

- Orientación a Objetos: Uso de clases Vertice y Arco para una mejor encapsulación.

- Iteradores: Implementación de los métodos de la interfaz para recorrer vértices, adyacentes y arcos.

Estructura del Proyecto
El proyecto se basa en los siguientes componentes:

- Grafo<T> (Interfaz): Define el contrato con todos los métodos esenciales (agregar/borrar vértices, arcos, búsquedas, etc.).

- Arco<T>: Clase inmutable que representa la conexión entre dos nodos.

- Vertice<T>: Clase interna que gestiona su propio ID y su lista de arcos salientes.

- GrafoDirigido<T>: Implementación principal que gestiona la colección de vértices.

- GrafoNoDirigido<T>: Extensión de la clase anterior que garantiza la simetría en los arcos.

Método,Descripción
- agregarVertice(int): Añade un nuevo nodo al grafo.

- borrarVertice(int): Elimina el vértice y todos los arcos entrantes/salientes asociados.
  
- agregarArco(int, int, T): Conecta dos vértices con una etiqueta personalizada.
  
- obtenerArco(int, int): Recupera el objeto Arco entre dos nodos.
  
- obtenerAdyacentes(int): Devuelve un iterador con los IDs de los vecinos.

Ejemplo de uso: 

Crear un grafo dirigido de strings
GrafoDirigido<String> miGrafo = new GrafoDirigido<>();

Agregar vértices
miGrafo.agregarVertice(1);
miGrafo.agregarVertice(2);

Agregar un arco con etiqueta
miGrafo.agregarArco(1, 2, "Conexión A-B");

Verificar existencia
if (miGrafo.existeArco(1, 2)) {
    System.out.println("El arco existe");
}


Se optó por una representación de Listas de Adyacencia por su eficiencia en memoria para grafos dispersos.
El borrado de vértices garantiza la integridad referencial al eliminar automáticamente cualquier arco que apunte al nodo eliminado.
