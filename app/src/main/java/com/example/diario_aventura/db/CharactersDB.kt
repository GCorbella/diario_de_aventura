package com.example.diario_aventura.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.diario_aventura.db.dao.*
import com.example.diario_aventura.db.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Character::class, Race::class, Armor::class, Weapon::class, Background::class], version = 1)
abstract class CharactersDB: RoomDatabase() {
    abstract fun characterDao(): CharacterDAO
    abstract fun raceDao(): RaceDAO
    abstract fun armorDao(): ArmorDAO
    abstract fun weaponDao(): WeaponDAO
    abstract fun backgroundDao(): BackgroundDAO

    companion object {
        @Volatile
        private var INSTANCE: CharactersDB? = null

        fun getInstance(context: Context): CharactersDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharactersDB::class.java, "characters"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Insertar datos iniciales cuando se crea la base de datos por primera vez
                            INSTANCE?.let { database ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    insertInitialRaces(database.raceDao())
                                    insertInitialBackgrounds(database.backgroundDao())
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private suspend fun insertInitialRaces(raceDao: RaceDAO) {
            raceDao.insertRaces(InitialData.initialRaces)
        }
        private suspend fun insertInitialBackgrounds(backgroundDao: BackgroundDAO) {
            backgroundDao.insertBackgrounds(InitialData.initialBackgrounds)
        }
    }
}
