package com.giocruz.planetapi.controllers

import com.giocruz.planetapi.controllers.dtos.FindPlanetOutput
import com.giocruz.planetapi.usecases.FindPlanet
import com.giocruz.planetapi.usecases.ListAllPlanets
import com.giocruz.planetapi.usecases.dtos.FindPlanetInput
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class FindPlanet(
    @Autowired private val findPlanetUseCase: FindPlanet, @Autowired private val listAllPlanets: ListAllPlanets
) {
    @GetMapping("/planets")
    fun execute(
        @RequestParam(name = "name", required = false) planetName: String?,
        @RequestParam(name = "id", required = false) planetId: String?
    ): ResponseEntity<Any> {

        val getAllPlanets = planetName == null && planetId == null
        if (getAllPlanets) {
            val (planets) = listAllPlanets.execute()
            return ResponseEntity.status(HttpStatus.OK).body(object {
                val planets = planets
            })
        }
        val input = FindPlanetInput(planetId, planetName)
        val planet = findPlanetUseCase.execute(input) ?: return ResponseEntity.status(HttpStatus.OK).body(null)
        return ResponseEntity.status(HttpStatus.OK).body(object {
            val planets = listOf(object {
                val id = planet.id
                val name = planet.name
                val weather = planet.weather
                val terrain = planet.terrain
            })
        })
    }
}