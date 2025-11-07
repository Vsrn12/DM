# Práctica: Navegación Inferior (BottomNavigationBar) en Flutter

## Descripción
Esta práctica implementa un sistema de navegación por pestañas usando BottomNavigationBar en Flutter, comprendiendo la relación entre pantallas, navegación, estado y comunicación entre componentes.

## Ejercicios Implementados

### 1. Navegación con BottomNavigationBar
Sistema de navegación con cuatro pestañas principales: Inicio, Usuarios, Configuración y Perfil.

**Características:**
* Barra de navegación inferior fija
* Cuatro pestañas independientes
* Cambio de vista sin recargar la aplicación
* Índice de selección controlado por estado
* Separación modular de componentes

**Funcionalidades:**
* Navegación fluida entre pestañas
* Indicador visual de pestaña activa
* Iconos representativos para cada sección
* Estado persistente durante la navegación

### 2. Perfil con Edición y Comunicación entre Pantallas
Tab de perfil con navegación a pantalla secundaria para editar información del usuario y retorno de datos.

**Características:**
* Pantalla de perfil con información del usuario
* Botón de navegación a pantalla de edición
* TextField para captura de nombre
* Retorno de datos usando Navigator.pop()
* Actualización automática de UI con setState()

**Funcionalidades Avanzadas:**
* Comunicación bidireccional entre pantallas
* Paso de datos mediante Navigator
* Validación de datos recibidos
* Actualización dinámica de estado
* Liberación correcta de recursos (dispose)

### Componentes Flutter
* **StatefulWidget**: Manejo de estado mutable
* **StatelessWidget**: Componentes sin estado
* **BottomNavigationBar**: Barra de navegación inferior
* **Navigator**: Sistema de navegación entre pantallas
* **TextField**: Captura de entrada de usuario
* **TextEditingController**: Control de campos de texto
* **MaterialPageRoute**: Transiciones entre pantallas

### Conceptos Aplicados
* Gestión de estado con setState()
* Navegación push y pop
* Comunicación entre pantallas
* Ciclo de vida de widgets
* Paso de parámetros entre rutas

## Estructura del Proyecto

```
lib/
├── main.dart                      # Punto de entrada
├── screens/
│   ├── home_page.dart            # Pantalla principal con BottomNavigationBar
│   ├── inicio_tab.dart           # Tab de inicio
│   ├── usuarios_tab.dart         # Tab de usuarios
│   ├── configuracion_tab.dart    # Tab de configuración
│   ├── perfil_tab.dart           # Tab de perfil con navegación
│   └── editar_perfil_screen.dart # Pantalla de edición de perfil
```

## Características Técnicas

### BottomNavigationBar
* Tipo: BottomNavigationBarType.fixed
* Items: 4 pestañas
* Color seleccionado: Azul
* Color no seleccionado: Gris
* Índice controlado por estado

### Navegación
* Push: MaterialPageRoute para nueva pantalla
* Pop: Retorno con datos capturados
* Async/await: Espera de resultado de navegación
* Validación: Verificación de datos no nulos

### Gestión de Estado
* StatefulWidget en componentes con estado mutable
* setState() para actualización de UI
* Variables privadas con guion bajo
* dispose() para liberar recursos

## Buenas Prácticas Implementadas

* Separación de componentes en archivos individuales
* Uso correcto de StatefulWidget y StatelessWidget
* Liberación de recursos con dispose()
* Validación de datos recibidos
* Comentarios descriptivos en código
* Nomenclatura clara y consistente
* Estructura modular y escalable

## Respuestas

### 1. ¿Por qué se recomienda usar un StatefulWidget para manejar el índice de la pestaña seleccionada?

Se recomienda usar StatefulWidget porque el índice de la pestaña seleccionada es un valor que cambia durante la ejecución de la aplicación. Cada vez que el usuario toca una pestaña diferente, necesitamos actualizar la UI para mostrar el contenido correspondiente. StatefulWidget permite:

* Almacenar el índice actual en una variable de estado
* Usar setState() para notificar al framework que debe reconstruir el widget
* Mantener el estado entre reconstrucciones del widget
* Reflejar cambios visuales inmediatos en la interfaz

Si usáramos StatelessWidget, no podríamos cambiar el índice seleccionado ni actualizar la vista mostrada.

### 2. ¿Qué ventajas ofrece separar cada pestaña en su propio widget o pantalla?

La separación de pestañas en widgets individuales ofrece múltiples ventajas:

**Organización:** Código más limpio y fácil de mantener con archivos separados por responsabilidad.

**Reutilización:** Los widgets separados pueden ser utilizados en otras partes de la aplicación si es necesario.

**Escalabilidad:** Facilita agregar nuevas funcionalidades a cada pestaña sin afectar las demás.

**Trabajo en equipo:** Diferentes desarrolladores pueden trabajar en pestañas distintas simultáneamente.

**Debugging:** Errores en una pestaña no afectan directamente a las otras, facilitando la identificación de problemas.

**Testing:** Cada componente puede ser probado de forma independiente.

**Rendimiento:** Flutter solo reconstruye el widget que cambió, no toda la aplicación.

### 3. ¿Cómo cambia la navegación entre pantallas al usar rutas nombradas en lugar de Navigator.push()?

Las rutas nombradas ofrecen un enfoque diferente a la navegación directa:

**Con Navigator.push():**
* Se crea la ruta directamente en el punto de navegación
* Requiere importar la clase de la pantalla destino
* Mayor acoplamiento entre componentes
* Dificulta la navegación profunda (deep linking)

**Con rutas nombradas:**
* Se definen todas las rutas en un solo lugar (MaterialApp)
* Se navega usando strings como identificadores
* Menor acoplamiento, las pantallas no necesitan conocerse entre sí
* Facilita la implementación de deep linking y navegación programática
* Permite pasar argumentos de forma estructurada
* Mejor organización para aplicaciones grandes

**Ejemplo:**
```dart
// Sin rutas nombradas
Navigator.push(context, MaterialPageRoute(builder: (context) => EditarPerfilScreen()));

// Con rutas nombradas
Navigator.pushNamed(context, '/editar-perfil');
```

### 4. ¿De qué forma el uso de Navigator.pop(context, data) facilita la comunicación entre pantallas?

Navigator.pop(context, data) facilita la comunicación de retorno mediante:

**Retorno de valores:** Permite enviar datos desde la pantalla actual hacia la pantalla anterior que la invocó.

**Patrón async/await:** La pantalla que hace push puede esperar el resultado usando await, haciendo el código más legible y secuencial.

**Validación de datos:** La pantalla receptora puede validar si recibió datos (null check) antes de procesarlos.

**Comunicación unidireccional clara:** Establece un flujo de datos definido: pantalla A → pantalla B → retorno a pantalla A con datos.

**Sin necesidad de estado global:** No requiere providers, bloc u otros gestores de estado complejos para comunicación simple.

**Ejemplo práctico:**
```dart
// Pantalla A espera resultado
final nombre = await Navigator.push(...);
if (nombre != null) {
  setState(() { _nombreUsuario = nombre; });
}

// Pantalla B retorna dato
Navigator.pop(context, _controller.text);
```

Este patrón es ideal para formularios, configuraciones y capturas de datos puntuales.

### 5. ¿Qué posibles mejoras o extensiones podrías agregar a esta aplicación para hacerla más completa?

**Persistencia de datos:**
* Usar SharedPreferences para guardar el nombre del usuario
* Implementar base de datos local con SQLite o Hive

**Validaciones mejoradas:**
* Agregar validación de formularios en TextField
* Mensajes de error personalizados
* Longitud mínima y máxima de nombre

**Funcionalidades adicionales:**
* Agregar foto de perfil con selección de imagen
* Implementar más campos editables (correo, teléfono, bio)
* Sistema de autenticación

**UI/UX mejorada:**
* Animaciones en transiciones de navegación
* Temas claro y oscuro
* Diseño más elaborado con cards y avatares

**Rutas nombradas:**
* Implementar sistema de rutas nombradas para mejor organización
* Manejo de rutas desconocidas

---

**Autor:** Delgado Chipana Piero Adrián  