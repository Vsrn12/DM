# Práctica 4: Ciclo de Vida de Activity en Android

## Descripción
Esta práctica implementa una aplicación Android que demuestra el comportamiento del ciclo de vida de una Activity utilizando Kotlin y Android Studio, incluyendo manejo de estado, rotación de pantalla y logging detallado.

## Ejercicio Implementado

### Aplicación de Contador con Manejo de Ciclo de Vida (MainActivity.kt)
Aplicación interactiva que muestra un contador incrementable que conserva su estado durante cambios de configuración y demuestra cada fase del ciclo de vida.

**Características:**
* Contador incrementable mediante botón
* Conservación automática de estado durante rotación
* Logging detallado en Logcat de cada fase del ciclo
* Mensajes Toast informativos para cada método
* Interfaz centrada y responsiva con LinearLayout

**Funcionalidades:**
* ✅ Incremento de contador con persistencia de estado
* ✅ Implementación completa de métodos de ciclo de vida
* ✅ Logging con filtro personalizado "CICLO"
* ✅ Manejo de onSaveInstanceState y onRestoreInstanceState
* ✅ Interfaz visual clara y funcional
* ✅ Mensajes informativos en tiempo real

**Funcionalidades Avanzadas:**
* ✅ Gestión automática de Bundle para datos temporales
* ✅ Logging detallado para seguimiento de estados
* ✅ Conservación de datos durante cambios de configuración
* ✅ Manejo completo del ciclo: onCreate → onStart → onResume → onPause → onStop → onDestroy
* ✅ Restauración automática de estado con onRestoreInstanceState
* ✅ Filtrado de logs para debugging eficiente

## Tecnologías Utilizadas

**Framework y Lenguaje**
* Android SDK
* Kotlin
* Android Studio

**Componentes Android**
* **Activity**: Gestión completa del ciclo de vida
* **LinearLayout**: Layout vertical centrado
* **TextView**: Visualización del contador
* **Button**: Control de incremento
* **Bundle**: Almacenamiento temporal de estado
* **Toast**: Mensajes informativos
* **Log**: Sistema de logging para debugging

**Recursos**
* **layout/**: Diseños XML de interfaz
* **Logcat**: Consola de debugging con filtros

## Estructura del Proyecto

```
app/src/main/
├── java/com/example/ciclodevidaapp/
│   └── MainActivity.kt          # Actividad principal con ciclo de vida
├── res/
│   └── layout/
│       └── activity_main.xml    # Interfaz de contador
```

## Preguntas de Reflexión - Respuestas

### 1. ¿Qué sucede si no usamos onSaveInstanceState? ¿Por qué se pierde el contador?

Sin onSaveInstanceState el contador se reinicia a cero cada vez que rotamos la pantalla porque Android destruye completamente la Activity durante los cambios de configuración. Todas las variables en memoria, incluyendo nuestro contador, se eliminan cuando la Activity se destruye. Al crear la nueva Activity, las variables se inicializan con sus valores por defecto, por lo que el contador vuelve a cero. Es como cerrar y abrir la aplicación desde cero, no hay memoria compartida entre la Activity destruida y la nueva instancia.

### 2. ¿Por qué Android destruye y vuelve a crear la Activity al rotar la pantalla?

* **Cambio de configuración**: La orientación es un cambio de configuración del sistema
* **Recursos diferentes**: Portrait y landscape pueden usar diferentes layouts, imágenes, dimensiones  
* **Optimización de memoria**: Android recicla recursos para la nueva orientación
* **Consistencia**: Garantiza que la Activity use los recursos correctos para cada orientación

### 3. ¿En qué casos prácticos usarías onPause y onStop en una aplicación real?

**onPause:**
* Pausar video o música cuando llega una llamada o notificación
* Guardar progreso en un juego o formulario
* Pausar animaciones o timers para ahorrar batería  
* Detener sensores como GPS o acelerómetro temporalmente

**onStop:**
* Detener descargas no críticas para ahorrar datos
* Pausar actualizaciones de ubicación o red
* Liberar recursos costosos como cámaras o conexiones
* Guardar datos importantes en base de datos

**Autor:** Delgado Chipana Piero Adrián