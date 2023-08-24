package com.example.diario_aventura.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.diario_aventura.db.dao.PersonajeDao
import com.example.diario_aventura.db.entities.Personaje

@Database(entities = [Personaje::class], version = 1)
abstract class PersonajesDB: RoomDatabase() {

    abstract val dao: PersonajeDao
}