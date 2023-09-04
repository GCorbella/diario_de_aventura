package com.example.diario_aventura.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.diario_aventura.db.entities.Background

@Dao
interface BackgroundDAO {

    @Insert
    fun insertBackground(background: Background)

    @Delete
    fun deleteBackground(background: Background)

    @Query("SELECT * FROM background ORDER BY name ASC")
    fun getBackgroundsOrderedByName(): List<Background>
}