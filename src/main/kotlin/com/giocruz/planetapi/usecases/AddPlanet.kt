package com.giocruz.planetapi.usecases

import com.giocruz.planetapi.repositories.interfaces.MovieRepository
import com.giocruz.planetapi.repositories.interfaces.PlanetRepository
import com.giocruz.planetapi.usecases.dtos.AddPlanetInput

class AddPlanet(private val planetRepository: PlanetRepository, private val memoryRepository: MovieRepository) {
    fun execute(input: AddPlanetInput) {
        val (name, weather, terrain) = input
        val movieAppearances = memoryRepository.getMovieAppearances(name)
        planetRepository.add(name, weather, terrain, movieAppearances)
    }
}