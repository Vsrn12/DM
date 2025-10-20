/*
Descripción: Actividad principal que actúa como contenedor para los fragments. Inicializa la base de datos Room y carga WelcomeFragment al inicio. Usa FragmentManager para navegación manual, como se vio en clase (semana 5). Mantengo simple para cumplir con requerimientos básicos.
Autor: Delgado Chipana Piero Adrián
Fecha creación: 2025-10-17
Fecha última modificación: 2025-10-19
*/

package com.example.parcial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var database: AppDatabase  // Instancia global de Room para acceso desde fragments
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializamos Room para persistencia de puntajes
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "score_database"
        ).build()

        // Carga inicial de WelcomeFragment si no hay estado previo
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, WelcomeFragment())
                .commit()
        }
    }
}