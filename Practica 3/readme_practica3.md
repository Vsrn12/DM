# **Práctica 3: Aplicaciones Básicas Android**

**Descripción**
Esta práctica consiste en resolver 2 ejercicios básicos de desarrollo Android utilizando Kotlin y Android Studio, implementando interacción con elementos de UI, manejo de eventos y reproducción multimedia.

## **Ejercicios Implementados**

### **1. Interacción con Imágenes (MainActivity.kt)**
Aplicación que muestra una imagen interactiva que responde al toque del usuario mostrando un Toast personalizado.

**Características:**
* Imagen clickeable centrada en pantalla
* Evento onClick configurado programáticamente  
* Toast con mensaje personalizado "Lo diste todo"
* Diseño responsivo con LinearLayout
* Imagen personalizada desde drawable (`@drawable/poke`)

**Funcionalidades:**
* ✅ Carga de imagen desde recursos drawable
* ✅ Configuración de listeners de clic
* ✅ Visualización de mensajes Toast
* ✅ Interfaz centrada y atractiva

### **2. Reproductor de Música Básico (MainActivity.kt)**  
Sistema completo de reproducción de audio con controles básicos de play, pause y stop usando MediaPlayer.

**Características:**
* Interfaz con botones de control (Play, Pause, Stop)
* Reproducción de archivo local `diganle.mp3`
* Estado visual dinámico del reproductor
* Manejo de ciclo de vida de MediaPlayer
* Control de errores y validaciones

**Funcionalidades Avanzadas:**
* ✅ Gestión de estados de reproducción (Playing, Paused, Stopped)
* ✅ Auto-pausa al minimizar aplicación
* ✅ Liberación automática de recursos en onDestroy()
* ✅ Manejo de finalización automática de pista
* ✅ Validaciones de estado antes de operaciones
* ✅ Mensajes informativos con Toast

## **Tecnologías Utilizadas**

### **Framework y Lenguaje**
* Android SDK
* Kotlin
* Android Studio

### **Componentes Android**
* **Activity**: Pantalla principal de aplicaciones
* **LinearLayout**: Layout vertical centrado
* **ImageView**: Visualización de imágenes
* **Button**: Botones de control
* **TextView**: Textos informativos
* **Toast**: Mensajes emergentes
* **MediaPlayer**: Reproducción de audio

### **Recursos**
* **drawable/**: Almacenamiento de imágenes
* **raw/**: Archivos de audio local
* **layout/**: Diseños XML de interfaz

## **Estructura del Proyecto**

```
app/src/main/
├── java/com/example/practica3/
│   └── MainActivity.kt          # Actividad principal
├── res/
│   ├── drawable/
│   │   └── poke.png            # Imagen interactiva
│   ├── raw/
│   │   └── diganle.mp3         # Archivo de audio
│   └── layout/
│       └── activity_main.xml    # Diseño de interfaz
```

**Autor:** Delgado Chipana Piero Adrián  