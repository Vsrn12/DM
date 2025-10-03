# En clase 2: RecyclerView con Edición y Long Click

## Descripción

Esta práctica consiste en extender la funcionalidad base de un `RecyclerView` en Android utilizando Kotlin, implementando la detección de un `long click` en los ítems, la presentación de un menú de acciones mediante un `AlertDialog` y la edición de datos a través de un diálogo personalizado.

## Ejercicio Implementado

### RecyclerView con Edición y Eliminación mediante Long Click

Implementación completa de la gestión de elementos de un `RecyclerView` a través de interacciones avanzadas del usuario:

| Característica | Descripción |
| :--- | :--- |
| **Long Click** | El `UsuarioViewHolder` detecta una pulsación larga (`setOnLongClickListener`). |
| **Menú de Acción** | Se muestra un `AlertDialog` con las opciones "Editar" y "Eliminar". |
| **Diálogo de Edición** | Se utiliza un `AlertDialog` con un layout personalizado (`dialog_edit_usuario.xml`) para modificar los campos del objeto `Usuario`. |
| **Actualización Eficiente** | El `UsuarioAdapter` utiliza callbacks para recibir los datos actualizados y llama a `notifyItemChanged()` o `notifyItemRemoved()` para refrescar la lista. |

### Funcionalidades:
✅ Detección de `long click` en el `ViewHolder`.
✅ Uso de `AlertDialog.Builder().setItems()` para crear el menú contextual.
✅ Implementación de `AlertDialog.Builder().setView()` para mostrar el formulario de edición.
✅ Manejo de callbacks para comunicar la acción del `ViewHolder` al `Adapter`.
✅ Inserción de nuevos usuarios mediante un `FloatingActionButton` (`notifyItemInserted`).
✅ Eliminación y Edición de usuarios mediante el menú de `long click` (`notifyItemRemoved` y `notifyItemChanged`).
✅ Validación de la posición con `bindingAdapterPosition != RecyclerView.NO_POSITION`.

### Tecnologías Utilizadas

| Framework y Lenguaje | Componentes Android | Elementos de UI |
| :--- | :--- | :--- |
| **Android SDK** | `RecyclerView`: Componente para listas eficientes. | `CardView`: Contenedor para los ítems de la lista. |
| **Kotlin** | `RecyclerView.Adapter`: Enlace de datos y vistas. | `FloatingActionButton`: Botón para agregar elementos. |
| **Android Studio** | `RecyclerView.ViewHolder`: Contenedor de las vistas de cada ítem. | `AlertDialog`: Ventana emergente para menú de acciones y edición. |
| | `Fragment`: Contenedor de la vista principal (`RecyclerViewFragment`). | `EditText`: Campos de entrada en el diálogo de edición. |
| | `AlertDialog.Builder`: Construcción de diálogos. | `TextView`: Visualización de los datos del usuario. |

## Preguntas de Reflexión

### 1. ¿Qué diferencia hay entre `notifyItemRemoved()`, `notifyItemInserted()` y `notifyItemChanged()`?

Estos métodos son cruciales para informar al `RecyclerView` sobre los cambios específicos en el conjunto de datos, permitiendo animaciones y un rendimiento eficiente:

| Método | Propósito | Efecto |
| :--- | :--- | :--- |
| **`notifyItemRemoved(pos)`** | Informa que el ítem en `pos` ha sido eliminado. | Muestra una **animación de borrado** y recalcula las posiciones de los ítems posteriores de forma automática. |
| **`notifyItemInserted(pos)`** | Informa que se insertó un nuevo ítem en `pos`. | Muestra una **animación de inserción** y actualiza todas las posiciones. |
| **`notifyItemChanged(pos)`** | Informa que el ítem en `pos` tiene datos actualizados. | Recicla y llama a `onBindViewHolder` **solo para esa posición**, sin animaciones de movimiento ni cambio de estructura de la lista. |

### 2. ¿Por qué es necesario validar `bindingAdapterPosition != RecyclerView.NO_POSITION`?

La propiedad `bindingAdapterPosition` retorna la posición del ítem dentro del `Adapter`. Es necesario validarla por las siguientes razones:
* **Seguridad:** El `RecyclerView` recicla vistas. Si un evento (como un click) se dispara mientras el `ViewHolder` está siendo animado, desvinculado o reciclado, la posición puede ser **`-1` (`NO_POSITION`)**.
* **Consistencia:** Al validar, se asegura que la acción de edición o eliminación solo se ejecute sobre un ítem de datos que **actualmente existe** y está vinculado al `Adapter`, evitando errores de índice (`IndexOutOfBoundsException`).

### 3. ¿Qué ventajas tiene usar un diálogo frente a abrir una nueva pantalla para editar?

| Ventaja | Descripción |
| :--- | :--- |
| **Contexto y Flujo** | El usuario permanece en la pantalla de la lista, manteniendo el contexto. Los diálogos son ideales para editar **datos menores** sin interrumpir el flujo de trabajo. |
| **Velocidad** | Los diálogos son más rápidos de mostrar que iniciar un nuevo `Activity` o realizar una `FragmentTransaction`, ofreciendo una experiencia de usuario más fluida. |
| **Simplicidad** | Se reduce la complejidad del código, ya que no se requiere gestionar la navegación (`FragmentManager` o `Navigation Component`) ni el paso bidireccional de datos complejos entre pantallas completas. |

Autor: Delgado Chipana Piero Adrián