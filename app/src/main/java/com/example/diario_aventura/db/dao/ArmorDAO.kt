package com.example.diario_aventura.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.diario_aventura.db.entities.Armor

@Dao
interface ArmorDAO {

    @Insert
    fun insertArmor(armor: Armor)

    @Delete
    fun deleteArmor(armor: Armor)

    @Query("SELECT * FROM armor ORDER BY name ASC")
    fun getArmorsOrderedByName(): List<Armor>
}