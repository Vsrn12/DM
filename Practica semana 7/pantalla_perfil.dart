import 'package:flutter/material.dart';

class PantallaPerfil extends StatelessWidget {
  const PantallaPerfil({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Mi Perfil"),
        backgroundColor: Colors.green,
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(20),
        child: Column(
          children: [
            // Foto de perfil
            Center(
              child: Container(
                width: 120,
                height: 120,
                decoration: BoxDecoration(
                  shape: BoxShape.circle,
                  image: const DecorationImage(
                    image: NetworkImage(
                      'https://randomuser.me/api/portraits/men/32.jpg', // Imagen de ejemplo
                    ),
                    fit: BoxFit.cover,
                  ),
                  border: Border.all(color: Colors.green, width: 3),
                ),
              ),
            ),
            const SizedBox(height: 20),

            // Nombre y descripción
            const Text(
              "Juan Pérez",
              style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            const Text(
              "Estudiante de Ingeniería en Sistemas | Apasionado por la tecnología y el desarrollo móvil",
              textAlign: TextAlign.center,
              style: TextStyle(fontSize: 16, color: Colors.grey),
            ),
            const SizedBox(height: 30),

            // Información de contacto
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

  Widget _buildContactRow(IconData icon, String text) {
    return Row(
      children: [
        Icon(icon, color: Colors.green),
        const SizedBox(width: 12),
        Text(
          text,
          style: const TextStyle(fontSize: 16),
        ),
      ],
    );
  }
}