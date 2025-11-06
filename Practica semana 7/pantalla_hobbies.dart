import 'package:flutter/material.dart';

/// Pantalla que muestra los hobbies o intereses del usuario.
/// Usa tarjetas personalizadas para mejor experiencia visual.
class PantallaHobbies extends StatelessWidget {
  const PantallaHobbies({super.key});

  /// Construye la lista de hobbies con diseño tipo tarjeta.
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Mis Hobbies"),
        backgroundColor: Colors.orange,
        foregroundColor: Colors.white,
        centerTitle: true,
        elevation: 2,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            // Hobby 1: Leer
            _buildHobbyCard(
              icon: Icons.book,
              title: "Leer",
              description: "Me encanta la ciencia ficción y los libros de desarrollo personal.",
              color: Colors.blue,
            ),
            const SizedBox(height: 16),

            // Hobby 2: Videojuegos
            _buildHobbyCard(
              icon: Icons.sports_esports,
              title: "Videojuegos",
              description: "Juego RPGs y juegos de estrategia en mi tiempo libre.",
              color: Colors.purple,
            ),
            const SizedBox(height: 16),

            // Hobby 3: Música
            _buildHobbyCard(
              icon: Icons.music_note,
              title: "Escuchar música",
              description: "Rock alternativo, electrónica y bandas sonoras.",
              color: Colors.red,
            ),
          ],
        ),
      ),
    );
  }

  /// Construye una tarjeta de hobby con ícono, título y descripción.
  /// Usa Container personalizado para simular Card con más control.
  Widget _buildHobbyCard({
    required IconData icon,
    required String title,
    required String description,
    required Color color,
  }) {
    return Container(
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: color.withOpacity(0.1), // Fondo suave con color temático
        borderRadius: BorderRadius.circular(12),
        border: Border.all(color: color.withOpacity(0.3)),
        boxShadow: [
          BoxShadow(
            color: Colors.black12,
            blurRadius: 6,
            offset: Offset(0, 2),
          ),
        ],
      ),
      child: Row(
        children: [
          // Ícono circular con fondo de color
          CircleAvatar(
            backgroundColor: color,
            radius: 22,
            child: Icon(icon, color: Colors.white, size: 28),
          ),
          const SizedBox(width: 16),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  title,
                  style: const TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                    color: Colors.black87,
                  ),
                ),
                const SizedBox(height: 4),
                Text(
                  description,
                  style: const TextStyle(fontSize: 14, color: Colors.black74),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}