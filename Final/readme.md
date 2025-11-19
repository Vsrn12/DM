# Práctica: Listas Dinámicas y CRUD Local con Provider en Flutter

## Descripción
Esta práctica consiste en crear una aplicación Flutter que permite gestionar usuarios (agregar, editar y eliminar) utilizando el patrón arquitectónico MVVM con Provider para el manejo del estado. La aplicación implementa un CRUD completo en memoria sin necesidad de base de datos, enfocándose en buenas prácticas de desarrollo móvil.

## Objetivos

### Objetivo General
Crear una aplicación Flutter que permite agregar, editar y eliminar usuarios utilizando el patrón MVVM con Provider, reforzando el manejo del estado, la UI dinámica y el trabajo con formularios.

### Objetivos Específicos
- Aplicar el patrón MVVM para separar la lógica de negocio y la interfaz
- Implementar Provider para manejar el estado de forma eficiente
- Usar componentes interactivos como RadioButton y Switch
- Comprender cómo funcionan las listas dinámicas en Flutter (ListView.builder)

## Conceptos Implementados

### Patrón MVVM (Model-View-ViewModel)
Arquitectura que separa la vista (UI) de la lógica (ViewModel), facilitando la reutilización del código y el mantenimiento de la aplicación.

### Provider
Librería oficial recomendada por Flutter para manejar el estado. Permite que la vista se actualice automáticamente cuando el ViewModel notifica un cambio, usando ChangeNotifier.

### CRUD Local
Operaciones básicas de Create (Crear), Read (Leer), Update (Actualizar) y Delete (Eliminar) realizadas en memoria sin persistencia de datos.

## Estructura del Proyecto

```
gestion_usuarios_flutter/
├── lib/
│   ├── main.dart                    # Punto de entrada de la aplicación
│   ├── models/
│   │   └── user.dart                # Modelo de datos User
│   ├── viewmodels/
│   │   └── user_view_model.dart     # Lógica de negocio y estado
│   └── views/
│       ├── user_list_screen.dart    # Pantalla de lista de usuarios
│       └── user_form_screen.dart    # Formulario para agregar/editar
├── pubspec.yaml                     # Configuración y dependencias
└── README.md                        # Este archivo
```

## Funcionalidades Implementadas

### Gestión de Usuarios
- Agregar nuevos usuarios con nombre, género y estado activo
- Editar usuarios existentes
- Eliminar usuarios de la lista
- Visualizar lista completa de usuarios registrados

### Componentes Interactivos
- TextFormField para captura de nombre con validación
- RadioButton para selección de género (Masculino/Femenino)
- Switch para indicar si el usuario está activo o inactivo
- Formulario con validación completa

### Navegación
- Navegación entre pantallas usando Navigator
- Paso de datos entre pantallas
- Retorno de datos desde formulario a lista

## Tecnologías Utilizadas

### Framework y Lenguaje
- Flutter SDK
- Dart

### Gestión de Estado
- Provider (ChangeNotifier)
- ChangeNotifierProvider para inyección de dependencias

### Componentes Flutter
- Scaffold: Estructura base de pantallas
- AppBar: Barra superior con título
- ListView.builder: Lista dinámica de usuarios
- Card: Tarjetas para cada usuario
- ListTile: Diseño de elementos de lista
- Form: Formulario con validación
- TextFormField: Campo de texto con validación
- RadioListTile: Botones de opción
- SwitchListTile: Interruptor de estado
- FloatingActionButton: Botón flotante para agregar
- IconButton: Botones de acción (editar/eliminar)

## Guía de Uso de la Aplicación

### Agregar un Usuario
1. Presionar el botón flotante (+) en la esquina inferior derecha
2. Completar el formulario con nombre
3. Seleccionar género usando los radio buttons
4. Activar o desactivar el switch según corresponda
5. Presionar "Guardar"

### Editar un Usuario
1. En la lista de usuarios, presionar el icono de lápiz (editar)
2. Modificar los datos necesarios en el formulario
3. Presionar "Actualizar"

### Eliminar un Usuario
1. En la lista de usuarios, presionar el icono de papelera (eliminar)
2. El usuario se eliminará inmediatamente de la lista

## Patrón de Arquitectura

### Model (Modelo)
Representa los datos de la aplicación. En este caso, la clase User contiene:
- nombre: String
- genero: String
- activo: bool

### View (Vista)
Las pantallas que muestran la interfaz de usuario:
- UserListScreen: Muestra la lista de usuarios
- UserFormScreen: Formulario para crear/editar usuarios

### ViewModel
UserViewModel gestiona la lógica de negocio:
- Mantiene la lista de usuarios
- Proporciona métodos para agregar, editar y eliminar
- Notifica a las vistas cuando hay cambios (notifyListeners)

## Flujo de Datos

1. El usuario interactúa con la Vista (View)
2. La Vista llama a métodos del ViewModel
3. El ViewModel modifica el Modelo
4. El ViewModel notifica los cambios (notifyListeners)
5. Provider actualiza automáticamente todas las Vistas que escuchan

## Validaciones Implementadas

### Formulario de Usuario
- Nombre: Campo obligatorio, no puede estar vacío
- Género: Selección obligatoria mediante RadioButton
- Estado Activo: Valor booleano con Switch

## Actividades Complementarias Propuestas

1. Agregar campo de edad con validación numérica
2. Implementar campo de correo electrónico con validación de formato
3. Agregar filtro para mostrar solo usuarios activos
4. Implementar búsqueda de usuarios por nombre
5. Agregar confirmación antes de eliminar un usuario
6. Implementar persistencia local con SharedPreferences o SQLite

## Preguntas de Reflexión

### ¿Qué ventajas ofrece usar Provider frente a setState()?
Provider permite compartir estado entre múltiples widgets sin necesidad de pasar datos manualmente. Además, solo reconstruye los widgets que realmente necesitan actualizarse, mejorando el rendimiento.

### ¿Por qué es importante usar ChangeNotifier en el ViewModel?
ChangeNotifier permite que el ViewModel notifique a sus oyentes cuando hay cambios en el estado, lo que desencadena automáticamente la reconstrucción de los widgets que dependen de ese estado.

### ¿Qué sucedería si no se llamara a notifyListeners()?
Las vistas no se actualizarían automáticamente cuando cambie el estado. Los datos cambiarían en el ViewModel pero la UI no reflejaría esos cambios hasta que se forzara una reconstrucción manual.

## Ventajas del Patrón MVVM

- Separación clara de responsabilidades
- Código más mantenible y testeable
- Reutilización de ViewModels
- Facilita el trabajo en equipo
- Mejor organización del proyecto

## Notas Importantes

- Los datos se almacenan solo en memoria, se pierden al cerrar la app
- No se implementa persistencia de datos en esta versión
- La validación es básica, puede extenderse según necesidades
- El código está comentado para facilitar el aprendizaje


## Autor
Delgado Chipana Piero Adrián
