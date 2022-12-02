package com.giocruz.planetapi.usecases

import com.giocruz.planetapi.repositories.interfaces.PlanetRepository
import com.giocruz.planetapi.usecases.dtos.FindPlanetInput
import com.giocruz.planetapi.usecases.dtos.FindPlanetOutput
import java.util.*

class FindPlanet(private val planetRepository: PlanetRepository) {
    fun execute(input: FindPlanetInput): FindPlanetOutput? {
        val (planetId, planetName) = input
        if (planetName != null) {
            val planet = planetRepository.findByName(planetName) ?: return null
            return FindPlanetOutput(planet.id.toString(), planet.name, planet.weather, planet.terrain)
        }
        if (planetId != null) {
            val planet = planetRepository.findById(UUID.fromString(planetId)) ?: return null
            return FindPlanetOutput(planet.id.toString(), planet.name, planet.weather, planet.terrain)
        }
        return null
    }
}