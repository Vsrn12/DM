/*
 * Descripción: Pantalla de lista de usuarios (CRUD) que recibe email del login
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 2025-01-24
 * Fecha última modificación: 2025-01-24
 */

import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../providers/user_provider.dart';
import '../models/user.dart';

class UserListScreen extends StatelessWidget {
  // ACTIVIDAD EXTRA 3: Recibir el email como parámetro
  final String email;

  // Constructor que requiere el email
  const UserListScreen({super.key, required this.email});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        // ACTIVIDAD EXTRA 3: Mostrar email en el AppBar
        title: Text('Bienvenido: $email'),
        backgroundColor: Colors.blue,
      ),
      body: Consumer<UserProvider>(
        builder: (context, provider, child) {
          if (provider.users.isEmpty) {
            return const Center(
              child: Text(
                'No hay usuarios registrados',
                style: TextStyle(fontSize: 18),
              ),
            );
          }

          return ListView.builder(
            itemCount: provider.users.length,
            itemBuilder: (context, index) {
              final user = provider.users[index];
              return Card(
                margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
                child: ListTile(
                  leading: CircleAvatar(
                    child: Text(user.name[0].toUpperCase()),
                  ),
                  title: Text(user.name),
                  subtitle: Text('${user.age} años - ${user.email}'),
                  trailing: Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      IconButton(
                        icon: const Icon(Icons.edit, color: Colors.blue),
                        onPressed: () {
                          _showEditDialog(context, provider, user);
                        },
                      ),
                      IconButton(
                        icon: const Icon(Icons.delete, color: Colors.red),
                        onPressed: () {
                          provider.deleteUser(user.id);
                          ScaffoldMessenger.of(context).showSnackBar(
                            const SnackBar(content: Text('Usuario eliminado')),
                          );
                        },
                      ),
                    ],
                  ),
                ),
              );
            },
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => _showAddDialog(context),
        child: const Icon(Icons.add),
      ),
    );
  }

  void _showAddDialog(BuildContext context) {
    final nameController = TextEditingController();
    final ageController = TextEditingController();
    final emailController = TextEditingController();

    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Agregar Usuario'),
        content: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            TextField(
              controller: nameController,
              decoration: const InputDecoration(labelText: 'Nombre'),
            ),
            TextField(
              controller: ageController,
              decoration: const InputDecoration(labelText: 'Edad'),
              keyboardType: TextInputType.number,
            ),
            TextField(
              controller: emailController,
              decoration: const InputDecoration(labelText: 'Email'),
              keyboardType: TextInputType.emailAddress,
            ),
          ],
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(context),
            child: const Text('Cancelar'),
          ),
          ElevatedButton(
            onPressed: () {
              if (nameController.text.isNotEmpty &&
                  ageController.text.isNotEmpty &&
                  emailController.text.isNotEmpty) {
                final provider = Provider.of<UserProvider>(context, listen: false);
                provider.addUser(
                  nameController.text,
                  int.parse(ageController.text),
                  emailController.text,
                );
                Navigator.pop(context);
                ScaffoldMessenger.of(context).showSnackBar(
                  const SnackBar(content: Text('Usuario agregado')),
                );
              }
            },
            child: const Text('Agregar'),
          ),
        ],
      ),
    );
  }

  void _showEditDialog(BuildContext context, UserProvider provider, User user) {
    final nameController = TextEditingController(text: user.name);
    final ageController = TextEditingController(text: user.age.toString());
    final emailController = TextEditingController(text: user.email);

    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Editar Usuario'),
        content: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            TextField(
              controller: nameController,
              decoration: const InputDecoration(labelText: 'Nombre'),
            ),
            TextField(
              controller: ageController,
              decoration: const InputDecoration(labelText: 'Edad'),
              keyboardType: TextInputType.number,
            ),
            TextField(
              controller: emailController,
              decoration: const InputDecoration(labelText: 'Email'),
              keyboardType: TextInputType.emailAddress,
            ),
          ],
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(context),
            child: const Text('Cancelar'),
          ),
          ElevatedButton(
            onPressed: () {
              if (nameController.text.isNotEmpty &&
                  ageController.text.isNotEmpty &&
                  emailController.text.isNotEmpty) {
                provider.updateUser(
                  user.id,
                  nameController.text,
                  int.parse(ageController.text),
                  emailController.text,
                );
                Navigator.pop(context);
                ScaffoldMessenger.of(context).showSnackBar(
                  const SnackBar(content: Text('Usuario actualizado')),
                );
              }
            },
            child: const Text('Guardar'),
          ),
        ],
      ),
    );
  }
}
