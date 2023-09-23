package com.example.diario_aventura.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diario_aventura.enums.Proficiencies
import com.example.diario_aventura.enums.Skills

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
    var cHealth: Int = 0,
    @ColumnInfo(name = "current_mana")
    var cMana: Int = 0,
    @ColumnInfo(name = "current_luck")
    var cLuck: Int = 0,
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
    var uFortitude: Int = 14,
    @ColumnInfo(name = "u_reflexes")
    var uReflexes: Int = 14,
    @ColumnInfo(name = "u_will")
    var uWill: Int = 14,
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
    var uUnarmed: Int = 0,
    @ColumnInfo(name = "u_daggers")
    var uDaggers: Int = 0,
    @ColumnInfo(name = "u_maces")
    var uMaces: Int = 0,
    @ColumnInfo(name = "u_axes")
    var uAxes: Int = 0,
    @ColumnInfo(name = "u_swords")
    var uSwords: Int = 0,
    @ColumnInfo(name = "u_rapiers")
    var uRapiers: Int = 0,
    @ColumnInfo(name = "u_whips")
    var uWhips: Int = 0,
    @ColumnInfo(name = "u_spears")
    var uSpears: Int = 0,
    @ColumnInfo(name = "u_2h_maces")
    var u2hMaces: Int = 0,
    @ColumnInfo(name = "u_2h_axes")
    var u2hAxes: Int = 0,
    @ColumnInfo(name = "u_2h_swords")
    var u2hSwords: Int = 0,
    @ColumnInfo(name = "u_double_weapons")
    var uDoubleWeapons: Int = 0,
    @ColumnInfo(name = "u_throwing_weapons")
    var uThrowingWeapons: Int = 0,
    @ColumnInfo(name = "u_bows")
    var uBows: Int = 0,
    @ColumnInfo(name = "u_crossbows")
    var uCrossbows: Int = 0,
    @ColumnInfo(name = "u_short_firearms")
    var uShortFirearms: Int = 0,
    @ColumnInfo(name = "u_long_firearms")
    var uLongFirearms: Int = 0,
    @ColumnInfo(name = "u_projectile_spells")
    var uProjectileSpells: Int = 0,
    @ColumnInfo(name = "u_ray_spells")
    var uRaySpells: Int = 0,
    //Human and Half-Human Bonuses
    @ColumnInfo(name = "human_bonus_strength")
    var hBStrength: Int = 0,
    @ColumnInfo(name = "human_bonus_dexterity")
    var hBDexterity: Int = 0,
    @ColumnInfo(name = "human_bonus_constitution")
    var hBConstitution: Int = 0,
    @ColumnInfo(name = "human_bonus_intelligence")
    var hBIntelligence: Int = 0,
    @ColumnInfo(name = "human_bonus_wisdom")
    var hBWisdom: Int = 0,
    @ColumnInfo(name = "human_bonus_charisma")
    var hBCharisma: Int = 0,
    @ColumnInfo(name = "human_bonus_skill")
    var hBSkill: Skills = Skills.NONE,
    @ColumnInfo(name = "human_bonus_proficiency")
    var hBProficiency: Proficiencies = Proficiencies.NONE,
    @ColumnInfo(name = "half-elf_bonus_skill")
    var hEBSkill: Skills = Skills.NONE,
    @ColumnInfo(name = "half-orc_bonus_proficiency")
    var hOBProficiency: Proficiencies = Proficiencies.NONE,
    //Feats
    @ColumnInfo(name = "sixth_sense")
    var sixthSense: Boolean = false,
    @ColumnInfo(name = "enh_initiative")
    var enhInitiative: Boolean = false,
    )
