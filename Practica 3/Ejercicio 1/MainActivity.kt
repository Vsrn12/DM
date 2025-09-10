/*
 * Descripción: Aplicación que muestra una imagen y al hacer clic en ella despliega un Toast con mensaje personalizado
 * Autor: Delgado Chipana Piero Adrián
 * Fecha creación: 10/09/2025
 * Fecha última modificación: 11/09/2025
 */

package com.example.practica3

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener referencia de la ImageView
        val imageView = findViewById<ImageView>(R.id.imageView)

        // Configurar el listener para el clic en la imagen
        imageView.setOnClickListener {
            // Mostrar Toast con mensaje
            Toast.makeText(
                this,
                "Lo diste todo",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}