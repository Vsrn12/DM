/*
 * Descripción: Tab de configuración con contenido simple
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 07/11/2025
 * Fecha última modificación: 07/11/2025
 */

import 'package:flutter/material.dart';

class ConfiguracionTab extends StatelessWidget {
  const ConfiguracionTab({super.key});

  @override
  Widget build(BuildContext context) {
    return const Center(
      child: Text(
        'Configuración',
        style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
      ),
    );
  }
}