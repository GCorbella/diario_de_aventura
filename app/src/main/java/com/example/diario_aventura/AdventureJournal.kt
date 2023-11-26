package com.example.diario_aventura

import android.app.Application
import com.example.diario_aventura.db.CharactersDB

class AdventureJournal : Application() {
    lateinit var db : CharactersDB // Declaración con lateinit para diferir la inicialización

    companion object {
        var selectedCharacterId: Long = -1L // Valor predeterminado
    }

    override fun onCreate() {
        super.onCreate()
        db = CharactersDB.getInstance(this)
    }
}