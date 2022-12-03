package com.giocruz.planetapi.controllers

import com.giocruz.planetapi.usecases.ListAllPlanets
import com.giocruz.planetapi.usecases.dtos.Planet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ListAllPlanets (@Autowired private val listAllPlanetsUseCase: ListAllPlanets){

    @GetMapping("/planets")
    fun execute(): List<Planet> {
        val response = listAllPlanetsUseCase.execute()
        return response.planets
    }
}