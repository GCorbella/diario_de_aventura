package com.example.diario_aventura

import android.app.Application
import androidx.room.Room
import com.example.diario_aventura.db.PersonajesDB

class DiarioDeAventuras : Application() {
    lateinit var db : PersonajesDB // Declaración con lateinit para diferir la inicialización

    companion object {
        var personajeSeleccionadoId: Int = -1 // Valor predeterminado
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            PersonajesDB::class.java, "personajes"
        ).build()
    }
}