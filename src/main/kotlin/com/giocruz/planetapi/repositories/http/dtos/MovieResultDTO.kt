package com.giocruz.planetapi.repositories.http.dtos

data class Planet(
    val name: String,
    val films: List<String>
)

data class MovieResultDTO(
    val count: String,
    val next: String,
    val previous: String?,
    val results: List<Planet>
)
