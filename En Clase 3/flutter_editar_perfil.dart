/*
 * Descripción: Pantalla para editar el nombre del usuario con TextField y navegación de retorno
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 07/11/2025
 * Fecha última modificación: 07/11/2025
 */

import 'package:flutter/material.dart';

class EditarPerfilScreen extends StatefulWidget {
  const EditarPerfilScreen({super.key});

  @override
  State<EditarPerfilScreen> createState() => _EditarPerfilScreenState();
}

class _EditarPerfilScreenState extends State<EditarPerfilScreen> {
  // Controlador para el TextField
  final TextEditingController _controller = TextEditingController();

  @override
  void dispose() {
    // Liberar recursos del controlador
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Editar perfil'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            // Icono decorativo
            const Icon(
              Icons.person_outline,
              size: 80,
              color: Colors.blue,
            ),
            const SizedBox(height: 30),
            // Campo de texto para ingresar el nombre
            TextField(
              controller: _controller,
              decoration: const InputDecoration(
                labelText: 'Nombre',
                border: OutlineInputBorder(),
                prefixIcon: Icon(Icons.edit),
              ),
              textCapitalization: TextCapitalization.words,
            ),
            const SizedBox(height: 30),
            // Botón para guardar y retornar el valor
            ElevatedButton.icon(
              onPressed: () {
                // Retornar el texto ingresado a la pantalla anterior
                Navigator.pop(context, _controller.text);
              },
              icon: const Icon(Icons.save),
              label: const Text('Guardar'),
              style: ElevatedButton.styleFrom(
                padding: const EdgeInsets.symmetric(horizontal: 40, vertical: 15),
                textStyle: const TextStyle(fontSize: 16),
              ),
            ),
          ],
        ),
      ),
    );
  }
}