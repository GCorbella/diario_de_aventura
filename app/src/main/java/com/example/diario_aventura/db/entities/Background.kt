package com.example.diario_aventura.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diario_aventura.enums.Skills

@Entity
data class Background(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "skill_1")
    val skill1: Skills = Skills.NONE,
    @ColumnInfo(name = "skill_2")
    val skill2: Skills = Skills.NONE,
    @ColumnInfo(name = "skill_3")
    val skill3: Skills = Skills.NONE,
    @ColumnInfo(name = "skill_4")
    val skill4: Skills = Skills.NONE,
    @ColumnInfo(name = "skill_5")
    val skill5: Skills = Skills.NONE,
    @ColumnInfo(name = "skill_6")
    val skill6: Skills = Skills.NONE,
    @ColumnInfo(name = "melee_proficiency")
    val meleeProficiency: Boolean = false,
    @ColumnInfo(name = "ranged_proficiency")
    val rangedProficiency: Boolean = false
)
