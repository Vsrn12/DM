/*
 * Descripción: Punto de entrada principal de la aplicación con configuración de Provider
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 12/11/2025
 */

import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'viewmodels/user_view_model.dart';
import 'views/user_list_screen.dart';

void main() {
  runApp(
    ChangeNotifierProvider(
      create: (_) => UserViewModel(),
      child: const MyApp(),
    ),
  );
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Gestión de Usuarios',
      theme: ThemeData(primarySwatch: Colors.indigo),
      home: const UserListScreen(),
    );
  }
}