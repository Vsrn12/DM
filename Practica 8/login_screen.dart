/*
 * Descripción: Pantalla de Login con validaciones usando Form y GlobalKey
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 2025-11-27
 * Fecha última modificación: 2025-11-27
 */

import 'package:flutter/material.dart';
import 'user_list_screen.dart'; // Importar LISTA de Actividad Anterior

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  // GlobalKey para manejar el estado del formulario
  // Permite ejecutar validate() y save() en todos los campos
  final _formKey = GlobalKey<FormState>();

  // Variables para almacenar los valores finales del formulario
  String _email = '';
  String _password = '';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Login'),
        backgroundColor: Colors.blue,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Form(
          // Asignar la key al Form para poder validarlo
          key: _formKey,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              // Título de bienvenida
              const Text(
                '¡Bienvenido!',
                style: TextStyle(
                  fontSize: 32,
                  fontWeight: FontWeight.bold,
                  color: Colors.blue,
                ),
              ),
              const SizedBox(height: 8),
              const Text(
                'Inicia sesión para continuar',
                style: TextStyle(fontSize: 16, color: Colors.grey),
              ),
              const SizedBox(height: 40),

              // CAMPO DE CORREO ELECTRÓNICO
              TextFormField(
                decoration: const InputDecoration(
                  labelText: 'Correo',
                  prefixIcon: Icon(Icons.email),
                  border: OutlineInputBorder(),
                ),
                keyboardType: TextInputType.emailAddress,
                // validator: función que se ejecuta al llamar validate()
                // Si retorna null = campo válido
                // Si retorna String = muestra ese mensaje de error
                validator: (value) {
                  // Validación 1: Campo no vacío
                  if (value == null || value.isEmpty) {
                    return 'El correo es obligatorio';
                  }
                  // Validación 2: Contiene @
                  if (!value.contains('@')) {
                    return 'Debe ser un correo válido';
                  }
                  return null; // Campo válido
                },
                // onSaved: se ejecuta al llamar save()
                // Guarda el valor final en la variable _email
                onSaved: (value) => _email = value!,
              ),

              const SizedBox(height: 16),

              // CAMPO DE CONTRASEÑA
              TextFormField(
                // obscureText: oculta el texto para contraseñas
                obscureText: true,
                decoration: const InputDecoration(
                  labelText: 'Contraseña',
                  prefixIcon: Icon(Icons.lock),
                  border: OutlineInputBorder(),
                ),
                // Validaciones de la contraseña
                validator: (value) {
                  // Validación 1: Campo no vacío
                  if (value == null || value.isEmpty) {
                    return 'La contraseña es obligatoria';
                  }
                  // Validación 2: Mínimo 6 caracteres
                  if (value.length < 6) {
                    return 'Debe tener al menos 6 caracteres';
                  }
                  // ACTIVIDAD EXTRA 1: Validación adicional - Contiene mayúscula
                  if (!value.contains(RegExp(r'[A-Z]'))) {
                    return 'Debe tener al menos una mayúscula';
                  }
                  // ACTIVIDAD EXTRA 1: Validación adicional - Contiene número
                  if (!value.contains(RegExp(r'[0-9]'))) {
                    return 'Debe tener al menos un número';
                  }
                  return null; // Campo válido
                },
                // Guardar el valor en _password cuando se llame save()
                onSaved: (value) => _password = value!,
              ),

              const SizedBox(height: 24),

              // BOTÓN DE INGRESAR
              SizedBox(
                width: double.infinity,
                height: 50,
                child: ElevatedButton(
                  onPressed: () {
                    // Paso 1: Validar todos los campos del formulario
                    // validate() ejecuta todos los validator de los TextFormField
                    if (_formKey.currentState!.validate()) {
                      // Paso 2: Guardar valores finales
                      // save() ejecuta todos los onSaved de los TextFormField
                      _formKey.currentState!.save();

                      // Paso 3: Navegar a la siguiente pantalla
                      // Pasar el email como parámetro a UserListScreen
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (_) => UserListScreen(email: _email),
                        ),
                      );
                    }
                    // Si validate() retorna false, no hace nada
                    // Los errores se muestran automáticamente bajo cada campo
                  },
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.blue,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(8),
                    ),
                  ),
                  child: const Text(
                    'Ingresar',
                    style: TextStyle(fontSize: 18),
                  ),
                ),
              ),

              const SizedBox(height: 16),

              // ACTIVIDAD EXTRA 2: Botón "Crear cuenta"
              TextButton(
                onPressed: () {
                  // Aquí iría la navegación a pantalla de registro
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(content: Text('Crear cuenta - Próximamente')),
                  );
                },
                child: const Text(
                  'Crear cuenta',
                  style: TextStyle(fontSize: 16),
                ),
              ),

              // ACTIVIDAD EXTRA 2: Texto "¿Olvidaste tu contraseña?"
              TextButton(
                onPressed: () {
                  // Aquí iría la navegación a recuperar contraseña
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(content: Text('Recuperar contraseña - Próximamente')),
                  );
                },
                child: const Text(
                  '¿Olvidaste tu contraseña?',
                  style: TextStyle(fontSize: 14, color: Colors.grey),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
