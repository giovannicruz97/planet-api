package com.giocruz.planetapi.usecases

import java.util.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import com.giocruz.planetapi.repositories.exceptions.PlanetNotFound
import com.giocruz.planetapi.repositories.memory.PlanetRepositoryMemory
import com.giocruz.planetapi.usecases.dtos.RemovePlanetInput

internal class RemovePlanetTest {

    private val useCase: RemovePlanet = RemovePlanet(PlanetRepositoryMemory())

    @Test
    fun testExecute() {
        val planetId = UUID.fromString("e16cdb04-e63c-4f01-8fc4-a3cd0aa7dd34")
        val input = RemovePlanetInput(planetId)
        useCase.execute(input)
    }

    @Test()
    fun testThrowPlanetNotFound() {
        val planetId = UUID.fromString("e16cdb04-e63c-4f01-8fc4-a3cd0aa7dd35")
        val input = RemovePlanetInput(planetId)
        assertThrows(PlanetNotFound::class.java) {
            useCase.execute(input)
        }
    }
}