package com.giocruz.planetapi.usecases

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import com.giocruz.planetapi.repositories.memory.PlanetRepositoryMemory
import com.giocruz.planetapi.usecases.dtos.AddPlanetInput

internal class AddPlanetTest() {

    private val planetRepository = PlanetRepositoryMemory()
    private val useCase = AddPlanet(planetRepository)

    @Test
    fun testExecute() {
        useCase.execute(AddPlanetInput("Marte", "Quente", "Água"))
        assertEquals(2, planetRepository.getPlanetsQuantity())
    }
}