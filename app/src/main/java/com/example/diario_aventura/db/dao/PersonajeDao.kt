package com.example.diario_aventura.db.dao

import androidx.room.*
import com.example.diario_aventura.db.entities.Personaje
import kotlinx.coroutines.flow.Flow


@Dao
interface PersonajeDao {

    @Insert
    fun insertPersonaje(personaje: Personaje)

    @Delete
    fun deletePersonaje(personaje: Personaje)

    @Query("SELECT * FROM personaje ORDER BY nombre ASC")
    fun getPersonajesOrderedByNombre(): List<Personaje>

    @Query("SELECT * FROM personaje ORDER BY raza ASC")
    fun getPersonajesOrderedByRaza(): List<Personaje>
}