# -*- coding: utf-8 -*-
"""
Created on Mon Apr 13 12:46:24 2026

@author: mrgamarra
"""

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
    lista_filtrada.ordenar_por_prioridad()
    lista_filtrada.mostrar()

    # 4. Crear lista circular
    recordatorio = ListaCircularTareas()

    # 5. Agregar SOLO tareas de prioridad ALTA a la lista circular
    print("\n=== AGREGANDO TAREAS DE ALTA PRIORIDAD ===")

    actual = lista_filtrada.cabeza
    while actual is not None:
        if actual.prioridad == "Alta":
            recordatorio.insertar(
                actual.codigo,
                actual.nombre,
                actual.materia,
                actual.fecha,
                actual.prioridad
            )
            print(f"Agregada: {actual.nombre} - {actual.prioridad}")

        actual = actual.siguiente

    print("\n=== LISTA CIRCULAR (RECORDATORIO) ===")
    recordatorio.mostrar()

    # 6. Adelantar tarea más próxima
    print("\n=== ADELANTANDO TAREA MÁS PRÓXIMA ===")
    recordatorio.adelantar_por_fecha()
    recordatorio.mostrar()

    # 7. Simular finalización de tarea
    print("\n=== ELIMINAR TAREA COMPLETADA ===")

    tarea_completada = "Taller listas"  # se puede cambiar o usar input
    print(f"Tarea completada: {tarea_completada}")

    recordatorio.eliminar(tarea_completada)

    print("\n=== LISTA CIRCULAR ACTUALIZADA ===")
    recordatorio.mostrar()