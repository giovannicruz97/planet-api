package com.giocruz.planetapi.usecases

import com.giocruz.planetapi.repositories.interfaces.PlanetRepository
import com.giocruz.planetapi.usecases.dtos.AddPlanetInput

class AddPlanet(private val planetRepository: PlanetRepository) {
    fun execute(input: AddPlanetInput) {
        val (name, weather, terrain) = input
        planetRepository.add(name, weather, terrain)
    }
}