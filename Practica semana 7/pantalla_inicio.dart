import 'package:flutter/material.dart';

/// Pantalla de bienvenida de la aplicación.
/// Muestra un mensaje de saludo y un botón inactivo para practicar diseño.
class PantallaInicio extends StatelessWidget {
  const PantallaInicio({super.key});

  /// Construye la interfaz completa de la pantalla de inicio.
  /// Usa Scaffold para estructura estándar: AppBar + body.
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // Barra superior con título centrado y color personalizado
      appBar: AppBar(
        title: const Text("Bienvenido"),
        backgroundColor: Colors.blueAccent,
        centerTitle: true, // Centra el título en la AppBar
        elevation: 4, // Sombra sutil para profundidad visual
      ),
      body: Center(
        // Centra todo el contenido vertical y horizontalmente
        child: Padding(
          padding: const EdgeInsets.all(20.0), // Espaciado uniforme en todos los lados
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center, // Centra los hijos verticalmente
            children: [
              // Título principal con estilo destacado
              const Text(
                "¡Hola! Bienvenido a mi aplicación personal",
                style: TextStyle(
                  fontSize: 24,
                  fontWeight: FontWeight.bold,
                  color: Colors.black87,
                ),
                textAlign: TextAlign.center,
              ),
              const SizedBox(height: 30), // Espacio vertical controlado

              // Subtítulo descriptivo
              const Text(
                "Explora mi perfil y conoce mis intereses.",
                style: TextStyle(fontSize: 16, color: Colors.grey),
                textAlign: TextAlign.center,
              ),
              const SizedBox(height: 40), // Espacio antes del botón

              // Botón estilizado (sin acción aún, como pide la práctica)
              ElevatedButton(
                onPressed: null, // Deshabilitado intencionalmente
                style: ElevatedButton.styleFrom(
                  padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 15),
                  backgroundColor: Colors.blueAccent,
                  foregroundColor: Colors.white,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(12), // Bordes redondeados
                  ),
                  elevation: 3, // Sombra ligera
                ),
                child: const Text(
                  "Ver mi perfil",
                  style: TextStyle(fontSize: 18, fontWeight: FontWeight.w600),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}