
# Online Python - IDE, Editor, Compiler, Interpreter


class NodoTareas:
    def __init__(self, codigo, nombre, materia,fechaentrega,prioridad, next = None):
        self.codigo = codigo
        self.nombre = nombre
        self.materia = materia
        self.fechaentrega = fechaentrega
        self.prioridad = prioridad
        self.next = next

class ListaTareas:
    def __init__(self):
        self.head = None
        
    # Método para agregar elementos al final de la lista enlazada
    def insertar(self, codigo, nombre, materia,fechaentrega, prioridad):
      #Si la lista no existe, entonces se crea
        if not self.head:
            self.head = NodoTareas(codigo, nombre, materia,fechaentrega,prioridad)
            return
        #recorre toda la lista hasta llegar al final,
        #cuando temporal.next=None
        temporal = self.head
        while temporal.next:
            temporal = temporal.next
        #Cuando llega al final de la lista, enlaza el último nodo con
        #el nuevo nodo que se crea
        temporal.next = NodoTareas(codigo, nombre, materia,fechaentrega,prioridad)
        
        
      # Método para imprimir la lista de nodos
    def mostrar( self ):
        node = self.head
        while node is not None:
            print(node.codigo, node.nombre, node.materia, node.fechaentrega, node.prioridad, end =" --> ")
            node = node.next
            
    
    def filtrar_por_materia (self, materia_buscar):
        actual = self.head
        lista_filtrada = ListaTareas()
        #Se busca el dato a eliminar en la lista
        while actual is not None:
            if actual.materia == materia_buscar:
                lista_filtrada.insertar(actual.codigo, actual.nombre,
                actual.materia,actual.fechaentrega, actual.prioridad)
            actual = actual.next
        return lista_filtrada
    


if __name__ == "__main__":

    # 1. Crear lista de tareas (lista simple)
    tareas = ListaTareas()

    tareas.insertar(1, "Taller listas", "Estructura de Datos", 20260420, "Alta")
    tareas.insertar(2, "Ensayo", "Física", 20260425, "Media")
    tareas.insertar(3, "Parcial 1", "Cálculo", 20260418, "Alta")
    tareas.insertar(4, "Quiz", "Física", 20260419, "Baja")
    tareas.insertar(5, "Proyecto", "Estructura de Datos", 20260501, "Alta")
    tareas.insertar(6, "Lectura", "Física", 20260422, "Media")
    tareas.insertar(7, "Laboratorio", "Física", 20260421, "Alta")
    tareas.insertar(8, "Exposición", "Estructura de Datos", 20260423, "Media")
    tareas.insertar(9, "Actividad", "Cálculo", 20260424, "Baja")
    tareas.insertar(10, "Parcial", "Estructura de Datos", 20260426, "Alta")

    print("\n=== LISTA DE TAREAS ===")
    tareas.mostrar()
    
    # 2. Seleccionar materia (puede cambiarse o hacerse input)
    materia_buscar = "Estructura de Datos"
    print(f"\n=== TAREAS DE LA MATERIA: {materia_buscar} ===")

    # 3. Buscar tareas por materia y ordenarlas por prioridad
    lista_filtrada = tareas.filtrar_por_materia(materia_buscar)
    lista_filtrada.mostrar()
    