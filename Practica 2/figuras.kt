/**
 * Descripción: Sistema de figuras geométricas con clase abstracta Shape y subclases
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 01/09/25
 * Fecha última modificación: 01/09/25
 */

import kotlin.math.PI

/**
 * Clase abstracta base para todas las figuras geométricas
 */
abstract class Shape {
    // Propiedades abstractas que deben ser implementadas por las subclases
    abstract val area: Double
    abstract val perimetro: Double
    
    /**
     * Método abstracto para calcular el área
     */
    abstract fun calcularArea(): Double
    
    /**
     * Método abstracto para calcular el perímetro
     */
    abstract fun calcularPerimetro(): Double
    
    /**
     * Método para imprimir el área
     */
    fun imprimirArea() {
        println("Área: ${String.format("%.2f", calcularArea())} unidades²")
    }
    
    /**
     * Método para imprimir el perímetro
     */
    fun imprimirPerimetro() {
        println("Perímetro: ${String.format("%.2f", calcularPerimetro())} unidades")
    }
    
    /**
     * Método para imprimir información completa de la figura
     */
    open fun imprimirInfo() {
        println("=== ${this::class.simpleName?.uppercase()} ===")
        imprimirArea()
        imprimirPerimetro()
    }
}

/**
 * Clase Cuadrado que hereda de Shape
 */
class Cuadrado : Shape {
    var lado: Double = 0.0
        private set
    
    // Constructor primario
    constructor(lado: Double) {
        if (lado > 0) {
            this.lado = lado
        } else {
            throw IllegalArgumentException("El lado debe ser mayor a cero")
        }
    }
    
    // Constructor secundario (alternativo)
    constructor() : this(1.0)
    
    override val area: Double
        get() = calcularArea()
    
    override val perimetro: Double
        get() = calcularPerimetro()
    
    override fun calcularArea(): Double = lado * lado
    
    override fun calcularPerimetro(): Double = 4 * lado
    
    override fun imprimirInfo() {
        super.imprimirInfo()
        println("Lado: $lado unidades")
    }
}

/**
 * Clase Círculo que hereda de Shape
 */
class Circulo : Shape {
    var radio: Double = 0.0
        private set
    
    // Constructor primario
    constructor(radio: Double) {
        if (radio > 0) {
            this.radio = radio
        } else {
            throw IllegalArgumentException("El radio debe ser mayor a cero")
        }
    }
    
    // Constructor secundario (alternativo)
    constructor() : this(1.0)
    
    override val area: Double
        get() = calcularArea()
    
    override val perimetro: Double
        get() = calcularPerimetro()
    
    override fun calcularArea(): Double = PI * radio * radio
    
    override fun calcularPerimetro(): Double = 2 * PI * radio
    
    override fun imprimirInfo() {
        super.imprimirInfo()
        println("Radio: $radio unidades")
    }
}

/**
 * Clase Rectángulo que hereda de Shape
 */
class Rectangulo : Shape {
    var largo: Double = 0.0
        private set
    var ancho: Double = 0.0
        private set
    
    // Constructor primario
    constructor(largo: Double, ancho: Double) {
        if (largo > 0 && ancho > 0) {
            this.largo = largo
            this.ancho = ancho
        } else {
            throw IllegalArgumentException("El largo y ancho deben ser mayores a cero")
        }
    }
    
    // Constructor secundario (rectángulo cuadrado)
    constructor(lado: Double) : this(lado, lado)
    
    // Constructor secundario (valores por defecto)
    constructor() : this(1.0, 1.0)
    
    override val area: Double
        get() = calcularArea()
    
    override val perimetro: Double
        get() = calcularPerimetro()
    
    override fun calcularArea(): Double = largo * ancho
    
    override fun calcularPerimetro(): Double = 2 * (largo + ancho)
    
    override fun imprimirInfo() {
        super.imprimirInfo()
        println("Largo: $largo unidades")
        println("Ancho: $ancho unidades")
    }
}

fun main() {
    println("=== SISTEMA DE FIGURAS GEOMÉTRICAS ===\n")
    
    try {
        // Crear instancias usando diferentes constructores
        val cuadrado = Cuadrado(5.0)
        val cuadradoDefault = Cuadrado()
        
        val circulo = Circulo(3.0)
        val circuloDefault = Circulo()
        
        val rectangulo = Rectangulo(8.0, 6.0)
        val rectanguloCuadrado = Rectangulo(4.0)
        val rectanguloDefault = Rectangulo()
        
        // Lista de todas las figuras
        val figuras: List<Shape> = listOf(
            cuadrado, cuadradoDefault,
            circulo, circuloDefault,
            rectangulo, rectanguloCuadrado, rectanguloDefault
        )
        
        // Ejecutar operaciones para cada figura
        figuras.forEachIndexed { index, figura ->
            println("--- FIGURA ${index + 1} ---")
            figura.imprimirInfo()
            println()
        }
        
        // Resumen de áreas y perímetros
        println("=== RESUMEN COMPARATIVO ===")
        figuras.forEach { figura ->
            println("${figura::class.simpleName}: Área=${String.format("%.2f", figura.area)}, " +
                   "Perímetro=${String.format("%.2f", figura.perimetro)}")
        }
        
        // Encontrar la figura con mayor área
        val figuraConMayorArea = figuras.maxByOrNull { it.area }
        println("\nFigura con mayor área: ${figuraConMayorArea?.let { 
            "${it::class.simpleName} (${String.format("%.2f", it.area)} unidades²)" 
        }}")
        
    } catch (e: IllegalArgumentException) {
        println("Error en validación: ${e.message}")
    }
}