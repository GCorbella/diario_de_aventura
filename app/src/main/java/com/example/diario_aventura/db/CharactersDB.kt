package com.example.diario_aventura.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.diario_aventura.db.dao.CharacterDAO
import com.example.diario_aventura.db.entities.Character

@Database(entities = [Character::class], version = 1)
abstract class CharactersDB: RoomDatabase() {
    abstract fun characterDao(): CharacterDAO
}