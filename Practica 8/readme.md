# Práctica: Login con Validaciones en Flutter

## Descripción
Esta práctica implementa una pantalla de Login profesional utilizando Flutter con validaciones completas, manejo de formularios con Form y GlobalKey, y navegación entre pantallas. Se integra con la práctica anterior de CRUD de usuarios, recibiendo el email del login en la pantalla principal.

## Ejercicio Implementado

### Login con Validaciones Completas (LoginScreen)
Sistema de autenticación con validaciones en tiempo real, manejo de formularios centralizado y navegación con paso de parámetros.

**Características:**
* Formulario con Form y GlobalKey<FormState> para validación centralizada
* Campo de correo electrónico con validaciones múltiples
* Campo de contraseña con validaciones de seguridad
* Validaciones adicionales: mayúscula y número requeridos
* Interfaz moderna con Material Design 3
* Navegación a pantalla de CRUD pasando email como parámetro

**Funcionalidades:**
* ✅ Validación de campo obligatorio para email y contraseña
* ✅ Validación de formato de correo con @
* ✅ Validación de longitud mínima de contraseña (6 caracteres)
* ✅ Validación de mayúscula requerida en contraseña
* ✅ Validación de número requerido en contraseña
* ✅ Uso de onSaved para capturar valores finales
* ✅ Navegación con Navigator.push y paso de parámetros

**Funcionalidades Avanzadas:**
* ✅ GlobalKey<FormState> para manejo centralizado del formulario
* ✅ validator ejecutado automáticamente en todos los campos
* ✅ onSaved para guardar valores sin TextEditingController
* ✅ Mensajes de error personalizados bajo cada campo
* ✅ Botones adicionales: "Crear cuenta" y "¿Olvidaste tu contraseña?"
* ✅ Diseño responsivo con padding y espaciados uniformes
* ✅ Email mostrado en AppBar de la pantalla de usuarios

## Tecnologías Utilizadas

**Framework y Lenguaje**
* Flutter SDK
* Dart
* Material Design 3

**Componentes Flutter**
* **Form**: Contenedor de formulario con validación centralizada
* **GlobalKey<FormState>**: Controlador del estado del formulario
* **TextFormField**: Campos de entrada con validación integrada
* **validator**: Función de validación por campo
* **onSaved**: Callback para guardar valores finales
* **Navigator.push**: Navegación entre pantallas
* **MaterialPageRoute**: Ruta de navegación con builder

**Elementos de UI**
* **Scaffold**: Estructura básica de pantalla
* **AppBar**: Barra superior con título
* **Column**: Layout vertical para campos
* **SizedBox**: Espaciados entre elementos
* **ElevatedButton**: Botón principal de acción
* **TextButton**: Botones secundarios de texto
* **OutlineInputBorder**: Bordes de campos de texto

**Validaciones Implementadas**
* **Campo obligatorio**: `value.isEmpty`
* **Formato email**: `value.contains('@')`
* **Longitud mínima**: `value.length < 6`
* **Contiene mayúscula**: `RegExp(r'[A-Z]')`
* **Contiene número**: `RegExp(r'[0-9]')`

## Estructura del Proyecto

```
lib/
├── main.dart                    # Punto de entrada con Provider
├── models/
│   └── user.dart               # Modelo de datos de usuario
├── providers/
│   └── user_provider.dart      # Gestor de estado con Provider
└── views/
    ├── login_screen.dart       # Pantalla de Login con validaciones
    └── user_list_screen.dart   # Pantalla CRUD que recibe email
```

## Conceptos Clave Implementados

### Form y GlobalKey
* **Form**: Contenedor que agrupa TextFormField
* **GlobalKey<FormState>**: Permite ejecutar validate() y save()
* **validate()**: Ejecuta todos los validator
* **save()**: Ejecuta todos los onSaved

### validator vs onSaved
* **validator**: Se ejecuta al llamar validate()
  - Retorna String → muestra error
  - Retorna null → campo válido
* **onSaved**: Se ejecuta al llamar save()
  - Guarda el valor final en variable
  - Solo se ejecuta si validate() fue exitoso

### onSaved vs TextEditingController

| Característica | onSaved | TextEditingController |
|----------------|---------|----------------------|
| **Cuándo usar** | Valores finales al submit | Acceso continuo al texto |
| **Complejidad** | Más simple | Más código |
| **Uso típico** | Formularios estáticos (Login) | Búsqueda en tiempo real |
| **Limpieza** | Automática | Manual con dispose() |
| **Recomendado para** | Login, Registro | Chat, Búsqueda |

### Navegación sin Rutas Nombradas
```dart
Navigator.push(
  context,
  MaterialPageRoute(
    builder: (_) => UserListScreen(email: _email),
  ),
);
```
* Push directo sin definir rutas
* Paso de parámetros por constructor
* Retorno automático con botón Back

## Flujo de la Aplicación

### Flujo de Login
1. **Usuario abre app** → Se muestra LoginScreen
2. **Ingresa email y contraseña** → Validaciones en tiempo real
3. **Presiona "Ingresar"** → Se ejecuta validate()
4. **Si hay errores** → Se muestran mensajes bajo cada campo
5. **Si es válido** → Se ejecuta save() y guarda valores
6. **Navegación** → Push a UserListScreen con email
7. **AppBar** → Muestra "Bienvenido: [email]"

### Validaciones Aplicadas

**Campo Email:**
1. No puede estar vacío
2. Debe contener @

**Campo Contraseña:**
1. No puede estar vacío
2. Mínimo 6 caracteres
3. Debe contener al menos una mayúscula (A-Z)
4. Debe contener al menos un número (0-9)

## Actividades Extra Implementadas

### ✅ Actividad 1: Validación Adicional
**Implementado en líneas 78-86 de login_screen.dart**
```dart
// Validación de mayúscula
if (!value.contains(RegExp(r'[A-Z]'))) {
  return 'Debe tener al menos una mayúscula';
}
// Validación de número
if (!value.contains(RegExp(r'[0-9]'))) {
  return 'Debe tener al menos un número';
}
```

### ✅ Actividad 2: Mejorar Interfaz
**Implementado en líneas 120-153 de login_screen.dart**
* Botón "Crear cuenta" con TextButton
* Texto "¿Olvidaste tu contraseña?"
* Padding uniforme de 16dp
* SizedBox para espaciados consistentes
* Título de bienvenida y subtítulo

### ✅ Actividad 3: Mostrar Email en CRUD
**Implementado en user_list_screen.dart líneas 20-25**
```dart
final String email;
const UserListScreen({super.key, required this.email});
...
AppBar(title: Text('Bienvenido: $email'))
```

## Instrucciones de Instalación

### Archivos a Copiar

**1. Crear/Modificar en lib/views/:**
```
lib/views/login_screen.dart          # Copiar código completo
lib/views/user_list_screen.dart      # Modificar para recibir email
```

**2. Modificar main.dart:**
```
lib/main.dart                        # Cambiar home a LoginScreen
```

### Dependencias Requeridas

En `pubspec.yaml`:
```yaml
dependencies:
  flutter:
    sdk: flutter
  provider: ^6.1.1
```

### Pasos de Instalación:
1. Copiar archivos en carpetas correspondientes
2. Ejecutar: `flutter pub get`
3. Ejecutar: `flutter run`

## Instrucciones para Probar

### Prueba 1: Validaciones de Email
1. Dejar campo email vacío → Presionar "Ingresar"
2. Ver mensaje: "El correo es obligatorio"
3. Escribir "usuario" sin @ → Presionar "Ingresar"
4. Ver mensaje: "Debe ser un correo válido"
5. Escribir "usuario@mail.com" → Error desaparece

### Prueba 2: Validaciones de Contraseña
1. Dejar campo vacío → Ver "La contraseña es obligatoria"
2. Escribir "12345" (5 caracteres) → Ver "Debe tener al menos 6 caracteres"
3. Escribir "abcdef" (sin mayúscula) → Ver "Debe tener al menos una mayúscula"
4. Escribir "Abcdef" (sin número) → Ver "Debe tener al menos un número"
5. Escribir "Abc123" → Todas las validaciones pasan

### Prueba 3: Flujo Completo
1. Ingresar email: "test@mail.com"
2. Ingresar contraseña: "Pass123"
3. Presionar "Ingresar"
4. Verificar navegación a pantalla de usuarios
5. Verificar AppBar muestra: "Bienvenido: test@mail.com"

### Prueba 4: Botones Adicionales
1. Presionar "Crear cuenta" → Ver Snackbar
2. Presionar "¿Olvidaste tu contraseña?" → Ver Snackbar
3. Verificar que no navegan pero muestran mensaje

### Prueba 5: Integración con CRUD
1. Login exitoso → Ver lista de usuarios
2. Agregar usuario desde el FloatingActionButton
3. Editar usuario existente
4. Eliminar usuario
5. Email del login permanece en AppBar

## Diferencias Técnicas Clave

### Form con GlobalKey vs Campos Individuales
**Con Form:**
- Validación centralizada con un solo comando
- save() automático en todos los campos
- Código más limpio y mantenible

**Sin Form:**
- Validación manual campo por campo
- Más código y propenso a errores

### onSaved vs TextEditingController

**onSaved (usado en Login):**
```dart
String _email = '';
onSaved: (value) => _email = value!,
```
- Solo guarda al final del formulario
- No necesita dispose()
- Ideal para formularios simples

**TextEditingController (no usado aquí):**
```dart
final emailController = TextEditingController();
emailController.text; // Acceso continuo
emailController.dispose(); // Requiere limpieza
```
- Acceso continuo al texto
- Requiere gestión manual
- Ideal para búsquedas en tiempo real

## Conclusiones

Esta práctica consolida los conceptos esenciales de formularios en Flutter:

* **Form y GlobalKey** permiten validación centralizada y eficiente
* **validator y onSaved** separan responsabilidades claramente
* **onSaved es más simple** que TextEditingController para casos de uso básicos
* **RegExp** permite validaciones complejas de formato
* **Navigator.push** con parámetros permite flujos dinámicos
* **Integración con Provider** mantiene estado global de la app

Se logró un sistema de login profesional, seguro y con excelente experiencia de usuario.

**Autor:** Delgado Chipana Piero Adrián