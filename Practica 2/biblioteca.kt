/**
 * Descripción: Sistema de gestión de biblioteca con herencia, polimorfismo e interfaces
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 01/09/25
 * Fecha última modificación: 01/09/25
 */

/**
 * Clase base abstracta para todos los materiales de la biblioteca
 */
abstract class Material(
    val titulo: String,
    val autor: String,
    val anioPublicacion: Int
) {
    abstract fun mostrarDetalles()
    
    override fun toString(): String = titulo
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Material) return false
        return titulo == other.titulo && autor == other.autor
    }
    
    override fun hashCode(): Int {
        return titulo.hashCode() * 31 + autor.hashCode()
    }
}

/**
 * Clase Libro que hereda de Material
 */
class Libro(
    titulo: String,
    autor: String,
    anioPublicacion: Int,
    val genero: String,
    val numeroPaginas: Int
) : Material(titulo, autor, anioPublicacion) {
    
    override fun mostrarDetalles() {
        println("=== DETALLES DEL LIBRO ===")
        println("Título: $titulo")
        println("Autor: $autor")
        println("Año de publicación: $anioPublicacion")
        println("Género: $genero")
        println("Número de páginas: $numeroPaginas")
    }
}

/**
 * Clase Revista que hereda de Material
 */
class Revista(
    titulo: String,
    autor: String,
    anioPublicacion: Int,
    val issn: String,
    val volumen: Int,
    val numero: Int,
    val editorial: String
) : Material(titulo, autor, anioPublicacion) {
    
    override fun mostrarDetalles() {
        println("=== DETALLES DE LA REVISTA ===")
        println("Título: $titulo")
        println("Autor: $autor")
        println("Año de publicación: $anioPublicacion")
        println("ISSN: $issn")
        println("Volumen: $volumen")
        println("Número: $numero")
        println("Editorial: $editorial")
    }
}

/**
 * Data class para representar usuarios de la biblioteca
 */
data class Usuario(
    val nombre: String,
    val apellido: String,
    val edad: Int
) {
    fun nombreCompleto(): String = "$nombre $apellido"
    
    override fun toString(): String = nombreCompleto()
}

/**
 * Interfaz que define las operaciones básicas de una biblioteca
 */
interface IBiblioteca {
    fun registrarMaterial(material: Material): Boolean
    fun registrarUsuario(usuario: Usuario): Boolean
    fun prestamo(usuario: Usuario, material: Material): Boolean
    fun devolucion(usuario: Usuario, material: Material): Boolean
    fun mostrarMaterialesDisponibles()
    fun mostrarMaterialesReservadosPorUsuario(usuario: Usuario)
}

/**
 * Clase que implementa el sistema de gestión de la biblioteca
 */
class Biblioteca : IBiblioteca {
    // Lista de materiales disponibles
    private val materialesDisponibles = mutableListOf<Material>()
    
    // Mapa de usuarios y sus materiales en préstamo
    private val usuariosConPrestamos = mutableMapOf<Usuario, MutableList<Material>>()
    
    // Lista de usuarios registrados
    private val usuariosRegistrados = mutableListOf<Usuario>()
    
    override fun registrarMaterial(material: Material): Boolean {
        return if (!materialesDisponibles.contains(material)) {
            materialesDisponibles.add(material)
            println("Material registrado exitosamente: ${material.titulo}")
            true
        } else {
            println("Error: El material ya está registrado")
            false
        }
    }
    
    override fun registrarUsuario(usuario: Usuario): Boolean {
        return if (!usuariosRegistrados.contains(usuario)) {
            usuariosRegistrados.add(usuario)
            usuariosConPrestamos[usuario] = mutableListOf()
            println("Usuario registrado exitosamente: ${usuario.nombreCompleto()}")
            true
        } else {
            println("Error: El usuario ya está registrado")
            false
        }
    }
    
    override fun prestamo(usuario: Usuario, material: Material): Boolean {
        return when {
            !usuariosRegistrados.contains(usuario) -> {
                println("Error: Usuario no registrado")
                false
            }
            !materialesDisponibles.contains(material) -> {
                println("Error: Material no disponible")
                false
            }
            usuariosConPrestamos[usuario]?.size ?: 0 >= 3 -> {
                println("Error: Usuario ha alcanzado el límite de préstamos (3 materiales)")
                false
            }
            else -> {
                materialesDisponibles.remove(material)
                usuariosConPrestamos[usuario]?.add(material)
                println("Préstamo realizado exitosamente:")
                println("Usuario: ${usuario.nombreCompleto()}")
                println("Material: ${material.titulo}")
                true
            }
        }
    }
    
    override fun devolucion(usuario: Usuario, material: Material): Boolean {
        return if (usuariosConPrestamos[usuario]?.remove(material) == true) {
            materialesDisponibles.add(material)
            println("Devolución realizada exitosamente:")
            println("Usuario: ${usuario.nombreCompleto()}")
            println("Material: ${material.titulo}")
            true
        } else {
            println("Error: El usuario no tiene este material en préstamo")
            false
        }
    }
    
    override fun mostrarMaterialesDisponibles() {
        println("=== MATERIALES DISPONIBLES ===")
        if (materialesDisponibles.isEmpty()) {
            println("No hay materiales disponibles")
        } else {
            materialesDisponibles.forEachIndexed { index, material ->
                println("${index + 1}. ${material.titulo} - ${material.autor} (${material.anioPublicacion})")
            }
        }
    }
    
    override fun mostrarMaterialesReservadosPorUsuario(usuario: Usuario) {
        println("=== MATERIALES EN PRÉSTAMO: ${usuario.nombreCompleto()} ===")
        val materiales = usuariosConPrestamos[usuario]
        if (materiales.isNullOrEmpty()) {
            println("El usuario no tiene materiales en préstamo")
        } else {
            materiales.forEachIndexed { index, material ->
                println("${index + 1}. ${material.titulo} - ${material.autor}")
            }
        }
    }
    
    /**
     * Método adicional para mostrar estadísticas de la biblioteca
     */
    fun mostrarEstadisticas() {
        println("=== ESTADÍSTICAS DE LA BIBLIOTECA ===")
        println("Usuarios registrados: ${usuariosRegistrados.size}")
        println("Materiales disponibles: ${materialesDisponibles.size}")
        println("Materiales en préstamo: ${usuariosConPrestamos.values.sumOf { it.size }}")
        println("Total de materiales: ${materialesDisponibles.size + usuariosConPrestamos.values.sumOf { it.size }}")
    }
    
    /**
     * Método para buscar materiales por título
     */
    fun buscarMaterialPorTitulo(titulo: String): List<Material> {
        val disponibles = materialesDisponibles.filter { 
            it.titulo.contains(titulo, ignoreCase = true) 
        }
        val enPrestamo = usuariosConPrestamos.values.flatten().filter { 
            it.titulo.contains(titulo, ignoreCase = true) 
        }
        return disponibles + enPrestamo
    }
}

fun main() {
    println("=== SISTEMA DE GESTIÓN DE BIBLIOTECA ===\n")
    
    // Crear instancia de la biblioteca
    val biblioteca = Biblioteca()
    
    // Crear usuarios
    val usuario1 = Usuario("Juan", "Pérez", 25)
    val usuario2 = Usuario("María", "González", 30)
    val usuario3 = Usuario("Carlos", "López", 22)
    
    // Crear materiales
    val libro1 = Libro("El Quijote", "Miguel de Cervantes", 1605, "Novela", 863)
    val libro2 = Libro("Cien Años de Soledad", "Gabriel García Márquez", 1967, "Realismo Mágico", 471)
    val libro3 = Libro("1984", "George Orwell", 1949, "Distopía", 328)
    
    val revista1 = Revista("National Geographic", "Varios", 2023, "0027-9358", 45, 3, "National Geographic Society")
    val revista2 = Revista("Scientific American", "Varios", 2023, "0036-8733", 328, 4, "Springer Nature")
    
    println("--- Registrando usuarios ---")
    biblioteca.registrarUsuario(usuario1)
    biblioteca.registrarUsuario(usuario2)
    biblioteca.registrarUsuario(usuario3)
    
    println("\n--- Registrando materiales ---")
    biblioteca.registrarMaterial(libro1)
    biblioteca.registrarMaterial(libro2)
    biblioteca.registrarMaterial(libro3)
    biblioteca.registrarMaterial(revista1)
    biblioteca.registrarMaterial(revista2)
    
    println("\n--- Materiales disponibles inicialmente ---")
    biblioteca.mostrarMaterialesDisponibles()
    
    println("\n--- Realizando préstamos ---")
    biblioteca.prestamo(usuario1, libro1)
    biblioteca.prestamo(usuario1, revista1)
    biblioteca.prestamo(usuario2, libro2)
    biblioteca.prestamo(usuario3, libro3)
    
    println("\n--- Materiales disponibles después de préstamos ---")
    biblioteca.mostrarMaterialesDisponibles()
    
    println("\n--- Materiales en préstamo por usuario ---")
    biblioteca.mostrarMaterialesReservadosPorUsuario(usuario1)
    biblioteca.mostrarMaterialesReservadosPorUsuario(usuario2)
    biblioteca.mostrarMaterialesReservadosPorUsuario(usuario3)
    
    println("\n--- Mostrando detalles de materiales ---")
    libro1.mostrarDetalles()
    println()
    revista1.mostrarDetalles()
    
    println("\n--- Realizando devoluciones ---")
    biblioteca.devolucion(usuario1, libro1)
    biblioteca.devolucion(usuario2, libro2)
    
    println("\n--- Estado final de la biblioteca ---")
    biblioteca.mostrarMaterialesDisponibles()
    println()
    biblioteca.mostrarEstadisticas()
    
    // Demostrar búsqueda
    println("\n--- Búsqueda de materiales ---")
    val resultados = biblioteca.buscarMaterialPorTitulo("El")
    println("Materiales encontrados con 'El':")
    resultados.forEach { println("- ${it.titulo}") }
}