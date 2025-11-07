/*
 * Descripción: Punto de entrada de la aplicación Flutter con BottomNavigationBar
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 07/11/2025
 * Fecha última modificación: 07/11/2025
 */

import 'package:flutter/material.dart';
import 'screens/home_page.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Navegación Inferior',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        useMaterial3: true,
      ),
      home: const MyHomePage(),
      debugShowCheckedModeBanner: false,
    );
  }
}