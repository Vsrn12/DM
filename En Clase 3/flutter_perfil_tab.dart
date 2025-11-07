/*
 * Descripción: Tab de perfil con navegación a pantalla de edición y actualización de nombre
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 07/11/2025
 * Fecha última modificación: 07/11/2025
 */

import 'package:flutter/material.dart';
import 'editar_perfil_screen.dart';

class PerfilTab extends StatefulWidget {
  const PerfilTab({super.key});

  @override
  State<PerfilTab> createState() => _PerfilTabState();
}

class _PerfilTabState extends State<PerfilTab> {
  // Variable para almacenar el nombre del usuario
  String _nombreUsuario = 'Usuario';

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          // Icono de perfil
          const Icon(
            Icons.account_circle,
            size: 100,
            color: Colors.blue,
          ),
          const SizedBox(height: 20),
          // Mostrar nombre del usuario
          Text(
            'Nombre: $_nombreUsuario',
            style: const TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
          ),
          const SizedBox(height: 40),
          // Botón para navegar a la pantalla de edición
          ElevatedButton.icon(
            onPressed: () async {
              // Navegar a la pantalla de edición y esperar el resultado
              final nombre = await Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => const EditarPerfilScreen(),
                ),
              );
              // Si se recibe un nombre, actualizar el estado
              if (nombre != null && nombre.isNotEmpty) {
                setState(() {
                  _nombreUsuario = nombre;
                });
              }
            },
            icon: const Icon(Icons.edit),
            label: const Text('Editar perfil'),
            style: ElevatedButton.styleFrom(
              padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 15),
            ),
          ),
        ],
      ),
    );
  }
}