package com.giocruz.planetapi.controllers

import com.giocruz.planetapi.repositories.exceptions.PlanetNotFound
import com.giocruz.planetapi.usecases.RemovePlanet
import com.giocruz.planetapi.usecases.dtos.RemovePlanetInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class RemovePlanet(
    @Autowired private val removePlanet: RemovePlanet
) {

    @DeleteMapping("/planets/{planetId}")
    fun execute(@PathVariable planetId: String): ResponseEntity<String> {
        val uuid = UUID.fromString(planetId)
        val input = RemovePlanetInput(uuid)
        return try {
            removePlanet.execute(input)
            ResponseEntity.status(HttpStatus.OK).body("Planet $planetId removed")
        } catch (e: PlanetNotFound) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Planet $planetId not found")
        }
    }
}