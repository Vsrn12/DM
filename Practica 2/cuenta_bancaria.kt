/**
 * Descripción: Clase CuentaBancaria con saldo y límite de retiro, implementa validaciones
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 01/09/25
 * Fecha última modificación: 01/09/25
 */

class CuentaBancaria {
    // Propiedades privadas con backing fields
    private var _saldo: Double = 0.0
    private var _limiteRetiro: Double = 1000.0
    
    // Getter y setter para saldo con validación
    var saldo: Double
        get() = _saldo
        set(value) {
            if (value >= 0) {
                _saldo = value
            } else {
                throw IllegalArgumentException("El saldo no puede ser negativo")
            }
        }
    
    // Getter y setter para límite de retiro con validación
    var limiteRetiro: Double
        get() = _limiteRetiro
        set(value) {
            if (value > 0) {
                _limiteRetiro = value
            } else {
                throw IllegalArgumentException("El límite de retiro debe ser mayor a cero")
            }
        }
    
    // Constructor primario
    constructor(saldoInicial: Double = 0.0, limiteRetiroInicial: Double = 1000.0) {
        this.saldo = saldoInicial
        this.limiteRetiro = limiteRetiroInicial
    }
    
    /**
     * Método para realizar retiros considerando el límite establecido
     * @param monto Cantidad a retirar
     * @return true si el retiro fue exitoso, false en caso contrario
     */
    fun retirar(monto: Double): Boolean {
        return when {
            monto <= 0 -> {
                println("Error: El monto debe ser mayor a cero")
                false
            }
            monto > limiteRetiro -> {
                println("Error: El monto excede el límite de retiro ($limiteRetiro)")
                false
            }
            monto > saldo -> {
                println("Error: Saldo insuficiente (Saldo actual: $$saldo)")
                false
            }
            else -> {
                _saldo -= monto
                println("Retiro exitoso. Nuevo saldo: $$saldo")
                true
            }
        }
    }
    
    /**
     * Método para depositar dinero en la cuenta
     * @param monto Cantidad a depositar
     */
    fun depositar(monto: Double) {
        if (monto > 0) {
            _saldo += monto
            println("Depósito exitoso. Nuevo saldo: $$saldo")
        } else {
            println("Error: El monto a depositar debe ser mayor a cero")
        }
    }
    
    /**
     * Método para mostrar información de la cuenta
     */
    fun mostrarInfo() {
        println("=== INFORMACIÓN DE CUENTA ===")
        println("Saldo actual: $$saldo")
        println("Límite de retiro: $$limiteRetiro")
    }
}

fun main() {
    println("=== SISTEMA DE CUENTA BANCARIA ===\n")
    
    try {
        // Crear cuenta con saldo inicial
        val cuenta = CuentaBancaria(5000.0, 2000.0)
        cuenta.mostrarInfo()
        
        println("\n--- Pruebas de operaciones ---")
        
        // Realizar operaciones
        cuenta.depositar(1000.0)
        cuenta.retirar(500.0)
        cuenta.retirar(3000.0) // Excede límite
        cuenta.retirar(1500.0) // Válido
        cuenta.retirar(5000.0) // Saldo insuficiente
        
        // Mostrar estado final
        println()
        cuenta.mostrarInfo()
        
    } catch (e: IllegalArgumentException) {
        println("Error en validación: ${e.message}")
    }
}