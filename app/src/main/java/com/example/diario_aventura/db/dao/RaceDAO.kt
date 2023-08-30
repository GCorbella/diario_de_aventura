package com.example.diario_aventura.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.diario_aventura.db.entities.Race

@Dao
interface RaceDAO {

    @Insert
    fun insertRace(race: Race)

    @Delete
    fun deleteRace(race: Race)

    @Query("SELECT * FROM race ORDER BY name ASC")
    fun getRacesOrderedByName(): List<Race>
}