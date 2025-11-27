/*
 * Descripción: Pantalla principal con menú de opciones para gestión de tortas
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 2024-11-20
 * Fecha última modificación: 2024-11-27
 */

package com.example.gestiontortas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnCrearTorta: Button
    private lateinit var btnCostosInsumos: Button
    private lateinit var btnApartados: Button
    private lateinit var btnSalir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarVistas()
        configurarEventos()
    }

    private fun inicializarVistas() {
        btnCrearTorta = findViewById(R.id.btnCrearTorta)
        btnCostosInsumos = findViewById(R.id.btnCostosInsumos)
        btnApartados = findViewById(R.id.btnApartados)
        btnSalir = findViewById(R.id.btnSalir)
    }

    private fun configurarEventos() {
        btnCrearTorta.setOnClickListener {
            val intent = Intent(this, CrearTortaActivity::class.java)
            startActivity(intent)
        }

        btnCostosInsumos.setOnClickListener {
            val intent = Intent(this, CostosInsumosActivity::class.java)
            startActivity(intent)
        }

        btnApartados.setOnClickListener {
            val intent = Intent(this, ApartadosActivity::class.java)
            startActivity(intent)
        }

        btnSalir.setOnClickListener {
            finish()
        }
    }
}