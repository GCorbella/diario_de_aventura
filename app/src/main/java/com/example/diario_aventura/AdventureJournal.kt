package com.example.diario_aventura

import android.app.Application
import androidx.room.Room
import com.example.diario_aventura.db.CharactersDB
import com.example.diario_aventura.db.CharactersDB.Companion.MIGRATION_1_2

class AdventureJournal : Application() {
    lateinit var db : CharactersDB // Declaración con lateinit para diferir la inicialización

    companion object {
        var selectedCharacterId: Int = -1 // Valor predeterminado
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            CharactersDB::class.java, "characters",
        ).addMigrations(MIGRATION_1_2).build()
    }
}