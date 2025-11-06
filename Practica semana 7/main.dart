import 'package:flutter/material.dart';
import 'pantalla_inicio.dart'; // Cambia según la pantalla que quieras probar

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Mi App Personal',
      debugShowCheckedModeBanner: false,
      home: PantallaInicio(), // Cambia aquí para probar: PantallaPerfil(), PantallaHobbies()
    );
  }
}