package com.example.diario_aventura.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.diario_aventura.db.dao.ArmorDAO
import com.example.diario_aventura.db.dao.CharacterDAO
import com.example.diario_aventura.db.dao.RaceDAO
import com.example.diario_aventura.db.dao.WeaponDAO
import com.example.diario_aventura.db.entities.Armor
import com.example.diario_aventura.db.entities.Character
import com.example.diario_aventura.db.entities.Race
import com.example.diario_aventura.db.entities.Weapon

@Database(entities = [Character::class, Race::class, Armor::class, Weapon::class], version = 2)
abstract class CharactersDB: RoomDatabase() {
    abstract fun characterDao(): CharacterDAO
    abstract fun raceDao():RaceDAO
    abstract fun armorDao():ArmorDAO
    abstract fun weaponDao():WeaponDAO

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Crear la tabla Race
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `race` (" +
                            "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "`name` TEXT NOT NULL, " +
                            "`size` TEXT NOT NULL" +
                            ")"
                )

                // Crear la tabla Armor
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `armor` (" +
                            "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "`name` TEXT NOT NULL" +
                            ")"
                )

                // Crear la tabla Weapon
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `weapon` (" +
                            "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "`name` TEXT NOT NULL, " +
                            "`proficiency` TEXT NOT NULL, " +
                            "`attack_attribute` TEXT NOT NULL, " +
                            "`damage` TEXT NOT NULL, " +
                            "`damage_attribute` TEXT NOT NULL, " +
                            "`damage_type` TEXT NOT NULL, " +
                            "`notes` TEXT NOT NULL" +
                            ")"
                )

                // Añadir las nuevas columnas a la tabla Character
                database.execSQL(
                    "ALTER TABLE `character` ADD COLUMN " +
                            "`strength` INTEGER NOT NULL DEFAULT 0, " +
                            "`dexterity` INTEGER NOT NULL DEFAULT 0, " +
                            "`constitution` INTEGER NOT NULL DEFAULT 0, " +
                            "`intelligence` INTEGER NOT NULL DEFAULT 0, " +
                            "`wisdom` INTEGER NOT NULL DEFAULT 0, " +
                            "`charisma` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_fortitude` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_reflexes` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_will` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_agility` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_crafting_1` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_crafting_2` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_athletics` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_spot` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_search` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_concentration` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_manual_dexterity` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_empathy` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_listen` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_perform_1` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_perform_2` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_persuasion` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_mount` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_pilot` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_heal` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_stealth` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_survival` INTEGER NOT NULL DEFAULT 14, " +
                            "`u_unarmed` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_daggers` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_maces` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_axes` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_swords` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_rapiers` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_whips` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_spears` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_2h_maces` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_2h_axes` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_2h_swords` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_double_weapons` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_throwing_weapons` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_bows` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_crossbows` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_short_firearms` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_long_firearms` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_projectile_spells` INTEGER NOT NULL DEFAULT 0, " +
                            "`u_ray_spells` INTEGER NOT NULL DEFAULT 0" +
                            ")"
                )
            }
        }
    }
}