import 'package:flutter/material.dart';
import 'pantalla_inicio.dart'; // Importa la pantalla inicial. Cambiar para probar otras pantallas

/// Punto de entrada principal de la aplicación Flutter.
/// Configura el MaterialApp y define la pantalla inicial.
void main() {
  runApp(const MyApp());
}

/// Clase raíz de la aplicación. Es un widget sin estado (StatelessWidget)
/// porque no cambia durante la ejecución.
class MyApp extends StatelessWidget {
  const MyApp({super.key});

  /// Construye el widget principal de la app.
  /// Usa MaterialApp como contenedor global para temas, rutas y configuración.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Mi App Personal', // Título que aparece en el administrador de tareas
      debugShowCheckedModeBanner: false, // Oculta el banner de "DEBUG" en desarrollo
      theme: ThemeData(
        primarySwatch: Colors.blue,
        useMaterial3: true, // Usa el diseño moderno de Material 3
      ),
      // Cambiar aquí para probar cada pantalla individualmente
      home: PantallaHobbies(),
    );
  }
}