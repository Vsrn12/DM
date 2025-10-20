# Parcial: Juego de Colores con Fragments y Room

## Descripción
Esta parcial consiste en resolver un ejercicio de desarrollo de una aplicación Android utilizando Kotlin y Android Studio, implementando navegación entre fragments, un temporizador, RecyclerView para historial de puntajes, y Room para persistencia de datos como funcionalidad adicional obligatoria. El proyecto cumple con los requerimientos del examen parcial 2025-2, utilizando conceptos de las semanas 1 a 8.

## Ejercicio Implementado
**Juego de Adivinanza de Colores (3 Fragments)**  
Sistema interactivo donde el usuario debe presionar el botón que coincide con un color aleatorio mostrado en pantalla en un tiempo límite de 30 segundos. Incluye una pantalla de bienvenida con reglas, el juego principal, y una pantalla de resultados con historial y puntaje máximo.

## Características
- **Navegación manual**: Uso de FragmentManager para transiciones entre fragments.
- **Juego interactivo**: Color aleatorio, botones para responder, y temporizador de 30 segundos.
- **Feedback**: Mensajes Toast para aciertos y errores.
- **Persistencia**: Historial y puntaje máximo almacenados en Room.
- **Historial**: RecyclerView para mostrar puntajes de todas las sesiones.
- **UI clara**: ConstraintLayout para interfaces organizadas.
- **Reglas**: AlertDialog en la pantalla de bienvenida.

## Funcionalidades
✅ Navegación manual con FragmentManager  
✅ Paso de datos con Bundle (puntaje a ResultFragment)  
✅ Temporizador con CountDownTimer  
✅ Feedback visual con Toast  
✅ RecyclerView para historial de puntajes  
✅ Room para persistencia de puntajes y consulta de máximo  
✅ Cancelación de temporizador para evitar memory leaks  
✅ Validación de respuestas del usuario  

## Funcionalidades Avanzadas
✅ **Room**: Almacena historial de puntajes y calcula el máximo puntaje con consulta SQL.  
✅ Corutinas para operaciones asíncronas en Room.  
✅ ConstraintLayout para UI responsiva.  
✅ Recursos en strings.xml y colors.xml para buenas prácticas.  

## Tecnologías Utilizadas
### Framework y Lenguaje
- Android SDK
- Kotlin
- Android Studio

### Componentes Android
- **Fragment**: Pantallas modulares (Welcome, Game, Result).  
- **FragmentManager**: Gestión manual de navegación.  
- **CountDownTimer**: Temporizador de 30 segundos.  
- **RecyclerView**: Visualización de historial.  
- **Room**: Base de datos para puntajes.  
- **Bundle**: Paso de datos entre fragments.  
- **AlertDialog**: Reglas del juego.  
- **Toast**: Feedback al usuario.

### Elementos de UI
- **View**: Cuadro para mostrar color aleatorio.  
- **TextView**: Puntajes y tiempo restante.  
- **Button**: Selección de colores y acciones.  
- **ConstraintLayout**: Organización de layouts.  
- **RecyclerView**: Lista de puntajes.

## Estructura del Proyecto
```
Parcial/
├── java/com/example/parcial/
│   ├── MainActivity.kt
│   ├── WelcomeFragment.kt
│   ├── GameFragment.kt
│   ├── ResultFragment.kt
│   ├── AppDatabase.kt
│   ├── ScoreEntity.kt
│   ├── ScoreDao.kt
│   ├── ScoreAdapter.kt
├── res/layout/
│   ├── activity_main.xml
│   ├── fragment_welcome.xml
│   ├── fragment_game.xml
│   ├── fragment_result.xml
├── res/values/
│   ├── strings.xml
│   ├── colors.xml
```

## Autor
Delgado Chipana Piero Adrián