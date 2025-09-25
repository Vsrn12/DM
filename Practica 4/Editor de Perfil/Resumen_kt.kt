/*
 * Descripción: Editor de perfil con confirmación - Muestra resumen y opciones de confirmación
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 2025-09-24
 * Fecha última modificación: 2025-09-24
 */

package com.example.editorperfil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResumenActivity : AppCompatActivity() {

    private lateinit var tvResumen: TextView
    private lateinit var btnConfirmar: Button
    private lateinit var btnEditar: Button
    private var usuario: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumen)

        inicializarVistas()
        obtenerDatos()
        mostrarResumen()
        configurarEventos()
    }

    private fun inicializarVistas() {
        tvResumen = findViewById(R.id.tvResumen)
        btnConfirmar = findViewById(R.id.btnConfirmar)
        btnEditar = findViewById(R.id.btnEditar)
    }

    private fun obtenerDatos() {
        usuario = intent.getSerializableExtra("usuario") as? Usuario
    }

    private fun mostrarResumen() {
        usuario?.let { user ->
            val resumen = """
                📋 RESUMEN DE PERFIL
                
                Nombre: ${user.nombre}
                Edad: ${user.edad} años
                Ciudad: ${user.ciudad}
                Correo: ${user.correo}
                
                ¿Los datos son correctos?
            """.trimIndent()
            
            tvResumen.text = resumen
        }
    }

    private fun configurarEventos() {
        btnConfirmar.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("confirmed", true)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        btnEditar.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("confirmed", false)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}