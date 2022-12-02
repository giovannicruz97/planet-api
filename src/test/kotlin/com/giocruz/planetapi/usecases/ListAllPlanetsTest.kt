package com.giocruz.planetapi.usecases

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import com.giocruz.planetapi.repositories.memory.PlanetRepositoryMemory

internal class ListAllPlanetsTest {

    private val useCase: ListAllPlanets = ListAllPlanets(PlanetRepositoryMemory())

    @Test
    fun testExecute() {
        val (planets) = useCase.execute()
        val firstPlanet = planets.first()
        assertEquals("Terra", firstPlanet.name)
        assertEquals("Hot", firstPlanet.weather)
        assertEquals("Water", firstPlanet.terrain)
    }
}