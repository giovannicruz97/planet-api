package com.giocruz.planetapi.usecases.dtos

data class Planet(val id: String, val name: String, val weather: String, val terrain: String)

data class ListAllPlanetsOutput(val planets: List<Planet>)