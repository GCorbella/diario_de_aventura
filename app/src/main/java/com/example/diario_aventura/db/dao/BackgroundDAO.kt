package com.example.diario_aventura.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.diario_aventura.db.entities.Background
import com.example.diario_aventura.db.entities.Race

@Dao
interface BackgroundDAO {

    @Insert
    fun insertBackground(background: Background)

    @Insert
    fun insertBackgrounds(races: List<Background>)

    @Delete
    fun deleteBackground(background: Background)

    @Query("SELECT * FROM background ORDER BY name ASC")
    fun getBackgroundsOrderedByName(): List<Background>

    @Query("SELECT * FROM background WHERE background_id = :id")
    fun getBackgroundById(id: Long): Background
}