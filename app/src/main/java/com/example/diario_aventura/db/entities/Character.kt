package com.example.diario_aventura.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "race")
    val race: Int = 0,
    @ColumnInfo(name = "background")
    val background: Int = 0,
//Stats
    @ColumnInfo(name = "current_health")
    val cHealth: Int = 0,
    @ColumnInfo(name = "current_mana")
    val cMana: Int = 0,
    @ColumnInfo(name = "current_luck")
    val cLuck: Int = 0,
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
//Saves
    @ColumnInfo(name = "u_fortitude")
    val uFortitude: Int = 14,
    @ColumnInfo(name = "u_reflexes")
    val uReflexes: Int = 14,
    @ColumnInfo(name = "u_will")
    val uWill: Int = 14,
//Skills
    @ColumnInfo(name = "u_agility")
    val uAgility: Int = 14,
    @ColumnInfo(name = "u_crafting_1")
    val uCrafting1: Int = 14,
    @ColumnInfo(name = "u_crafting_2")
    val uCrafting2: Int = 14,
    @ColumnInfo(name = "u_athletics")
    val uAthletics: Int = 14,
    @ColumnInfo(name = "u_spot")
    val uSpot: Int = 14,
    @ColumnInfo(name = "u_search")
    val uSearch: Int = 14,
    @ColumnInfo(name = "u_concentration")
    val uConcentration: Int = 14,
    @ColumnInfo(name = "u_manual_dexterity")
    val uManualDexterity: Int = 14,
    @ColumnInfo(name = "u_empathy")
    val uEmpathy: Int = 14,
    @ColumnInfo(name = "u_listen")
    val uListen: Int = 14,
    @ColumnInfo(name = "u_perform_1")
    val uPerform1: Int = 14,
    @ColumnInfo(name = "u_perform_2")
    val uPerform2: Int = 14,
    @ColumnInfo(name = "u_persuasion")
    val uPersuasion: Int = 14,
    @ColumnInfo(name = "u_mount")
    val uMount: Int = 14,
    @ColumnInfo(name = "u_pilot")
    val uPilot: Int = 14,
    @ColumnInfo(name = "u_heal")
    val uHeal: Int = 14,
    @ColumnInfo(name = "u_stealth")
    val uStealth: Int = 14,
    @ColumnInfo(name = "u_survival")
    val uSurvival: Int = 14,
//Weapon proficiencies
    @ColumnInfo(name = "u_unarmed")
    val uUnarmed: Int = 0,
    @ColumnInfo(name = "u_daggers")
    val uDaggers: Int = 0,
    @ColumnInfo(name = "u_maces")
    val uMaces: Int = 0,
    @ColumnInfo(name = "u_axes")
    val uAxes: Int = 0,
    @ColumnInfo(name = "u_swords")
    val uSwords: Int = 0,
    @ColumnInfo(name = "u_rapiers")
    val uRapiers: Int = 0,
    @ColumnInfo(name = "u_whips")
    val uWhips: Int = 0,
    @ColumnInfo(name = "u_spears")
    val uSpears: Int = 0,
    @ColumnInfo(name = "u_2h_maces")
    val u2hMaces: Int = 0,
    @ColumnInfo(name = "u_2h_axes")
    val u2hAxes: Int = 0,
    @ColumnInfo(name = "u_2h_swords")
    val u2hSwords: Int = 0,
    @ColumnInfo(name = "u_double_weapons")
    val uDoubleWeapons: Int = 0,
    @ColumnInfo(name = "u_throwing_weapons")
    val uThrowingWeapons: Int = 0,
    @ColumnInfo(name = "u_bows")
    val uBows: Int = 0,
    @ColumnInfo(name = "u_crossbows")
    val uCrossbows: Int = 0,
    @ColumnInfo(name = "u_short_firearms")
    val uShortFirearms: Int = 0,
    @ColumnInfo(name = "u_long_firearms")
    val uLongFirearms: Int = 0,
    @ColumnInfo(name = "u_projectile_spells")
    val uProjectileSpells: Int = 0,
    @ColumnInfo(name = "u_ray_spells")
    val uRaySpells: Int = 0,
    )
