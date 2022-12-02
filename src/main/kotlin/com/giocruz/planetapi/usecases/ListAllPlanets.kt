package com.giocruz.planetapi.usecases

import com.giocruz.planetapi.entities.Planet
import com.giocruz.planetapi.repositories.interfaces.PlanetRepository
import com.giocruz.planetapi.usecases.dtos.ListAllPlanetsOutput
import com.giocruz.planetapi.usecases.dtos.Planet as PlanetDTO

class ListAllPlanets(private val planetRepository: PlanetRepository) {
    fun execute(): ListAllPlanetsOutput {
        val planets = planetRepository.listAll()
        val planetsList = planets.map { planet: Planet ->
            PlanetDTO(
                planet.id.toString(),
                planet.name,
                planet.weather,
                planet.terrain
            )
        }
        return ListAllPlanetsOutput(planetsList)
    }
}