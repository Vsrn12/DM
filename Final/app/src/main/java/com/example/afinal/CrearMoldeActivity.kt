/*
 * Descripción: Pantalla para crear moldes con forma y tamaño
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 2024-11-20
 * Fecha última modificación: 2024-11-27
 */

package com.example.afinal

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CrearMoldeActivity : AppCompatActivity() {

    private lateinit var spinnerForma: Spinner
    private lateinit var etTamano: EditText
    private lateinit var btnCrearMolde: Button
    private lateinit var lvMoldes: ListView
    private val moldes = mutableListOf<Molde>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_molde)

        inicializarVistas()
        configurarSpinners()
        configurarAdapter()
        configurarEventos()
    }

    private fun inicializarVistas() {
        spinnerForma = findViewById(R.id.spinnerForma)
        etTamano = findViewById(R.id.etTamano)
        btnCrearMolde = findViewById(R.id.btnCrearMolde)
        lvMoldes = findViewById(R.id.lvMoldes)
    }

    private fun configurarSpinners() {
        val formas = arrayOf("Circular", "Cuadrado", "Rectangular", "Corazón")
        spinnerForma.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, formas)
    }

    private fun configurarAdapter() {
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf<String>())
        lvMoldes.adapter = adapter
    }

    private fun configurarEventos() {
        btnCrearMolde.setOnClickListener {
            val forma = spinnerForma.selectedItem.toString()
            val tamano = etTamano.text.toString().toIntOrNull()

            if (tamano == null || tamano <= 0) {
                Toast.makeText(this, "Ingrese un tamaño válido en cm", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val molde = Molde(forma, tamano)
            moldes.add(molde)
            actualizarLista()
            
            etTamano.setText("")
            Toast.makeText(this, "Molde creado: $forma $tamano cm", Toast.LENGTH_SHORT).show()
        }
    }

    private fun actualizarLista() {
        val lista = moldes.map { "${it.forma} - ${it.tamano} cm" }
        adapter.clear()
        adapter.addAll(lista)
        adapter.notifyDataSetChanged()
    }
}

data class Molde(val forma: String, val tamano: Int)