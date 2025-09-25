# Práctica 4: Comunicación entre Actividades

## Descripción
Esta práctica consiste en resolver 2 ejercicios de comunicación entre actividades en Android utilizando Kotlin y Android Studio, implementando envío de datos bidireccional, manejo de resultados y persistencia de estado durante rotación de pantalla.

## Ejercicios Implementados

### 1. Editor de Perfil con Confirmación (FormularioActivity.kt y ResumenActivity.kt)
Aplicación que permite capturar datos de perfil de usuario, mostrarlos en una segunda pantalla y confirmar o editar la información.

**Características:**
* Formulario con 4 campos: Nombre, Edad, Ciudad y Correo electrónico
* Validación de campos obligatorios con mensajes de error
* Navegación a pantalla de resumen con datos capturados
* Opciones de confirmar perfil o volver a editar
* Data class Usuario con Serializable para transferencia de objetos
* Interfaz con tema verde y diseño centrado

**Funcionalidades:**
* ✅ Captura de datos con validación en tiempo real
* ✅ Comunicación de datos usando Intent.putExtra() y Serializable
* ✅ Manejo de resultados con registerForActivityResult
* ✅ Toast "Perfil guardado correctamente" al confirmar
* ✅ Persistencia de datos durante rotación con onSaveInstanceState
* ✅ Limpieza automática de campos después de confirmar

**Funcionalidades Avanzadas:**
* ✅ Data class Usuario encapsula todos los datos del perfil
* ✅ setResult() para comunicación bidireccional entre actividades  
* ✅ Focus automático en campos con errores de validación
* ✅ Restauración completa de estado con onRestoreInstanceState
* ✅ ScrollView para soporte en pantallas pequeñas
* ✅ Diseño responsivo con elevación y colores temáticos

### 2. Editor de Nota Rápida (EditorActivity.kt y OpcionesActivity.kt)
Sistema de notas que permite escribir, compartir por correo simulado o continuar editando manteniendo el contenido.

**Características:**
* Campo de texto multilínea para escribir notas extensas
* Validación de contenido antes de compartir
* Pantalla de opciones con botones de compartir y editar
* Simulación de compartir por correo con Toast
* Retorno al editor manteniendo el texto original
* Interfaz diferenciada con tema naranja-morado

**Funcionalidades:**
* ✅ Editor multilínea con inputType optimizado para texto
* ✅ Comunicación bidireccional con Intent.putExtra()
* ✅ Toast "Compartido por correo" al seleccionar compartir
* ✅ Opción "Editar de nuevo" regresa con contenido intacto
* ✅ Persistencia de texto durante rotación con onSaveInstanceState
* ✅ Validación de contenido vacío con mensaje informativo

**Funcionalidades Avanzadas:**
* ✅ Posicionamiento inteligente del cursor al restaurar texto
* ✅ registerForActivityResult para manejo moderno de resultados
* ✅ setResult() con datos para comunicación de vuelta
* ✅ onRestoreInstanceState restaura texto y posición del cursor
* ✅ Tema visual diferenciado (naranja editor, morado opciones)
* ✅ Manejo completo del ciclo de vida para datos temporales

## Tecnologías Utilizadas

**Framework y Lenguaje**
* Android SDK
* Kotlin
* Android Studio

**Componentes Android**
* **Activity**: Múltiples pantallas con navegación
* **Intent**: Comunicación y transferencia de datos entre actividades
* **Bundle**: Persistencia de estado durante rotación de pantalla
* **registerForActivityResult**: Manejo moderno de resultados de actividades
* **Serializable**: Transferencia de objetos complejos entre actividades
* **LinearLayout**: Layouts organizados verticalmente
* **ScrollView**: Soporte para contenido extenso

**Elementos de UI**
* **EditText**: Campos de entrada con validación y tipos específicos
* **TextView**: Visualización de información y resúmenes
* **Button**: Controles de navegación y acciones
* **Toast**: Mensajes informativos emergentes

**Recursos**
* **layout/**: Diseños XML diferenciados por ejercicio
* **Temas de color**: Verde (perfil), Naranja-Morado (notas)

## Estructura del Proyecto

### Ejercicio 1: Editor de Perfil
```
EditorPerfil/
├── java/com/example/editorperfil/
│   ├── FormularioActivity.kt    # Captura datos + Data class Usuario
│   └── ResumenActivity.kt       # Confirmación de perfil
├── res/layout/
│   ├── activity_formulario.xml  # Interfaz formulario (tema verde)
│   └── activity_resumen.xml     # Interfaz confirmación
```

### Ejercicio 2: Editor de Notas
```
EditorNotas/
├── java/com/example/editornotas/
│   ├── EditorActivity.kt        # Editor multilínea
│   └── OpcionesActivity.kt      # Opciones compartir/editar
├── res/layout/
│   ├── activity_editor.xml      # Interfaz editor (tema naranja)
│   └── activity_opciones.xml    # Interfaz opciones (tema morado)
```

## Flujos de Navegación

### Flujo 1: Editor de Perfil
1. **FormularioActivity** → Usuario ingresa datos personales
2. **Validación** → Campos obligatorios verificados con mensajes de error
3. **ResumenActivity** → Muestra resumen completo de datos capturados
4. **Confirmación** → "Confirmar" guarda perfil o "Volver a editar" regresa
5. **Resultado** → Toast confirmación y limpieza de campos

### Flujo 2: Editor de Notas  
1. **EditorActivity** → Usuario escribe nota multilínea
2. **Validación** → Verifica que hay contenido antes de compartir
3. **OpcionesActivity** → Muestra nota completa y opciones disponibles
4. **Acción** → "Compartir por correo" (Toast) o "Editar de nuevo"
5. **Resultado** → Mantiene contenido para continuar editando

## Características Técnicas Implementadas

### Comunicación entre Actividades
* **Intent.putExtra()**: Transferencia de datos simples entre pantallas
* **Serializable**: Envío de objetos complejos (Data class Usuario)
* **registerForActivityResult**: Manejo moderno y seguro de resultados
* **setResult()**: Comunicación bidireccional con datos de retorno

### Manejo de Estado y Ciclo de Vida
* **onSaveInstanceState()**: Preserva datos antes de destrucción por rotación
* **onRestoreInstanceState()**: Restaura datos después de recreación
* **Bundle**: Contenedor seguro para datos temporales durante cambios de configuración

### Validación y Experiencia de Usuario
* **Validación en tiempo real**: Mensajes de error inmediatos en campos
* **Focus automático**: Navegación intuitiva hacia campos con errores
* **Toast informativos**: Feedback claro y consistente al usuario
* **Posicionamiento de cursor**: Experiencia fluida en editor multilínea

**Autor:** Delgado Chipana Piero Adrián