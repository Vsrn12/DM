/**
 * Descripción: Clase Producto con precio y descuento, calcula precio final con validaciones
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 01/09/25
 * Fecha última modificación: 01/09/25
 */

class Producto {
    // Propiedades privadas con backing fields
    private var _precio: Double = 0.0
    private var _descuento: Double = 0.0
    
    // Getter y setter para precio con validación
    var precio: Double
        get() = _precio
        set(value) {
            if (value >= 0) {
                _precio = value
            } else {
                throw IllegalArgumentException("El precio no puede ser negativo")
            }
        }
    
    // Getter y setter para descuento con validación (0-100%)
    var descuento: Double
        get() = _descuento
        set(value) {
            if (value in 0.0..100.0) {
                _descuento = value
            } else {
                throw IllegalArgumentException("El descuento debe estar entre 0 y 100%")
            }
        }
    
    // Propiedades adicionales
    var nombre: String = ""
    
    // Constructor primario
    constructor(nombre: String = "", precioInicial: Double = 0.0, descuentoInicial: Double = 0.0) {
        this.nombre = nombre
        this.precio = precioInicial
        this.descuento = descuentoInicial
    }
    
    /**
     * Calcula el precio final después de aplicar el descuento
     * @return Precio final con descuento aplicado
     */
    fun calcularPrecioFinal(): Double {
        val montoDescuento = precio * (descuento / 100)
        return precio - montoDescuento
    }
    
    /**
     * Calcula el monto de descuento en dinero
     * @return Cantidad de dinero descontada
     */
    fun calcularMontoDescuento(): Double {
        return precio * (descuento / 100)
    }
    
    /**
     * Aplica un descuento adicional al descuento actual
     * @param descuentoAdicional Porcentaje de descuento adicional
     */
    fun aplicarDescuentoAdicional(descuentoAdicional: Double) {
        if (descuentoAdicional in 0.0..100.0) {
            val nuevoDescuento = descuento + descuentoAdicional
            descuento = if (nuevoDescuento <= 100.0) nuevoDescuento else 100.0
            println("Descuento adicional aplicado. Descuento total: $descuento%")
        } else {
            println("Error: El descuento adicional debe estar entre 0 y 100%")
        }
    }
    
    /**
     * Muestra la información completa del producto
     */
    fun mostrarInfo() {
        println("=== INFORMACIÓN DEL PRODUCTO ===")
        println("Nombre: $nombre")
        println("Precio original: $$precio")
        println("Descuento aplicado: $descuento%")
        println("Monto del descuento: $${String.format("%.2f", calcularMontoDescuento())}")
        println("Precio final: $${String.format("%.2f", calcularPrecioFinal())}")
    }
    
    /**
     * Verifica si el producto tiene descuento
     * @return true si tiene descuento, false en caso contrario
     */
    fun tieneDescuento(): Boolean = descuento > 0.0
}

fun main() {
    println("=== SISTEMA DE GESTIÓN DE PRODUCTOS ===\n")
    
    try {
        // Crear productos con diferentes configuraciones
        val producto1 = Producto("Laptop Gaming", 1500.0, 15.0)
        val producto2 = Producto("Mouse Inalámbrico", 50.0, 0.0)
        val producto3 = Producto("Teclado Mecánico", 120.0, 25.0)
        
        val productos = listOf(producto1, producto2, producto3)
        
        println("--- Información inicial de productos ---")
        productos.forEach { it.mostrarInfo(); println() }
        
        println("--- Aplicando descuentos adicionales ---")
        
        // Aplicar descuento adicional al mouse
        producto2.aplicarDescuentoAdicional(10.0)
        println("Información actualizada del ${producto2.nombre}:")
        producto2.mostrarInfo()
        
        println("\n--- Resumen final ---")
        productos.forEach { producto ->
            println("${producto.nombre}: $${String.format("%.2f", producto.calcularPrecioFinal())} " +
                   "(${if (producto.tieneDescuento()) "Con descuento" else "Sin descuento"})")
        }
        
    } catch (e: IllegalArgumentException) {
        println("Error en validación: ${e.message}")
    }
}