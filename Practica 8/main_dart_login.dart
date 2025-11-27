/*
 * Descripción: Punto de entrada de la aplicación con Provider y LoginScreen como inicio
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 2025-11-27
 * Fecha última modificación: 2025-11-27
 */

import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'providers/user_provider.dart';
import 'views/login_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    // ChangeNotifierProvider permite usar Provider en toda la app
    return ChangeNotifierProvider(
      create: (_) => UserProvider(),
      child: MaterialApp(
        title: 'Login con Validaciones',
        debugShowCheckedModeBanner: false,
        theme: ThemeData(
          primarySwatch: Colors.blue,
          useMaterial3: true,
        ),
        // Pantalla inicial: LoginScreen en lugar de UserListScreen
        home: const LoginScreen(),
      ),
    );
  }
}
