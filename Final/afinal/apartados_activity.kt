/*
 * Descripción: Pantalla para gestionar apartados (formas, tamaños, sabores, rellenos, forrados)
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 2024-11-20
 * Fecha última modificación: 2024-11-27
 */

package com.example.gestiontortas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ApartadosActivity : AppCompatActivity() {

    private lateinit var btnCrearMolde: Button
    private lateinit var btnSabores: Button
    private lateinit var btnRellenos: Button
    private lateinit var btnForrados: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apartados)

        inicializarVistas()
        configurarEventos()
    }

    private fun inicializarVistas() {
        btnCrearMolde = findViewById(R.id.btnCrearMolde)
        btnSabores = findViewById(R.id.btnSabores)
        btnRellenos = findViewById(R.id.btnRellenos)
        btnForrados = findViewById(R.id.btnForrados)
    }

    private fun configurarEventos() {
        btnCrearMolde.setOnClickListener {
            val intent = Intent(this, CrearMoldeActivity::class.java)
            startActivity(intent)
        }

        btnSabores.setOnClickListener {
            val intent = Intent(this, GestionarOpcionesActivity::class.java)
            intent.putExtra("tipo", "Sabores")
            startActivity(intent)
        }

        btnRellenos.setOnClickListener {
            val intent = Intent(this, GestionarOpcionesActivity::class.java)
            intent.putExtra("tipo", "Rellenos")
            startActivity(intent)
        }

        btnForrados.setOnClickListener {
            val intent = Intent(this, GestionarOpcionesActivity::class.java)
            intent.putExtra("tipo", "Forrados")
            startActivity(intent)
        }
    }
}