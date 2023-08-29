package com.example.diario_aventura.db.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Weapons (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "proficiency")
    val proficiency: String = "",
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