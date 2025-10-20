/*
Descripción: Define la base de datos Room con la entidad ScoreEntity. Proporciona acceso al DAO para operaciones de puntajes. Usado para la funcionalidad adicional de persistencia (semana 7).
Autor: Delgado Chipana Piero Adrián
Fecha creación: 2025-10-17
Fecha última modificación: 2025-10-19
*/

package com.example.parcial

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ScoreEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scoreDao(): ScoreDao
}