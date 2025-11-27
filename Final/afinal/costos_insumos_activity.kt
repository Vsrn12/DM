/*
 * Descripción: Pantalla para gestionar costos de insumos con opción de añadir
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 2024-11-20
 * Fecha última modificación: 2024-11-27
 */

package com.example.gestiontortas

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class CostosInsumosActivity : AppCompatActivity() {

    private lateinit var lvInsumos: ListView
    private lateinit var btnAnadirInsumo: Button
    private val insumos = mutableListOf<Insumo>()
    private lateinit var adapter: InsumosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_costos_insumos)

        inicializarVistas()
        configurarAdapter()
        configurarEventos()
        cargarInsumosIniciales()
    }

    private fun inicializarVistas() {
        lvInsumos = findViewById(R.id.lvInsumos)
        btnAnadirInsumo = findViewById(R.id.btnAnadirInsumo)
    }

    private fun configurarAdapter() {
        adapter = InsumosAdapter(this, insumos)
        lvInsumos.adapter = adapter
    }

    private fun configurarEventos() {
        btnAnadirInsumo.setOnClickListener {
            mostrarDialogoAnadirInsumo()
        }

        lvInsumos.setOnItemClickListener { _, _, position, _ ->
            mostrarDialogoEditarInsumo(position)
        }
    }

    private fun cargarInsumosIniciales() {
        insumos.add(Insumo("Harina", 25.0))
        insumos.add(Insumo("Azúcar", 15.0))
        insumos.add(Insumo("Huevos", 12.0))
        adapter.notifyDataSetChanged()
    }

    private fun mostrarDialogoAnadirInsumo() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_insumo, null)
        val etNombre = dialogView.findViewById<EditText>(R.id.etNombreInsumo)
        val etPrecio = dialogView.findViewById<EditText>(R.id.etPrecioInsumo)

        AlertDialog.Builder(this)
            .setTitle("Añadir Insumo")
            .setView(dialogView)
            .setPositiveButton("Añadir") { _, _ ->
                val nombre = etNombre.text.toString()
                val precio = etPrecio.text.toString().toDoubleOrNull()

                if (nombre.isNotEmpty() && precio != null) {
                    insumos.add(Insumo(nombre, precio))
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "Insumo añadido", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Datos inválidos", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun mostrarDialogoEditarInsumo(position: Int) {
        val insumo = insumos[position]
        val dialogView = layoutInflater.inflate(R.layout.dialog_insumo, null)
        val etNombre = dialogView.findViewById<EditText>(R.id.etNombreInsumo)
        val etPrecio = dialogView.findViewById<EditText>(R.id.etPrecioInsumo)

        etNombre.setText(insumo.nombre)
        etPrecio.setText(insumo.precio.toString())

        AlertDialog.Builder(this)
            .setTitle("Editar Insumo")
            .setView(dialogView)
            .setPositiveButton("Guardar") { _, _ ->
                val nombre = etNombre.text.toString()
                val precio = etPrecio.text.toString().toDoubleOrNull()

                if (nombre.isNotEmpty() && precio != null) {
                    insumos[position] = Insumo(nombre, precio)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "Insumo actualizado", Toast.LENGTH_SHORT).show()
                }
            }
            .setNeutralButton("Eliminar") { _, _ ->
                insumos.removeAt(position)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Insumo eliminado", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}

data class Insumo(val nombre: String, val precio: Double)