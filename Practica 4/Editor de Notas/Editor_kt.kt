/*
 * Descripción: Editor de nota rápida - Permite escribir y editar notas
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 2025-09-24
 * Fecha última modificación: 2025-09-24
 */

package com.example.editornotas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class EditorActivity : AppCompatActivity() {

    private lateinit var etNota: EditText
    private lateinit var btnCompartir: Button

    private val opcionesLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val notaEditada = result.data?.getStringExtra("nota_editada")
            notaEditada?.let { nota ->
                etNota.setText(nota)
                etNota.setSelection(nota.length)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        inicializarVistas()
        configurarEventos()
    }

    private fun inicializarVistas() {
        etNota = findViewById(R.id.etNota)
        btnCompartir = findViewById(R.id.btnCompartir)
    }

    private fun configurarEventos() {
        btnCompartir.setOnClickListener {
            val textoNota = etNota.text.toString().trim()
            
            if (textoNota.isEmpty()) {
                Toast.makeText(this, "Escribe algo antes de compartir", Toast.LENGTH_SHORT).show()
                etNota.requestFocus()
                return@setOnClickListener
            }

            val intent = Intent(this, OpcionesActivity::class.java)
            intent.putExtra("nota", textoNota)
            opcionesLauncher.launch(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("nota_texto", etNota.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val textoGuardado = savedInstanceState.getString("nota_texto", "")
        etNota.setText(textoGuardado)
        if (textoGuardado.isNotEmpty()) {
            etNota.setSelection(textoGuardado.length)
        }
    }
}