package com.giocruz.planetapi.usecases

import com.giocruz.planetapi.repositories.memory.PlanetRepositoryMemory
import com.giocruz.planetapi.usecases.dtos.FindPlanetInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class FindPlanetTest {

    private val planetRepository = PlanetRepositoryMemory()
    private val useCase = FindPlanet(planetRepository)

    @Test
    fun testExecuteUsingPlanetId() {
        val planetId = "e16cdb04-e63c-4f01-8fc4-a3cd0aa7dd34"
        val planet = useCase.execute(FindPlanetInput(planetId = planetId))
        if (planet != null) {
            assertEquals(planetId, planet.id)
            assertEquals("Terra", planet.name)
        }
    }

    @Test
    fun testExecuteUsingPlanetName() {
        val planetName = "Terra"
        val planet = useCase.execute(FindPlanetInput(planetName = planetName))
        if (planet != null) {
            assertEquals("e16cdb04-e63c-4f01-8fc4-a3cd0aa7dd34", planet.id)
            assertEquals(planetName, planet.name)
        }
    }

    @Test
    fun testExecuteReturningNull() {
        val planetName = "Pluto"
        val planet = useCase.execute(FindPlanetInput(planetName = planetName))
        assertNull(planet)
    }
}