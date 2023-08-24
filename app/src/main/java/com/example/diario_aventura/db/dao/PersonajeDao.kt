package com.example.diario_aventura.db.dao

import androidx.room.*
import com.example.diario_aventura.db.entities.Personaje
import kotlinx.coroutines.flow.Flow


@Dao
interface PersonajeDao {

    @Upsert
    fun upsertPersonaje(personaje: Personaje)

    @Delete
    fun deletePersonaje(personaje: Personaje)

    @Query("SELECT * FROM personajes ORDER BY nombre ASC")
    fun getPersonajesOrderedByNombre(): Flow<List<Personaje>>

    @Query("SELECT * FROM personajes ORDER BY raza ASC")
    fun getPersonajesOrderedByRaza(): Flow<List<Personaje>>
}