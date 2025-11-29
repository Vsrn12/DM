# Aplicación de Gestión de Tortas

## Descripción
Aplicación Android para gestionar la creación y costeo de tortas personalizadas. Permite configurar características de las tortas (forma, tamaño, masa, sabores, rellenos, forrado), gestionar costos de insumos y administrar opciones disponibles de manera escalable.

## Características Principales

### 1. Crear Torta
Sistema completo de configuración de tortas con opciones personalizables y validaciones.

**Características:**
* Selección de forma del molde (Circular por defecto, escalable)
* Selección de tamaño en centímetros (20cm, 24cm, 28cm por defecto)
* Tipo de masa obligatorio: Tradicional o Premescla (selección única)
* Selección de sabor: Chocolate, Vainilla, Naranja (uno solo)
* Configuración de rellenos: mínimo 2, máximo 10 rellenos seleccionables
* Forrado con Chantilli: opciones Blanco, Color o Café
* Adicionales personalizados con precio

**Funcionalidades:**
* ✅ Spinners desplegables para todas las opciones
* ✅ RadioGroup para tipo de masa (solo una selección)
* ✅ Validación de cantidad de rellenos (2-10)
* ✅ Selección dinámica de rellenos según cantidad indicada
* ✅ Campo de adicionales con descripción y precio
* ✅ Botón "Calcular Costo" (preparado para futura implementación)

### 2. Costos de Insumos
Gestión completa de insumos con precios por mayor para cálculo de costos.

**Características:**
* Lista de insumos con nombre y precio
* Agregar nuevos insumos desde la interfaz
* Editar insumos existentes (nombre y precio)
* Eliminar insumos
* Precios editables en tiempo real
* Interfaz con ListView y Adapter personalizado

**Funcionalidades:**
* ✅ Diálogo para añadir insumo con nombre y precio
* ✅ Click en item para editar o eliminar
* ✅ Adapter personalizado para mostrar formato de precio
* ✅ Insumos iniciales precargados (Harina, Azúcar, Huevos)
* ✅ Validación de datos antes de guardar

### 3. Apartados (Gestión Escalable)
Sistema administrativo para gestionar todas las opciones disponibles en la creación de tortas.

**Características:**
* Crear Molde: Añadir nuevas formas y tamaños
* Gestionar Sabores: Añadir o eliminar sabores disponibles
* Gestionar Rellenos: Añadir o eliminar tipos de relleno
* Gestionar Forrados: Añadir o eliminar opciones de forrado
* Interfaz unificada para gestión de opciones

**Funcionalidades:**
* ✅ Crear Molde con forma y tamaño en cm personalizado
* ✅ Añadir sabores dinámicamente
* ✅ Añadir rellenos dinámicamente
* ✅ Añadir forrados dinámicamente
* ✅ Eliminar opciones tocando en la lista
* ✅ Opciones iniciales precargadas
* ✅ Sistema escalable para futuros apartados

## Tecnologías Utilizadas

**Framework y Lenguaje**
* Android SDK
* Kotlin
* Android Studio

**Componentes Android**
* **Activity**: 6 pantallas diferentes
* **Intent**: Navegación entre pantallas
* **Spinner**: Listas desplegables para selección
* **RadioGroup/RadioButton**: Selección única de tipo de masa
* **CheckBox**: Selección múltiple de rellenos
* **ListView**: Listas dinámicas de insumos y opciones
* **AlertDialog**: Diálogos para añadir/editar insumos
* **BaseAdapter**: Adapter personalizado para insumos

**Elementos de UI**
* **LinearLayout**: Layouts verticales organizados
* **ScrollView**: Soporte para formularios extensos
* **EditText**: Campos de entrada de texto y números
* **Button**: Botones de acción en todas las pantallas
* **TextView**: Etiquetas y títulos
* **Toast**: Mensajes informativos

**Recursos**
* **layout/**: 9 diseños XML diferenciados por color
* **Temas de color**: Naranja (principal), Verde (insumos), Morado (apartados), Azul (moldes), Amarillo (opciones)

## Estructura del Proyecto

```
app/src/main/java/com/example/gestiontortas/
├── MainActivity.kt                    # Menú principal
├── CrearTortaActivity.kt             # Formulario de creación
├── CostosInsumosActivity.kt          # Gestión de insumos
├── ApartadosActivity.kt              # Menú de apartados
├── CrearMoldeActivity.kt             # Crear formas y tamaños
├── GestionarOpcionesActivity.kt      # Gestionar sabores/rellenos/forrados
└── InsumosAdapter.kt                 # Adapter para lista de insumos

app/src/main/res/layout/
├── activity_main.xml                 # Pantalla principal
├── activity_crear_torta.xml          # Formulario de torta
├── activity_costos_insumos.xml       # Lista de insumos
├── activity_apartados.xml            # Menú apartados
├── activity_crear_molde.xml          # Crear moldes
├── activity_gestionar_opciones.xml   # Gestionar opciones
├── dialog_insumo.xml                 # Diálogo añadir/editar insumo
└── item_insumo.xml                   # Item de lista de insumo
```

## Flujos de la Aplicación

### Flujo 1: Crear Torta
1. **Pantalla Principal** → Presionar "CREAR TORTA"
2. **Seleccionar Forma** → Spinner con formas disponibles (Circular por defecto)
3. **Seleccionar Tamaño** → Spinner con tamaños en cm (20, 24, 28)
4. **Tipo de Masa** → RadioButton Tradicional o Premescla (obligatorio)
5. **Seleccionar Sabor** → Spinner con sabores (Chocolate, Vainilla, Naranja)
6. **Cantidad de Rellenos** → Ingresar número entre 2 y 10
7. **Confirmar Rellenos** → Se generan spinners dinámicamente
8. **Seleccionar Rellenos** → Elegir de opciones disponibles
9. **Forrado** → Seleccionar Chantilli y color
10. **Adicionales** → Opcional: descripción y precio
11. **Calcular Costo** → (Preparado para futura implementación)

### Flujo 2: Gestionar Insumos
1. **Pantalla Principal** → Presionar "COSTOS DE INSUMOS"
2. **Ver Lista** → Insumos actuales con precios
3. **Añadir Insumo** → Presionar botón, diálogo con nombre y precio
4. **Editar Insumo** → Tocar item, editar o eliminar
5. **Guardar Cambios** → Automático al confirmar

### Flujo 3: Gestionar Apartados
1. **Pantalla Principal** → Presionar "APARTADOS"
2. **Opciones Disponibles:**
   - **Crear Molde** → Forma + Tamaño en cm
   - **Gestionar Sabores** → Añadir/eliminar sabores
   - **Gestionar Rellenos** → Añadir/eliminar rellenos
   - **Gestionar Forrados** → Añadir/eliminar forrados
3. **Añadir Opción** → Ingresar nombre y añadir
4. **Eliminar Opción** → Tocar item en lista

## Validaciones Implementadas

### Crear Torta
* Tipo de masa es obligatorio (RadioGroup validado)
* Cantidad de rellenos entre 2 y 10
* Todos los campos con spinner tienen selección por defecto

### Costos de Insumos
* Nombre de insumo no puede estar vacío
* Precio debe ser número válido
* Confirmación antes de eliminar

### Crear Molde
* Tamaño debe ser número mayor a 0
* Forma seleccionada de spinner

### Gestionar Opciones
* Nombre no puede estar vacío
* Confirmación visual al añadir/eliminar

### Prueba 1: Menú Principal
1. Abrir app → Ver 4 botones
2. Verificar botones: Crear Torta, Costos Insumos, Apartados, Salir
3. Presionar Salir → App se cierra

### Prueba 2: Crear Torta Completa
1. Presionar "CREAR TORTA"
2. Seleccionar Forma: Circular
3. Seleccionar Tamaño: 24 cm
4. Seleccionar Tipo de Masa: Tradicional
5. Seleccionar Sabor: Chocolate
6. Ingresar cantidad rellenos: 3
7. Presionar "Confirmar Rellenos"
8. Verificar aparecen 3 spinners de rellenos
9. Seleccionar forrado: Chantilli
10. Seleccionar color: Blanco
11. Opcional: Adicional "Velas" precio "2"
12. Presionar "CALCULAR COSTO"
13. Ver Toast de confirmación

### Prueba 3: Validación Rellenos
1. Ingresar cantidad: 1 → Error "entre 2 y 10"
2. Ingresar cantidad: 15 → Error "entre 2 y 10"
3. Ingresar cantidad: 5 → Funciona correctamente

### Prueba 4: Gestionar Insumos
1. Ir a "COSTOS DE INSUMOS"
2. Ver 3 insumos precargados
3. Presionar "AÑADIR INSUMO"
4. Ingresar: Mantequilla, Precio: 18.5
5. Ver insumo añadido en lista
6. Tocar insumo "Harina"
7. Editar precio a 30.0
8. Guardar cambios
9. Verificar precio actualizado

### Prueba 5: Crear Moldes
1. Ir a "APARTADOS"
2. Presionar "CREAR MOLDE"
3. Forma: Cuadrado
4. Tamaño: 22 cm
5. Presionar "CREAR MOLDE"
6. Ver molde en lista: "Cuadrado - 22 cm"
7. Crear varios moldes diferentes

### Prueba 6: Gestionar Sabores
1. Ir a "APARTADOS" → "GESTIONAR SABORES"
2. Ver sabores precargados: Chocolate, Vainilla, Naranja
3. Añadir: "Fresa"
4. Ver "Fresa" en lista
5. Tocar "Naranja" → Se elimina
6. Verificar cambios

### Prueba 7: Gestionar Rellenos
1. Ir a "APARTADOS" → "GESTIONAR RELLENOS"
2. Ver rellenos precargados
3. Añadir: "Manzana"
4. Verificar añadido
5. Eliminar cualquier relleno tocándolo

### Prueba 8: Gestionar Forrados
1. Ir a "APARTADOS" → "GESTIONAR FORRADOS"
2. Ver forrados precargados
3. Añadir nuevos tipos
4. Eliminar tocando

## Conexiones Futuras

### Tipo de Masa → Costos de Insumos
Los dos tipos de masa (Tradicional y Premescla) tendrán diferentes insumos y cantidades. Esta conexión se implementará cuando se definan los porcentajes de cada insumo por tipo de masa.

### Cálculo de Costos
El botón "Calcular Costo" está preparado para:
1. Obtener insumos según tipo de masa seleccionada
2. Calcular cantidades según tamaño
3. Aplicar porcentajes de insumos
4. Sumar costos de rellenos y adicionales
5. Mostrar costo total

## Escalabilidad

La aplicación está diseñada para ser completamente escalable:

* **Formas**: Se pueden añadir infinitas formas desde Crear Molde
* **Tamaños**: Se crean con cualquier medida en cm
* **Sabores**: Añadir/eliminar desde Apartados
* **Rellenos**: Añadir/eliminar desde Apartados
* **Forrados**: Añadir/eliminar desde Apartados
* **Insumos**: Gestión completa desde Costos de Insumos

No hay límites hardcodeados, todo es dinámico y editable.

## Notas de Desarrollo

**Fecha Creación**: 20 de noviembre de 2024  
**Fecha Última Modificación**: 28 de noviembre de 2024

**Próximas Implementaciones**:
* Conexión tipo de masa con insumos específicos
* Sistema de porcentajes para cálculo de costos
* Guardado persistente de datos
* Historial de tortas creadas
* Nuevos apartados para calcular el costo de las nuevas creaciones en relleno, sabor, etc

**Autor:** Delgado Chipana Piero Adrián