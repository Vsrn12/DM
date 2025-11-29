/*
 * Descripción: Pantalla para crear una torta con selección de opciones
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 2024-11-20
 * Fecha última modificación: 2024-11-28
 */

package com.example.afinal

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CrearTortaActivity : AppCompatActivity() {

    private lateinit var spinnerForma: Spinner
    private lateinit var spinnerTamano: Spinner
    private lateinit var rgTipoMasa: RadioGroup
    private lateinit var rbTradicional: RadioButton
    private lateinit var rbPremescla: RadioButton
    private lateinit var spinnerSabor: Spinner
    private lateinit var etCantidadRellenos: EditText
    private lateinit var btnConfirmarRellenos: Button
    private lateinit var llRellenos: LinearLayout
    private lateinit var spinnerForrado: Spinner
    private lateinit var spinnerColorForrado: Spinner
    private lateinit var etAdicional: EditText
    private lateinit var etPrecioAdicional: EditText
    private lateinit var btnCalcularCosto: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_torta)

        inicializarVistas()
        configurarSpinners()
        configurarEventos()
    }

    private fun inicializarVistas() {
        spinnerForma = findViewById(R.id.spinnerForma)
        spinnerTamano = findViewById(R.id.spinnerTamano)
        rgTipoMasa = findViewById(R.id.rgTipoMasa)
        rbTradicional = findViewById(R.id.rbTradicional)
        rbPremescla = findViewById(R.id.rbPremescla)
        spinnerSabor = findViewById(R.id.spinnerSabor)
        etCantidadRellenos = findViewById(R.id.etCantidadRellenos)
        btnConfirmarRellenos = findViewById(R.id.btnConfirmarRellenos)
        llRellenos = findViewById(R.id.llRellenos)
        spinnerForrado = findViewById(R.id.spinnerForrado)
        spinnerColorForrado = findViewById(R.id.spinnerColorForrado)
        etAdicional = findViewById(R.id.etAdicional)
        etPrecioAdicional = findViewById(R.id.etPrecioAdicional)
        btnCalcularCosto = findViewById(R.id.btnCalcularCosto)
    }

    private fun configurarSpinners() {
        // Spinner Forma
        val formas = arrayOf("Circular")
        spinnerForma.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, formas)

        // Spinner Tamaño (en cm)
        val tamanos = arrayOf("20 cm", "24 cm", "28 cm")
        spinnerTamano.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tamanos)

        // Spinner Sabor
        val sabores = arrayOf("Chocolate", "Vainilla", "Naranja")
        spinnerSabor.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sabores)

        // Spinner Forrado
        val forrados = arrayOf("Chantilli")
        spinnerForrado.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, forrados)

        // Spinner Color Forrado
        val colores = arrayOf("Blanco", "Color", "Café")
        spinnerColorForrado.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colores)
    }

    private fun configurarEventos() {
        btnConfirmarRellenos.setOnClickListener {
            val cantidad = etCantidadRellenos.text.toString().toIntOrNull()
            
            if (cantidad == null || cantidad < 2 || cantidad > 10) {
                Toast.makeText(this, "Ingrese cantidad entre 2 y 10", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            llRellenos.removeAllViews()
            
            val rellenos = arrayOf("Manjar", "Frutilla", "Café", "Durazno", "Fresa", "Fosh")
            
            for (i in 0 until cantidad) {
                val checkBox = CheckBox(this)
                checkBox.text = "Relleno ${i + 1}"
                checkBox.tag = i
                
                val spinner = Spinner(this)
                spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, rellenos)
                
                llRellenos.addView(checkBox)
                llRellenos.addView(spinner)
            }
            
            Toast.makeText(this, "Seleccione $cantidad rellenos", Toast.LENGTH_SHORT).show()
        }

        btnCalcularCosto.setOnClickListener {
            if (!validarCampos()) return@setOnClickListener
            
            Toast.makeText(this, "Torta creada - Cálculo de costo próximamente", Toast.LENGTH_LONG).show()
        }
    }

    private fun validarCampos(): Boolean {
        if (rgTipoMasa.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Seleccione tipo de masa", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}