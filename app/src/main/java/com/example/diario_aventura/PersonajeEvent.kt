package com.example.diario_aventura

import com.example.diario_aventura.db.entities.Personaje

sealed interface PersonajeEvent {
    object SavePersonaje: PersonajeEvent
    data class SetNombre(val nombre: String): PersonajeEvent
    data class SetRaza(val raza: String): PersonajeEvent
    object ShowDialog: PersonajeEvent
    object HideDialog: PersonajeEvent
    data class SortPersonajes(val sortType: SortType): PersonajeEvent
    data class DeletePersonaje(val personaje: Personaje): PersonajeEvent
}