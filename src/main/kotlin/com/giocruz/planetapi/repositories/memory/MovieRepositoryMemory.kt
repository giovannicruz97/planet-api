package com.giocruz.planetapi.repositories.memory

import com.giocruz.planetapi.repositories.interfaces.MovieRepository

class MovieRepositoryMemory : MovieRepository {

    private val appearances = mapOf("Terra" to 10, "Marte" to 2, "Tatooine" to 3)
    override fun getMovieAppearances(planetName: String): Int {
        return this.appearances[planetName] ?: 0
    }
}