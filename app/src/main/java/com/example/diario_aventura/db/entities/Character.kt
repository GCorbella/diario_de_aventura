package com.example.diario_aventura.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "character_id")
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "race")
    val race: Long = 0,
    @ColumnInfo(name = "background")
    val background: Long = 0,
//Stats
    @ColumnInfo(name = "current_health")
    val cHealth: Int = 0,
    @ColumnInfo(name = "current_mana")
    val cMana: Int = 0,
    @ColumnInfo(name = "current_luck")
    val cLuck: Int = 0,
//Attributes
    @ColumnInfo(name = "strength")
    var strength: Int = 10,
    @ColumnInfo(name = "dexterity")
    var dexterity: Int = 10,
    @ColumnInfo(name = "constitution")
    var constitution: Int = 10,
    @ColumnInfo(name = "intelligence")
    var intelligence: Int = 10,
    @ColumnInfo(name = "wisdom")
    var wisdom: Int = 10,
    @ColumnInfo(name = "charisma")
    var charisma: Int = 10,
//Saves
    @ColumnInfo(name = "u_fortitude")
    val uFortitude: Int = 14,
    @ColumnInfo(name = "u_reflexes")
    val uReflexes: Int = 14,
    @ColumnInfo(name = "u_will")
    val uWill: Int = 14,
//Skills
    @ColumnInfo(name = "u_agility")
    var uAgility: Int = 14,
    @ColumnInfo(name = "u_crafting_1")
    var uCrafting1: Int = 14,
    @ColumnInfo(name = "u_crafting_2")
    var uCrafting2: Int = 14,
    @ColumnInfo(name = "u_athletics")
    var uAthletics: Int = 14,
    @ColumnInfo(name = "u_spot")
    var uSpot: Int = 14,
    @ColumnInfo(name = "u_search")
    var uSearch: Int = 14,
    @ColumnInfo(name = "u_concentration")
    var uConcentration: Int = 14,
    @ColumnInfo(name = "u_manual_dexterity")
    var uManualDexterity: Int = 14,
    @ColumnInfo(name = "u_empathy")
    var uEmpathy: Int = 14,
    @ColumnInfo(name = "u_listen")
    var uListen: Int = 14,
    @ColumnInfo(name = "u_perform_1")
    var uPerform1: Int = 14,
    @ColumnInfo(name = "u_perform_2")
    var uPerform2: Int = 14,
    @ColumnInfo(name = "u_persuasion")
    var uPersuasion: Int = 14,
    @ColumnInfo(name = "u_mount")
    var uMount: Int = 14,
    @ColumnInfo(name = "u_pilot")
    var uPilot: Int = 14,
    @ColumnInfo(name = "u_heal")
    var uHeal: Int = 14,
    @ColumnInfo(name = "u_stealth")
    var uStealth: Int = 14,
    @ColumnInfo(name = "u_survival")
    var uSurvival: Int = 14,
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
