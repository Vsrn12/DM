/*
 * Descripción: Calculadora básica que realiza operaciones matemáticas fundamentales
 * (suma, resta, multiplicación, división) con menú interactivo
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 25/08/25
 * Fecha última modificación: 25/08/25
 */

fun main() {
    println("=== Calculadora Elemental ===")
    
    var continuar = true
    while (continuar) {
        mostrarMenu()
        val opcion = obtenerOpcion()
        
        when (opcion) {
            1 -> realizarOperacion("suma", ::sumar)
            2 -> realizarOperacion("resta", ::restar)
            3 -> realizarOperacion("multiplicación", ::multiplicar)
            4 -> realizarOperacion("división", ::dividir)
            5 -> {
                println("¡Gracias por usar la calculadora!")
                continuar = false
            }
        }
        
        if (continuar) {
            println("\nPresiona Enter para continuar...")
            readln()
        }
    }
}

/**
 * Muestra el menú principal de la calculadora
 */
fun mostrarMenu() {
    println("\n==== Menú ====")
    println("1. Suma")
    println("2. Resta")
    println("3. Multiplicación")
    println("4. División")
    println("5. Salir")
    println("===============")
}

/**
 * Obtiene y valida la opción del menú seleccionada por el usuario
 */
fun obtenerOpcion(): Int {
    while (true) {
        print("Selecciona una opción (1-5): ")
        try {
            val opcion = readln().toInt()
            if (opcion in 1..5) {
                return opcion
            } else {
                println("Error: Selecciona una opción válida (1-5)")
            }
        } catch (e: NumberFormatException) {
            println("Error: Ingresa un número válido")
        }
    }
}

/**
 * Solicita dos números al usuario y valida la entrada
 */
fun solicitarNumeros(): Pair<Double, Double> {
    val num1 = solicitarNumero("primer")
    val num2 = solicitarNumero("segundo")
    return Pair(num1, num2)
}

/**
 * Solicita un número específico con validación
 */
fun solicitarNumero(orden: String): Double {
    while (true) {
        print("Ingresa el $orden número: ")
        try {
            return readln().toDouble()
        } catch (e: NumberFormatException) {
            println("Error: Ingresa un número válido")
        }
    }
}

/**
 * Realiza una operación específica y muestra el resultado
 */
fun realizarOperacion(nombreOperacion: String, operacion: (Double, Double) -> Double) {
    println("\n--- $nombreOperacion ---")
    val (num1, num2) = solicitarNumeros()
    
    try {
        val resultado = operacion(num1, num2)
        mostrarResultado(nombreOperacion, num1, num2, resultado)
    } catch (e: ArithmeticException) {
        println("Error: ${e.message}")
    }
}

/**
 * Función para realizar suma
 */
fun sumar(a: Double, b: Double): Double = a + b

/**
 * Función para realizar resta
 */
fun restar(a: Double, b: Double): Double = a - b

/**
 * Función para realizar multiplicación
 */
fun multiplicar(a: Double, b: Double): Double = a * b

/**
 * Función para realizar división con validación de división por cero
 */
fun dividir(a: Double, b: Double): Double {
    if (b == 0.0) {
        throw ArithmeticException("No se puede dividir por cero")
    }
    return a / b
}

/**
 * Muestra el resultado de la operación de forma formateada
 */
fun mostrarResultado(operacion: String, num1: Double, num2: Double, resultado: Double) {
    val simbolo = when (operacion) {
        "suma" -> "+"
        "resta" -> "-"
        "multiplicación" -> "×"
        "división" -> "÷"
        else -> "="
    }
    
    println("\nResultado:")
    println("$num1 $simbolo $num2 = ${formatearResultado(resultado)}")
}

/**
 * Formatea el resultado para mostrar enteros cuando sea posible
 */
fun formatearResultado(numero: Double): String {
    return if (numero == numero.toLong().toDouble()) {
        numero.toLong().toString()
    } else {
        String.format("%.2f", numero)
    }
}