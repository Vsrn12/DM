/*
Descripción: Entidad Room para almacenar puntajes. Contiene ID auto-generado y el valor del puntaje. Simple para cumplir con la funcionalidad adicional de historial persistente.
Autor: Delgado Chipana Piero Adrián
Fecha creación: 2025-10-17
Fecha última modificación: 2025-10-19
*/

package com.example.parcial

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scores")
data class ScoreEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val score: Int
)