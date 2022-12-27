package com.giocruz.planetapi.controllers.dtos

data class Planet(
    val id: String,
    val name: String,
    val weather: String,
    val terrain: String
)

data class FindPlanetOutput(
    val planet: List<Planet>
)