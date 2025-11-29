/*
 * Descripción: Pantalla genérica para gestionar sabores, rellenos y forrados
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 2024-11-20
 * Fecha última modificación: 2024-11-27
 */

package com.example.afinal

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class GestionarOpcionesActivity : AppCompatActivity() {

    private lateinit var tvTitulo: TextView
    private lateinit var etNombre: EditText
    private lateinit var btnAnadir: Button
    private lateinit var lvOpciones: ListView
    private val opciones = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>
    private var tipo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestionar_opciones)

        tipo = intent.getStringExtra("tipo") ?: "Opciones"
        
        inicializarVistas()
        configurarAdapter()
        configurarEventos()
        cargarOpcionesIniciales()
    }

    private fun inicializarVistas() {
        tvTitulo = findViewById(R.id.tvTitulo)
        etNombre = findViewById(R.id.etNombre)
        btnAnadir = findViewById(R.id.btnAnadir)
        lvOpciones = findViewById(R.id.lvOpciones)

        tvTitulo.text = "Gestionar $tipo"
        btnAnadir.text = "Añadir ${tipo.dropLast(1)}"
    }

    private fun configurarAdapter() {
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, opciones)
        lvOpciones.adapter = adapter
    }

    private fun configurarEventos() {
        btnAnadir.setOnClickListener {
            val nombre = etNombre.text.toString().trim()

            if (nombre.isEmpty()) {
                Toast.makeText(this, "Ingrese un nombre", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            opciones.add(nombre)
            adapter.notifyDataSetChanged()
            etNombre.setText("")
            Toast.makeText(this, "$nombre añadido", Toast.LENGTH_SHORT).show()
        }

        lvOpciones.setOnItemClickListener { _, _, position, _ ->
            opciones.removeAt(position)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Opción eliminada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cargarOpcionesIniciales() {
        when (tipo) {
            "Sabores" -> {
                opciones.addAll(listOf("Chocolate", "Vainilla", "Naranja"))
            }
            "Rellenos" -> {
                opciones.addAll(listOf("Manjar", "Frutilla", "Café", "Durazno", "Fresa", "Fosh"))
            }
            "Forrados" -> {
                opciones.addAll(listOf("Chantilli Blanco", "Chantilli Color", "Chantilli Café"))
            }
        }
        adapter.notifyDataSetChanged()
    }
}