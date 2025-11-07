/*
 * Descripción: Tab de usuarios con contenido simple
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 07/11/2025
 * Fecha última modificación: 07/11/2025
 */

import 'package:flutter/material.dart';

class UsuariosTab extends StatelessWidget {
  const UsuariosTab({super.key});

  @override
  Widget build(BuildContext context) {
    return const Center(
      child: Text(
        'Usuarios',
        style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
      ),
    );
  }
}