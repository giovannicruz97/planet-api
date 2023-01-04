package com.giocruz.planetapi.usecases

import com.giocruz.planetapi.repositories.exceptions.PlanetExists
import com.giocruz.planetapi.repositories.memory.MovieRepositoryMemory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import com.giocruz.planetapi.repositories.memory.PlanetRepositoryMemory
import com.giocruz.planetapi.usecases.dtos.AddPlanetInput
import java.util.*

internal class AddPlanetTest() {

    private val planetRepository = PlanetRepositoryMemory()
    private val movieRepositoryMemory = MovieRepositoryMemory()
    private val useCase = AddPlanet(planetRepository, movieRepositoryMemory)

    @Test
    fun testExecute() {
        useCase.execute(AddPlanetInput("Marte", "Quente", "Água"))
        assertEquals(2, planetRepository.getPlanetsQuantity())
    }

    @Test
    fun testPlanetExists() {
        assertThrows(PlanetExists::class.java) {
            useCase.execute(AddPlanetInput("Terra", "Quente", "Água"))
        }
    }
}