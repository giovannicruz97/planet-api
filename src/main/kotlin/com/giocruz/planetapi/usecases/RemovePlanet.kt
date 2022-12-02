package com.giocruz.planetapi.usecases

import com.giocruz.planetapi.repositories.interfaces.PlanetRepository
import com.giocruz.planetapi.usecases.dtos.RemovePlanetInput

class RemovePlanet(private val planetRepository: PlanetRepository) {
    fun execute(input: RemovePlanetInput) {
        planetRepository.remove(input.planetId)
    }
}