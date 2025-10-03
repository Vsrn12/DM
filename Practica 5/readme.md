# Práctica 5: Fragments y Navegación

## Descripción
Esta práctica consiste en resolver un ejercicio de navegación entre fragments en Android utilizando Kotlin y Android Studio, implementando paso de datos con Bundle, comunicación bidireccional entre fragments y manejo manual de navegación sin Navigation Component.

## Ejercicio Implementado

### Configurador de Pedido de Comida (4 Fragments)
Sistema completo de configuración de pedido paso a paso con navegación manual entre fragments, permitiendo seleccionar comida, agregar extras, revisar el pedido completo y confirmar o editar.

**Características:**
* Navegación manual paso a paso con 4 fragments diferentes
* Selección de comida principal mediante RadioGroup (Pizza, Hamburguesa, Ensalada)
* Selección múltiple de extras mediante CheckBox (Bebida, Papas, Postre)
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
* ✅ Temas de color diferenciados por pantalla (azul, naranja, morado, verde)

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

**Recursos**
* **layout/**: 5 diseños XML (activity + 4 fragments)
* **Temas de color**: Azul (inicio), Naranja (comida), Morado (extras), Verde (resumen)

## Estructura del Proyecto

```
ConfiguradorPedido/
├── java/com/example/configuradorpedido/
│   ├── MainActivity.kt                    # Activity con navegación manual
│   ├── InicioFragment.kt                  # Fragment de inicio
│   ├── SeleccionComidaFragment.kt         # Selección de comida
│   ├── SeleccionExtrasFragment.kt         # Selección de extras
│   └── ResumenPedidoFragment.kt           # Resumen y confirmación
├── res/layout/
│   ├── activity_main.xml                  # FrameLayout contenedor
│   ├── fragment_inicio.xml                # Interfaz de inicio (azul)
│   ├── fragment_seleccion_comida.xml      # Interfaz selección comida (naranja)
│   ├── fragment_seleccion_extras.xml      # Interfaz selección extras (morado)
│   └── fragment_resumen_pedido.xml        # Interfaz resumen (verde)
```

## Flujos de Navegación

### Flujo 1: Armar Pedido Completo
1. **InicioFragment** → Usuario presiona "Nuevo Pedido"
2. **SeleccionComidaFragment** → Selecciona comida con RadioButton
3. **Validación** → Debe seleccionar una opción antes de continuar
4. **SeleccionExtrasFragment** → Marca extras con CheckBox (opcionales)
5. **ResumenPedidoFragment** → Muestra resumen completo
6. **Confirmar** → Toast "Pedido confirmado" y regreso a inicio

### Flujo 2: Editar Pedido
1. **ResumenPedidoFragment** → Usuario presiona "Editar Pedido"
2. **setFragmentResult** → Envía datos actuales con key "pedido_editado"
3. **popBackStack** → Regresa 2 pantallas atrás a SeleccionComidaFragment
4. **setFragmentResultListener** → Recibe datos y restaura selecciones
5. **SeleccionComidaFragment** → Comida previamente seleccionada ya marcada
6. **SeleccionExtrasFragment** → Extras previamente marcados ya activos
7. **Usuario modifica** → Puede cambiar cualquier selección
8. **Continuar** → Navega nuevamente al resumen con datos actualizados

## Características Técnicas Implementadas

### Navegación Manual con FragmentManager
* **cargarFragment()**: Reemplaza fragment con addToBackStack
* **cargarFragmentSinBackStack()**: Reemplaza fragment sin historial
* **volverAFragment()**: Limpia stack y carga fragment específico
* **replace()**: Cambia fragment actual por uno nuevo
* **addToBackStack()**: Permite regresar con botón Back

### Paso de Datos entre Fragments
* **Bundle**: Contenedor de datos primitivos y strings
* **arguments**: Propiedad para pasar datos al fragment
* **bundleOf()**: Función helper para crear bundles
* **getString(), getBoolean()**: Extracción de datos del Bundle

### Comunicación Bidireccional
* **setFragmentResult()**: Envía datos de vuelta con request key
* **setFragmentResultListener()**: Escucha datos del fragment posterior
* **Request Key**: Identificador único "pedido_editado"
* **Restauración automática**: Datos se preservan al regresar

### Validación y UX
* **Validación de selección**: RadioGroup checkedRadioButtonId
* **Toast informativos**: Feedback en confirmar y validaciones
* **Temas diferenciados**: Color único por pantalla
* **Iconos descriptivos**: Emojis en textos para claridad

## Instrucciones de Instalación

### Archivos Kotlin - Copiar en:
```
app/src/main/java/com/example/configuradorpedido/
```
* MainActivity.kt
* InicioFragment.kt
* SeleccionComidaFragment.kt
* SeleccionExtrasFragment.kt
* ResumenPedidoFragment.kt

### Archivos Layout - Copiar en:
```
app/src/main/res/layout/
```
* activity_main.xml
* fragment_inicio.xml
* fragment_seleccion_comida.xml
* fragment_seleccion_extras.xml
* fragment_resumen_pedido.xml

### Pasos para Ejecutar:
1. Crear proyecto: ConfiguradorPedido
2. Copiar archivos en carpetas correspondientes
3. Sync Project with Gradle Files
4. Run app

## Instrucciones para Probar

### Prueba 1: Flujo Completo
1. Abrir app → Pantalla azul de inicio
2. Presionar "NUEVO PEDIDO"
3. Seleccionar "Pizza" → "SIGUIENTE"
4. Marcar "Bebida" y "Papas" → "SIGUIENTE"
5. Ver resumen: Pizza + Bebida, Papas
6. Presionar "CONFIRMAR PEDIDO"
7. Ver Toast "Pedido confirmado exitosamente"
8. Verificar regreso a pantalla de inicio

### Prueba 2: Editar Pedido
1. Nuevo pedido → Seleccionar "Hamburguesa"
2. Agregar "Postre" → Ver resumen
3. Presionar "EDITAR PEDIDO"
4. Verificar "Hamburguesa" sigue seleccionada
5. Cambiar a "Ensalada" → Siguiente
6. Verificar "Postre" sigue marcado
7. Agregar "Bebida" → Siguiente
8. Ver resumen actualizado: Ensalada + Postre, Bebida
9. Confirmar pedido

### Prueba 3: Validaciones
1. Nuevo pedido → NO seleccionar comida
2. Presionar "SIGUIENTE"
3. Ver Toast "Selecciona una comida"
4. Seleccionar comida → Funciona correctamente

### Prueba 4: Botón Back
1. Armar pedido hasta resumen
2. Presionar botón Back del dispositivo
3. Verificar regreso a pantalla anterior (extras)
4. Presionar Back nuevamente
5. Verificar regreso a selección de comida

**Autor:** Delgado Chipana Piero Adrián