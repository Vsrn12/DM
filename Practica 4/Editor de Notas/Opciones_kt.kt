/*
 * Descripción: Editor de nota rápida - Opciones para compartir o seguir editando
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 2025-09-24
 * Fecha última modificación: 2025-09-24
 */

package com.example.editornotas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OpcionesActivity : AppCompatActivity() {

    private lateinit var tvNota: TextView
    private lateinit var btnCompartirCorreo: Button
    private lateinit var btnEditarNuevo: Button
    private var notaTexto: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)

        inicializarVistas()
        obtenerNota()
        mostrarNota()
        configurarEventos()
    }

    private fun inicializarVistas() {
        tvNota = findViewById(R.id.tvNota)
        btnCompartirCorreo = findViewById(R.id.btnCompartirCorreo)
        btnEditarNuevo = findViewById(R.id.btnEditarNuevo)
    }

    private fun obtenerNota() {
        notaTexto = intent.getStringExtra("nota") ?: ""
    }

    private fun mostrarNota() {
        val textoMostrar = """
            📝 TU NOTA:
            
            "$notaTexto"
            
            ¿Qué deseas hacer?
        """.trimIndent()
        
        tvNota.text = textoMostrar
    }

    private fun configurarEventos() {
        btnCompartirCorreo.setOnClickListener {
            Toast.makeText(this, "Compartido por correo", Toast.LENGTH_LONG).show()
            setResult(RESULT_CANCELED)
            finish()
        }

        btnEditarNuevo.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("nota_editada", notaTexto)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}