package com.example.diario_aventura.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diario_aventura.enums.Skills

@Entity
data class Background(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "background_id")
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String = "",
    //Skills
    @ColumnInfo(name = "u_agility")
    var uAgility: Int = 0,
    @ColumnInfo(name = "u_crafting_1")
    var uCrafting1: Int = 0,
    @ColumnInfo(name = "u_crafting_2")
    var uCrafting2: Int = 0,
    @ColumnInfo(name = "u_athletics")
    var uAthletics: Int = 0,
    @ColumnInfo(name = "u_spot")
    var uSpot: Int = 0,
    @ColumnInfo(name = "u_search")
    var uSearch: Int = 0,
    @ColumnInfo(name = "u_concentration")
    var uConcentration: Int = 0,
    @ColumnInfo(name = "u_manual_dexterity")
    var uManualDexterity: Int = 0,
    @ColumnInfo(name = "u_empathy")
    var uEmpathy: Int = 0,
    @ColumnInfo(name = "u_listen")
    var uListen: Int = 0,
    @ColumnInfo(name = "u_perform_1")
    var uPerform1: Int = 0,
    @ColumnInfo(name = "u_perform_2")
    var uPerform2: Int = 0,
    @ColumnInfo(name = "u_persuasion")
    var uPersuasion: Int = 0,
    @ColumnInfo(name = "u_mount")
    var uMount: Int = 0,
    @ColumnInfo(name = "u_pilot")
    var uPilot: Int = 0,
    @ColumnInfo(name = "u_heal")
    var uHeal: Int = 0,
    @ColumnInfo(name = "u_stealth")
    var uStealth: Int = 0,
    @ColumnInfo(name = "u_survival")
    var uSurvival: Int = 0,
    @ColumnInfo(name = "melee_proficiency")
    val meleeProficiency: Boolean = false,
    @ColumnInfo(name = "ranged_proficiency")
    val rangedProficiency: Boolean = false
)
