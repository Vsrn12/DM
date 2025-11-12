/*
 * Descripción: Modelo de datos que representa un Usuario en el sistema
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 12/11/2025
 */

class User {
  String nombre;
  String genero;
  bool activo;

  User({
    required this.nombre,
    required this.genero,
    required this.activo,
  });
}