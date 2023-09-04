package com.example.diario_aventura.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diario_aventura.enums.Proficiencies
import com.example.diario_aventura.enums.Skills

@Entity
data class Race (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "size")
    val size: String = "",
//Attributes
    @ColumnInfo(name = "strength")
    val strength: Int = 0,
    @ColumnInfo(name = "dexterity")
    val dexterity: Int = 0,
    @ColumnInfo(name = "constitution")
    val constitution: Int = 0,
    @ColumnInfo(name = "intelligence")
    val intelligence: Int = 0,
    @ColumnInfo(name = "wisdom")
    val wisdom: Int = 0,
    @ColumnInfo(name = "charisma")
    val charisma: Int = 0,
//Skills & Proficiencies
    @ColumnInfo(name = "skill_boosted")
    val bSkill: Skills = Skills.NONE,
    @ColumnInfo(name = "proficiency_boosted")
    val bProficiency: Proficiencies = Proficiencies.NONE
)
