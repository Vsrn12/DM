/*
 * Descripción: Implementación del juego Piedra, Papel o Tijera contra la computadora
 * La computadora elige aleatoriamente y determina el ganador
 * Autor: Delgado Chipana Piero Adrian
 * Fecha creación: 25/08/25
 * Fecha última modificación: 25/08/25
 */

import kotlin.random.Random

fun main() {
    println("=== Juego: Piedra, Papel o Tijera ===")
    
    do {
        jugarRonda()
        println("\n¿Desea jugar otra ronda? (s/n)")
    } while (readln().lowercase().startsWith("s"))
    
    println("¡Gracias por jugar!")
}

/**
 * Ejecuta una ronda completa del juego
 */
fun jugarRonda() {
    val opciones = listOf("piedra", "papel", "tijera")
    val eleccionComputadora = obtenerEleccionComputadora(opciones)
    val eleccionUsuario = obtenerEleccionUsuario(opciones)
    
    mostrarElecciones(eleccionUsuario, eleccionComputadora)
    val resultado = determinarGanador(eleccionUsuario, eleccionComputadora)
    mostrarResultado(resultado)
}

/**
 * Genera la elección aleatoria de la computadora
 */
fun obtenerEleccionComputadora(opciones: List<String>): String {
    return opciones[Random.nextInt(opciones.size)]
}

/**
 * Solicita y valida la elección del usuario
 */
fun obtenerEleccionUsuario(opciones: List<String>): String {
    while (true) {
        println("\nElige tu opción:")
        opciones.forEachIndexed { index, opcion -> 
            println("${index + 1}. ${opcion.capitalize()}")
        }
        print("Tu elección (1-3): ")
        
        try {
            val eleccion = readln().toInt()
            if (eleccion in 1..3) {
                return opciones[eleccion - 1]
            } else {
                println("Error: Selecciona una opción válida (1-3)")
            }
        } catch (e: NumberFormatException) {
            print("Ingresa el nombre de la opción: ")
            val entrada = readln().lowercase().trim()
            if (entrada in opciones) {
                return entrada
            } else {
                println("Error: Opción no válida. Usa: piedra, papel o tijera")
            }
        }
    }
}

/**
 * Determina el ganador basado en las reglas del juego
 */
fun determinarGanador(usuario: String, computadora: String): String {
    return when {
        usuario == computadora -> "Empate"
        (usuario == "piedra" && computadora == "tijera") ||
        (usuario == "papel" && computadora == "piedra") ||
        (usuario == "tijera" && computadora == "papel") -> "Ganaste"
        else -> "Perdiste"
    }
}

/**
 * Muestra las elecciones de ambos jugadores
 */
fun mostrarElecciones(usuario: String, computadora: String) {
    println("\n--- Elecciones ---")
    println("Tú elegiste: ${usuario.capitalize()}")
    println("Computadora eligió: ${computadora.capitalize()}")
}

/**
 * Muestra el resultado final con mensaje personalizado
 */
fun mostrarResultado(resultado: String) {
    println("\n--- Resultado ---")
    val mensaje = when (resultado) {
        "Ganaste" -> "🎉 ¡Felicidades! ¡Ganaste esta ronda!"
        "Perdiste" -> "😔 La computadora ganó esta vez"
        "Empate" -> "🤝 Es un empate, ¡están igualados!"
        else -> "Error en el resultado"
    }
    println(mensaje)
}