package com.example.diario_aventura.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Personaje(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val raza: String,
    val tamanyo: String
)
