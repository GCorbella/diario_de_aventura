package com.example.diario_aventura.db.dao

import androidx.room.*
import com.example.diario_aventura.db.entities.Character


@Dao
interface CharacterDAO {

    @Insert
    fun insertCharacter(character: Character): Long

    @Delete
    fun deleteCharacter(character: Character)

    @Update
    fun updateCharacter(character: Character)

    @Query("SELECT * FROM character ORDER BY name ASC")
    fun getCharactersOrderedByName(): List<Character>

    @Query("SELECT * FROM character ORDER BY race ASC")
    fun getCharactersOrderedByRace(): List<Character>

    @Query("SELECT * FROM character WHERE id = :id")
    fun getCharacterById(id: Int): Character

    fun createNewCharacter(name: String, race: Int, background: Int): Long {
        val newCharacter = Character(name = name, race = race, background = background)
        return insertCharacter(newCharacter)
    }
}