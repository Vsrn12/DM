/*
 * Descripción: Sistema de evaluación de empleados que calcula bonificaciones
 * basadas en puntuación de rendimiento (0-10) y salario mensual
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 25/08/25
 * Fecha última modificación: 25/08/25
 */

import java.text.NumberFormat
import java.util.*

fun main() {
    println("=== Sistema de Evaluación de Empleados ===")
    
    val salario = solicitarSalario()
    val puntuacion = solicitarPuntuacion()
    
    val nivel = determinarNivel(puntuacion)
    val dineroRecibido = calcularBonificacion(salario, puntuacion)
    
    mostrarResultados(nivel, dineroRecibido)
}

/**
 * Solicita y valida el salario mensual del empleado
 */
fun solicitarSalario(): Double {
    while (true) {
        print("Ingrese el salario mensual: $")
        try {
            val salario = readln().toDouble()
            if (salario > 0) {
                return salario
            } else {
                println("Error: El salario debe ser mayor a 0")
            }
        } catch (e: NumberFormatException) {
            println("Error: Ingrese un número válido")
        }
    }
}

/**
 * Solicita y valida la puntuación del empleado (0-10)
 */
fun solicitarPuntuacion(): Int {
    while (true) {
        print("Ingrese la puntuación (0-10): ")
        try {
            val puntuacion = readln().toInt()
            if (puntuacion in 0..10) {
                return puntuacion
            } else {
                println("Error: La puntuación debe estar entre 0 y 10")
            }
        } catch (e: NumberFormatException) {
            println("Error: Ingrese un número entero válido")
        }
    }
}

/**
 * Determina el nivel de rendimiento basado en la puntuación
 */
fun determinarNivel(puntuacion: Int): String {
    return when (puntuacion) {
        in 0..3 -> "Inaceptable"
        in 4..6 -> "Aceptable"
        in 7..10 -> "Meritorio"
        else -> "Puntuación inválida"
    }
}

/**
 * Calcula la bonificación basada en el salario y la puntuación
 */
fun calcularBonificacion(salario: Double, puntuacion: Int): Double {
    return salario * (puntuacion.toDouble() / 10)
}

/**
 * Muestra los resultados formateados al usuario
 */
fun mostrarResultados(nivel: String, dinero: Double) {
    val formatoMoneda = NumberFormat.getCurrencyInstance(Locale.US)
    
    println("\n=== RESULTADOS DE EVALUACIÓN ===")
    println("Nivel de Rendimiento: $nivel")
    println("Cantidad de Dinero Recibido: ${formatoMoneda.format(dinero)}")
    println("=====================================")
}