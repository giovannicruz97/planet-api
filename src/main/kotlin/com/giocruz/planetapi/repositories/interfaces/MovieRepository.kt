package com.giocruz.planetapi.repositories.interfaces

interface MovieRepository {
    fun getMovieAppearances(planetName: String): Int
}