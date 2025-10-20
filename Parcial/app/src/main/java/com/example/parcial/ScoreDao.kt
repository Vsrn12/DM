/*
Descripción: DAO para operaciones CRUD en la tabla de puntajes. Incluye insertar, obtener todos los puntajes y el máximo puntaje. Usamos suspend para corutinas (semana 7).
Autor: Delgado Chipana Piero Adrián
Fecha creación: 2025-10-17
Fecha última modificación: 2025-10-19
*/

package com.example.parcial

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreDao {
    @Insert
    suspend fun insert(score: ScoreEntity)

    @Query("SELECT * FROM scores ORDER BY score DESC")
    suspend fun getAllScores(): List<ScoreEntity>

    @Query("SELECT MAX(score) FROM scores")
    suspend fun getMaxScore(): Int?
}