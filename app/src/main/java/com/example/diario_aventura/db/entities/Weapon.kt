package com.example.diario_aventura.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diario_aventura.enums.Proficiencies

@Entity
data class Weapon (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "weapon_id")
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "proficiency")
    val proficiency: Proficiencies = Proficiencies.NONE,
    @ColumnInfo(name = "attack_attribute")
    val attackAttribute: String = "",
    @ColumnInfo(name = "damage")
    val damage: String = "",
    @ColumnInfo(name = "damage_attribute")
    val damageAttribute: String = "",
    @ColumnInfo(name = "damage_type")
    val damageType: String = "",
    @ColumnInfo(name = "notes")
    val notes: String = ""
)