package com.example.diario_aventura.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Armor (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "armor_id")
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "type")
    val type: String = "",
    @ColumnInfo(name = "cut_reduction")
    val rCut: Int = 0,
    @ColumnInfo(name = "piercing_reduction")
    val rPiercing: Int = 0,
    @ColumnInfo(name = "blunt_reduction")
    val rBlunt: Int = 0,
    @ColumnInfo(name = "max_dexterity")
    val maxDex: Int = 8,
    @ColumnInfo(name = "skill_penalty")
    val skillPenalty: Int = 0,
    @ColumnInfo(name = "spell_failure")
    val spellFailure: Int = 0,
    @ColumnInfo(name = "speed_penalty")
    val speedPenalty: Int = 0
)