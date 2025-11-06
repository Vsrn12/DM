import 'package:flutter/material.dart';

/// Pantalla que muestra información personal del usuario.
/// Incluye foto, nombre, descripción y datos de contacto con íconos.
class PantallaPerfil extends StatelessWidget {
  const PantallaPerfil({super.key});

  /// Construye la interfaz del perfil con diseño limpio y organizado.
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Mi Perfil"),
        backgroundColor: Colors.green,
        foregroundColor: Colors.white,
        centerTitle: true,
        elevation: 2,
      ),
      body: SingleChildScrollView(
        // Permite desplazamiento si el contenido excede la pantalla
        padding: const EdgeInsets.all(20),
        child: Column(
          children: [
            // FOTO DE PERFIL
            Center(
              child: Container(
                width: 120,
                height: 120,
                decoration: BoxDecoration(
                  shape: BoxShape.circle,
                  image: const DecorationImage(
                    image: NetworkImage(
                      'https://randomuser.me/api/portraits/men/32.jpg',
                    ), // Imagen de ejemplo desde internet
                    fit: BoxFit.cover,
                  ),
                  border: Border.all(color: Colors.green, width: 3), // Borde temático
                  boxShadow: [
                    BoxShadow(
                      color: Colors.black26,
                      blurRadius: 8,
                      offset: Offset(0, 4),
                    ),
                  ],
                ),
              ),
            ),
            const SizedBox(height: 20),

            // NOMBRE Y DESCRIPCIÓN
            const Text(
              "Juan Pérez",
              style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            const Text(
              "Estudiante de Ingeniería en Sistemas | Apasionado por la tecnología y el desarrollo móvil",
              textAlign: TextAlign.center,
              style: TextStyle(fontSize: 16, color: Colors.black54),
            ),
            const SizedBox(height: 30),

            // Reutilizamos un método privado para evitar repetición (DRY)
            _buildContactRow(Icons.email, "juan.perez@email.com"),
            const SizedBox(height: 12),
            _buildContactRow(Icons.phone, "+51 987 654 321"),
            const SizedBox(height: 12),
            _buildContactRow(Icons.location_on, "Lima, Perú"),
          ],
        ),
      ),
    );
  }

  /// Construye una fila de contacto con ícono y texto.
  /// Mejora la legibilidad y permite reutilización.
  Widget _buildContactRow(IconData icon, String text) {
    return Row(
      children: [
        Icon(icon, color: Colors.green, size: 24),
        const SizedBox(width: 12),
        Text(
          text,
          style: const TextStyle(fontSize: 16, color: Colors.black87),
        ),
      ],
    );
  }
}