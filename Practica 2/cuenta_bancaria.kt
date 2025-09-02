/*
 * Descripción: Sistema de cuenta bancaria con encapsulación, validaciones
 * y gestión de transacciones con límites de retiro configurables
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 01/09/2025
 * Fecha última modificación: 01/09/2025
 */

import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Excepción personalizada para operaciones bancarias inválidas
 */
class TransaccionInvalidaException(message: String) : Exception(message)

/**
 * Data class para representar una transacción bancaria
 */
data class Transaccion(
    val tipo: TipoTransaccion,
    val monto: Double,
    val saldoAnterior: Double,
    val saldoNuevo: Double,
    val fecha: LocalDateTime = LocalDateTime.now(),
    val descripcion: String = ""
)

/**
 * Enum para tipos de transacción
 */
enum class TipoTransaccion(val descripcion: String) {
    DEPOSITO("Depósito"),
    RETIRO("Retiro"),
    TRANSFERENCIA_ENTRADA("Transferencia recibida"),
    TRANSFERENCIA_SALIDA("Transferencia enviada")
}

/**
 * Clase que representa una cuenta bancaria con encapsulación completa
 * y validaciones robustas
 */
class CuentaBancaria(
    private val numeroCuenta: String,
    saldoInicial: Double = 0.0,
    limiteRetiroInicial: Double = 1000.0
) {
    
    companion object {
        private val formatoMoneda = NumberFormat.getCurrencyInstance(Locale.US)
        private val formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        private const val SALDO_MINIMO = 0.0
        private const val LIMITE_RETIRO_MINIMO = 100.0
        private const val LIMITE_RETIRO_MAXIMO = 50000.0
    }
    
    // Propiedades privadas para encapsulación
    private var _saldo: Double = 0.0
    private var _limiteRetiro: Double = 0.0
    private val historialTransacciones = mutableListOf<Transaccion>()
    
    // Inicialización con validación
    init {
        saldo = saldoInicial
        limiteRetiro = limiteRetiroInicial
    }
    
    /**
     * Getter para el saldo con formato de moneda para visualización
     */
    val saldo: Double
        get() = _saldo
    
    /**
     * Setter para el saldo con validación
     */
    private var saldoInterno: Double
        get() = _saldo
        set(value) {
            if (value < SALDO_MINIMO) {
                throw TransaccionInvalidaException(
                    "El saldo no puede ser menor a ${formatoMoneda.format(SALDO_MINIMO)}"
                )
            }
            _saldo = value
        }
    
    /**
     * Getter para el límite de retiro
     */
    val limiteRetiro: Double
        get() = _limiteRetiro
    
    /**
     * Setter para el límite de retiro con validación
     */
    var limiteRetiro: Double
        get() = _limiteRetiro
        set(value) {
            when {
                value < LIMITE_RETIRO_MINIMO -> throw TransaccionInvalidaException(
                    "El límite de retiro mínimo es ${formatoMoneda.format(LIMITE_RETIRO_MINIMO)}"
                )
                value > LIMITE_RETIRO_MAXIMO -> throw TransaccionInvalidaException(
                    "El límite de retiro máximo es ${formatoMoneda.format(LIMITE_RETIRO_MAXIMO)}"
                )
                else -> _limiteRetiro = value
            }
        }
    
    /**
     * Getter para el número de cuenta (solo lectura)
     */
    val numeroCuenta: String = numeroCuenta
    
    /**
     * Getter para obtener copia del historial de transacciones
     */
    val historial: List<Transaccion>
        get() = historialTransacciones.toList()
    
    /**
     * Setter público para el saldo inicial (solo durante construcción)
     */
    private var saldo: Double
        get() = _saldo
        set(value) {
            if (value < SALDO_MINIMO) {
                throw TransaccionInvalidaException(
                    "El saldo inicial no puede ser menor a ${formatoMoneda.format(SALDO_MINIMO)}"
                )
            }
            _saldo = value
        }
    
    /**
     * Realiza un depósito en la cuenta
     */
    fun depositar(monto: Double, descripcion: String = "Depósito en efectivo"): Boolean {
        return try {
            validarMontoPositivo(monto)
            val saldoAnterior = _saldo
            saldoInterno += monto
            
            registrarTransaccion(
                TipoTransaccion.DEPOSITO, 
                monto, 
                saldoAnterior, 
                _saldo, 
                descripcion
            )
            
            println(" Depósito exitoso: ${formatoMoneda.format(monto)}")
            println(" Nuevo saldo: ${formatoMoneda.format(_saldo)}")
            true
        } catch (e: TransaccionInvalidaException) {
            println(" Error en depósito: ${e.message}")
            false
        }
    }
    
    /**
     * Realiza un retiro de la cuenta con validaciones
     */
    fun retirar(monto: Double, descripcion: String = "Retiro en efectivo"): Boolean {
        return try {
            validarRetiro(monto)
            val saldoAnterior = _saldo
            saldoInterno -= monto
            
            registrarTransaccion(
                TipoTransaccion.RETIRO, 
                monto, 
                saldoAnterior, 
                _saldo, 
                descripcion
            )
            
            println(" Retiro exitoso: ${formatoMoneda.format(monto)}")
            println(" Nuevo saldo: ${formatoMoneda.format(_saldo)}")
            
            // Alerta de saldo bajo
            if (_saldo < 1000) {
                println("⚠️ Advertencia: Su saldo es bajo (${formatoMoneda.format(_saldo)})")
            }
            
            true
        } catch (e: TransaccionInvalidaException) {
            println("❌ Error en retiro: ${e.message}")
            false
        }
    }
    
    /**
     * Transfiere dinero a otra cuenta
     */
    fun transferir(cuentaDestino: CuentaBancaria, monto: Double, descripcion: String = ""): Boolean {
        return try {
            validarRetiro(monto)
            val saldoAnterior = _saldo
            
            // Realizar transferencia
            saldoInterno -= monto
            cuentaDestino.recibirTransferencia(monto, this.numeroCuenta)
            
            registrarTransaccion(
                TipoTransaccion.TRANSFERENCIA_SALIDA, 
                monto, 
                saldoAnterior, 
                _saldo, 
                "Transferencia a ${cuentaDestino.numeroCuenta} - $descripcion"
            )
            
            println(" Transferencia exitosa: ${formatoMoneda.format(monto)}")
            println(" Enviado a cuenta: ${cuentaDestino.numeroCuenta}")
            println(" Nuevo saldo: ${formatoMoneda.format(_saldo)}")
            true
        } catch (e: TransaccionInvalidaException) {
            println("❌ Error en transferencia: ${e.message}")
            false
        }
    }
    
    /**
     * Recibe una transferencia de otra cuenta
     */
    private fun recibirTransferencia(monto: Double, cuentaOrigen: String) {
        val saldoAnterior = _saldo
        _saldo += monto
        
        registrarTransaccion(
            TipoTransaccion.TRANSFERENCIA_ENTRADA, 
            monto, 
            saldoAnterior, 
            _saldo, 
            "Transferencia de cuenta $cuentaOrigen"
        )
        
        println(" Transferencia recibida: ${formatoMoneda.format(monto)}")
    }
    
    /**
     * Valida que el monto sea positivo
     */
    private fun validarMontoPositivo(monto: Double) {
        if (monto <= 0) {
            throw TransaccionInvalidaException("El monto debe ser mayor a cero")
        }
    }
    
    /**
     * Valida las condiciones para un retiro
     */
    private fun validarRetiro(monto: Double) {
        validarMontoPositivo(monto)
        
        when {
            monto > _limiteRetiro -> throw TransaccionInvalidaException(
                "El monto excede el límite de retiro diario (${formatoMoneda.format(_limiteRetiro)})"
            )
            monto > _saldo -> throw TransaccionInvalidaException(
                "Fondos insuficientes. Saldo disponible: ${formatoMoneda.format(_saldo)}"
            )
        }
    }
    
    /**
     * Registra una transacción en el historial
     */
    private fun registrarTransaccion(
        tipo: TipoTransaccion, 
        monto: Double, 
        saldoAnterior: Double, 
        saldoNuevo: Double, 
        descripcion: String
    ) {
        val transaccion = Transaccion(tipo, monto, saldoAnterior, saldoNuevo, LocalDateTime.now(), descripcion)
        historialTransacciones.add(transaccion)
    }
    
    /**
     * Muestra el estado actual de la cuenta
     */
    fun mostrarEstadoCuenta() {
        println("\n=== ESTADO DE CUENTA ===")
        println(" Número de cuenta: $numeroCuenta")
        println(" Saldo actual: ${formatoMoneda.format(_saldo)}")
        println(" Límite de retiro: ${formatoMoneda.format(_limiteRetiro)}")
        println(" Total de transacciones: ${historialTransacciones.size}")
        println("========================")
    }
    
    /**
     * Muestra el historial de transacciones
     */
    fun mostrarHistorial(limite: Int = 10) {
        println("\n=== HISTORIAL DE TRANSACCIONES ===")
        if (historialTransacciones.isEmpty()) {
            println("No hay transacciones registradas")
            return
        }
        
        val transaccionesRecientes = historialTransacciones.takeLast(limite)
        transaccionesRecientes.forEach { transaccion ->
            println("${transaccion.fecha.format(formatoFecha)} | " +
                    "${transaccion.tipo.descripcion} | " +
                    "${formatoMoneda.format(transaccion.monto)} | " +
                    "Saldo: ${formatoMoneda.format(transaccion.saldoNuevo)}")
            if (transaccion.descripcion.isNotEmpty()) {
                println("     ${transaccion.descripcion}")
            }
        }
        println("================================")
    }
    
    /**
     * Calcula estadísticas de la cuenta
     */
    fun obtenerEstadisticas(): Map<String, Any> {
        val depositos = historialTransacciones.filter { it.tipo == TipoTransaccion.DEPOSITO }
        val retiros = historialTransacciones.filter { it.tipo == TipoTransaccion.RETIRO }
        
        return mapOf(
            "totalDepositos" to depositos.sumOf { it.monto },
            "totalRetiros" to retiros.sumOf { it.monto },
            "numeroDepositos" to depositos.size,
            "numeroRetiros" to retiros.size,
            "promedioDeposito" to if (depositos.isNotEmpty()) depositos.map { it.monto }.average() else 0.0,
            "promedioRetiro" to if (retiros.isNotEmpty()) retiros.map { it.monto }.average() else 0.0
        )
    }
}

/**
 * Función de demostración del sistema
 */
fun main() {
    println(" === DEMO SISTEMA DE CUENTA BANCARIA ===")
    
    try {
        // Crear cuentas de prueba
        val cuentaJuan = CuentaBancaria("001-12345", 5000.0, 2000.0)
        val cuentaMaria = CuentaBancaria("002-67890", 3000.0, 1500.0)
        
        println(" Cuentas creadas exitosamente")
        
        // Mostrar estado inicial
        cuentaJuan.mostrarEstadoCuenta()
        cuentaMaria.mostrarEstadoCuenta()
        
        println("\n === REALIZANDO OPERACIONES ===")
        
        // Operaciones diversas
        cuentaJuan.depositar(1000.0, "Salario mensual")
        cuentaJuan.retirar(500.0, "Retiro cajero automático")
        cuentaJuan.transferir(cuentaMaria, 750.0, "Pago prestamo")
        
        cuentaMaria.depositar(200.0, "Venta productos")
        cuentaMaria.retirar(100.0, "Gastos personales")
        
        // Intentar operación inválida
        println("\n === PROBANDO VALIDACIONES ===")
        cuentaJuan.retirar(3000.0) // Excede límite
        cuentaJuan.retirar(10000.0) // Fondos insuficientes
        
        // Mostrar estados finales
        println("\n === ESTADOS FINALES ===")
        cuentaJuan.mostrarEstadoCuenta()
        cuentaJuan.mostrarHistorial()
        
        // Mostrar estadísticas
        println("\n === ESTADÍSTICAS CUENTA JUAN ===")
        val stats = cuentaJuan.obtenerEstadisticas()
        stats.forEach { (key, value) ->
            when (value) {
                is Double -> println("$key: ${NumberFormat.getCurrencyInstance(Locale.US).format(value)}")
                else -> println("$key: $value")
            }
        }
        
    } catch (e: TransaccionInvalidaException) {
        println(" Error en demostración: ${e.message}")
    }
}