package com.example.diario_aventura.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.diario_aventura.db.entities.Weapon

@Dao
interface WeaponDAO {

    @Insert
    fun insertWeapon(weapon: Weapon)

    @Delete
    fun deleteWeapon(weapon: Weapon)

    @Query("SELECT * FROM weapon ORDER BY name ASC")
    fun getWeaponsOrderedByName(): List<Weapon>
}