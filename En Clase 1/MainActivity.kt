/*
 * Descripción: Mostrar el cíclo de vida de una o varias actividades de android con un contador
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 10/09/2025
 * Fecha última modificación: 10/09/2025
 */

package com.example.ciclodevidaapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private var contador = 0
    private lateinit var textViewContador: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewContador = findViewById(R.id.textViewContador)
        val btnAumentar = findViewById<Button>(R.id.btnAumentar)

        btnAumentar.setOnClickListener {
            contador++
            textViewContador.text = "Contador: $contador"
        }

        Log.d("CICLO", "Create")
        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Log.d("CICLO", "Corriendo")
        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("CICLO", "Resume")
        Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d("CICLO", "Pausado")
        Toast.makeText(this, "onPause()", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Log.d("CICLO", "Detenido")
        Toast.makeText(this, "onStop()", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("CICLO", "Destruido")
        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("CONTADOR", contador)
        Log.d("CICLO", "onSaveInstanceState llamado - Contador guardado: $contador")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        contador = savedInstanceState.getInt("CONTADOR")
        textViewContador.text = "Contador: $contador"
        Log.d("CICLO", "onRestoreInstanceState llamado - Contador restaurado: $contador")
    }
}