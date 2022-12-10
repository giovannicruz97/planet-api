package com.giocruz.planetapi.repositories.interfaces

import com.giocruz.planetapi.entities.Planet
import java.util.UUID

interface PlanetRepository {

    fun listAll(): MutableList<Planet>
    fun remove(id: UUID)
    fun add(name: String, weather: String, terrain: String, movieAppearances: Int = 0)
    fun findById(id: UUID): Planet?
    fun findByName(name: String): Planet?
}