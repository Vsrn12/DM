/*
 * Descripción: Juego de adivinanza donde el usuario debe adivinar un número
 * aleatorio entre 1 y 30 en máximo 5 intentos con pistas
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 25/08/25
 * Fecha última modificación: 25/08/25
 */

import kotlin.random.Random

fun main() {
    println("=== Juego: Adivina el Número ===")
    
    do {
        jugarPartida()
        println("\n¿Deseas jugar otra partida? (s/n)")
    } while (readln().lowercase().startsWith("s"))
    
    println("¡Gracias por jugar!")
}

/**
 * Ejecuta una partida completa del juego
 */
fun jugarPartida() {
    val numeroSecreto = generarNumeroAleatorio()
    val maxIntentos = 5
    var intentosRealizados = 0
    var adivinado = false
    
    mostrarInstrucciones(maxIntentos)
    
    while (intentosRealizados < maxIntentos && !adivinado) {
        intentosRealizados++
        val numeroUsuario = solicitarNumero(intentosRealizados, maxIntentos)
        
        when {
            numeroUsuario == numeroSecreto -> {
                adivinado = true
                mostrarMensajeVictoria(intentosRealizados)
            }
            intentosRealizados == maxIntentos -> {
                mostrarGameOver(numeroSecreto)
            }
            else -> {
                darPista(numeroUsuario, numeroSecreto, intentosRealizados, maxIntentos)
            }
        }
    }
}

/**
 * Genera un número aleatorio entre 1 y 30
 */
fun generarNumeroAleatorio(): Int {
    return Random.nextInt(1, 31)
}

/**
 * Muestra las instrucciones del juego
 */
fun mostrarInstrucciones(maxIntentos: Int) {
    println("\n🎯 ¡He pensado un número entre 1 y 30!")
    println("💪 Tienes $maxIntentos intentos para adivinarlo")
    println("🧭 Te daré pistas para ayudarte")
    println("¡Buena suerte!")
}

/**
 * Solicita un número al usuario con validación
 */
fun solicitarNumero(intento: Int, maxIntentos: Int): Int {
    while (true) {
        print("\nIntento $intento/$maxIntentos - Ingresa tu número (1-30): ")
        try {
            val numero = readln().toInt()
            if (numero in 1..30) {
                return numero
            } else {
                println("❌ Error: El número debe estar entre 1 y 30")
            }
        } catch (e: NumberFormatException) {
            println("❌ Error: Ingresa un número válido")
        }
    }
}

/**
 * Proporciona pistas al usuario sobre su intento
 */
fun darPista(numeroUsuario: Int, numeroSecreto: Int, intentoActual: Int, maxIntentos: Int) {
    val diferencia = kotlin.math.abs(numeroUsuario - numeroSecreto)
    val intentosRestantes = maxIntentos - intentoActual
    
    val pista = when {
        numeroUsuario < numeroSecreto -> "📈 El número a adivinar es MAYOR que $numeroUsuario"
        numeroUsuario > numeroSecreto -> "📉 El número a adivinar es MENOR que $numeroUsuario"
        else -> ""
    }
    
    val proximidad = when {
        diferencia <= 2 -> "🔥 ¡Estás muy cerca!"
        diferencia <= 5 -> "🌡️  Estás cerca"
        diferencia <= 10 -> "❄️  Estás frío"
        else -> "🧊 Estás muy frío"
    }
    
    println("$pista")
    println("$proximidad")
    println("⏰ Te quedan $intentosRestantes intentos")
}

/**
 * Muestra mensaje de victoria cuando el usuario acierta
 */
fun mostrarMensajeVictoria(intentos: Int) {
    println("\n🎉 ¡FELICIDADES! ¡Adivinaste el número!")
    
    val mensaje = when (intentos) {
        1 -> "🏆 ¡Increíble! Lo adivinaste en el primer intento"
        2 -> "🥇 ¡Excelente! Solo necesitaste $intentos intentos"
        3 -> "🥈 ¡Muy bien! Lo lograste en $intentos intentos"
        4 -> "🥉 ¡Bien hecho! Adivinaste en $intentos intentos"
        else -> "🎯 ¡Lo lograste! Necesitaste $intentos intentos"
    }
    
    println(mensaje)
    println("🌟 ¡Eres muy bueno en este juego!")
}

/**
 * Muestra mensaje de game over cuando se agotan los intentos
 */
fun mostrarGameOver(numeroSecreto: Int) {
    println("\n💀 GAME OVER 💀")
    println("❌ Se acabaron tus intentos")
    println("🔢 El número secreto era: $numeroSecreto")
    println("🤔 ¡Mejor suerte la próxima vez!")
}