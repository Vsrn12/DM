import 'package:flutter/material.dart';

class PantallaHobbies extends StatelessWidget {
  const PantallaHobbies({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Mis Hobbies"),
        backgroundColor: Colors.orange,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            _buildHobbyCard(
              icon: Icons.book,
              title: "Leer",
              description: "Me encanta la ciencia ficción y los libros de desarrollo personal.",
              color: Colors.blue,
            ),
            const SizedBox(height: 16),
            _buildHobbyCard(
              icon: Icons.sports_esports,
              title: "Videojuegos",
              description: "Juego RPGs y juegos de estrategia en mi tiempo libre.",
              color: Colors.purple,
            ),
            const SizedBox(height: 16),
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

  Widget _buildHobbyCard({
    required IconData icon,
    required String title,
    required String description,
    required Color color,
  }) {
    return Container(
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: color.withOpacity(0.1),
        borderRadius: BorderRadius.circular(12),
        border: Border.all(color: color.withOpacity(0.3)),
      ),
      child: Row(
        children: [
          CircleAvatar(
            backgroundColor: color,
            child: Icon(icon, color: Colors.white),
          ),
          const SizedBox(width: 16),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  title,
                  style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                ),
                const SizedBox(height: 4),
                Text(
                  description,
                  style: const TextStyle(fontSize: 14, color: Colors.black87),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}