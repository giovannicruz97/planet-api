package com.giocruz.planetapi.controllers

import com.giocruz.planetapi.usecases.AddPlanet
import com.giocruz.planetapi.usecases.dtos.AddPlanetInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

data class AddPlanetRequest(val name: String, val weather: String, val terrain: String)


@RestController
class AddPlanet(
    @Autowired private val addPlanet: AddPlanet
) {

    @PostMapping("/planets")
    fun handle(@RequestBody request: AddPlanetRequest): ResponseEntity<Boolean> {
        val input = AddPlanetInput(request.name, request.weather, request.terrain)
        addPlanet.execute(input)
        return ResponseEntity.status(HttpStatus.OK).body(true)
    }
}