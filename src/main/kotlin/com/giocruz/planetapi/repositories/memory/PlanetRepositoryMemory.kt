package com.giocruz.planetapi.repositories.memory

import java.util.*
import com.giocruz.planetapi.entities.Planet
import com.giocruz.planetapi.repositories.exceptions.PlanetNotFound
import com.giocruz.planetapi.repositories.interfaces.PlanetRepository

class PlanetRepositoryMemory : PlanetRepository {

    private val planets =
        mutableListOf(
            Planet(
                UUID.fromString("e16cdb04-e63c-4f01-8fc4-a3cd0aa7dd34"),
                "Terra",
                "Hot",
                "Water",
                1
            )
        )

    fun getPlanetsQuantity(): Int {
        return planets.size
    }

    override fun listAll(): MutableList<Planet> {
        return planets
    }

    override fun remove(id: UUID) {
        val notFound = !planets.removeAll { planet: Planet -> planet.id == id }
        if (notFound) throw PlanetNotFound("planet.id $id not found")
    }

    override fun add(name: String, weather: String, terrain: String, movieAppearances: Int) {
        val planet = Planet(UUID.randomUUID(), name, weather, terrain, movieAppearances)
        planets.add(planet)
    }

    override fun findById(id: UUID): Planet? {
        return planets.find { planet: Planet -> planet.id == id }
    }

    override fun findByName(name: String): Planet? {
        return planets.find { planet: Planet -> planet.name.lowercase() == name.lowercase() }
    }
}