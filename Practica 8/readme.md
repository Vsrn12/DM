# Práctica 5: Fragments y Navegación

## Descripción
Esta práctica consiste en resolver un ejercicio de navegación entre fragments en Android utilizando Kotlin y Android Studio, implementando paso de datos con Bundle, comunicación bidireccional entre fragments y manejo manual de navegación sin Navigation Component.

## Ejercicio Implementado

### Configurador de Pedido de Comida (4 Fragments)
Sistema completo de configuración de pedido paso a paso con navegación manual entre fragments, permitiendo seleccionar comida, agregar extras, revisar el pedido completo y confirmar o editar.

**Características:**
* Navegación manual paso a paso con 4 fragments diferentes
* Selección de comida principal mediante RadioGroup
* Selección múltiple de extras mediante CheckBox 
* Resumen completo del pedido antes de confirmar
* Opciones de confirmar pedido o regresar a editar manteniendo datos
* Comunicación bidireccional entre fragments con setFragmentResult

**Funcionalidades:**
* ✅ Navegación manual con FragmentManager
* ✅ Paso de datos hacia adelante con Bundle y arguments
* ✅ Comunicación de regreso con setFragmentResult y setFragmentResultListener
* ✅ Restauración de datos al editar pedido
* ✅ Uso de popBackStack para navegación hacia atrás
* ✅ Validación de selecciones antes de continuar
* ✅ Mensajes Toast informativos en acciones clave

**Funcionalidades Avanzadas:**
* ✅ FragmentResultListener para recibir datos editados
* ✅ Bundle para pasar múltiples datos entre fragments
* ✅ RadioGroup para selección única de comida
* ✅ CheckBox para selección múltiple de extras
* ✅ FragmentManager manual sin dependencias adicionales
* ✅ PopBackStack múltiple para regresar dos pantallas atrás
* ✅ Temas de color diferenciados por pantalla

## Tecnologías Utilizadas

**Framework y Lenguaje**
* Android SDK
* Kotlin
* Android Studio

**Componentes Android**
* **Fragment**: Pantallas modulares y reutilizables de UI
* **FragmentManager**: Gestión manual de transacciones de fragments
* **FragmentTransaction**: Control de navegación entre fragments
* **Bundle**: Contenedor de datos para paso entre fragments
* **FragmentResult**: Comunicación bidireccional moderna entre fragments

**Elementos de UI**
* **RadioGroup/RadioButton**: Selección única de comida
* **CheckBox**: Selección múltiple de extras
* **TextView**: Visualización de información y resúmenes
* **Button**: Controles de navegación y acciones
* **Toast**: Mensajes informativos emergentes
* **LinearLayout**: Layouts organizados verticalmente
* **FrameLayout**: Contenedor principal para fragments

## Estructura del Proyecto

```
ConfiguradorPedido/
├── java/com/example/a5/
│   ├── MainActivity.kt                  
│   ├── InicioFragment.kt                  
│   ├── SeleccionComidaFragment.kt       
│   ├── SeleccionExtrasFragment.kt        
│   └── ResumenPedidoFragment.kt          
├── res/layout/
│   ├── activity_main.xml                 
│   ├── fragment_inicio.xml               
│   ├── fragment_seleccion_comida.xml     
│   ├── fragment_seleccion_extras.xml      
│   └── fragment_resumen_pedido.xml        
```

**Autor:** Delgado Chipana Piero Adrián