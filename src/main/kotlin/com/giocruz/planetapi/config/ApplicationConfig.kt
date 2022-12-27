package com.giocruz.planetapi.config

import com.giocruz.planetapi.repositories.http.MovieRepositoryHttp
import com.giocruz.planetapi.repositories.interfaces.MovieRepository
import com.giocruz.planetapi.repositories.memory.MovieRepositoryMemory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.giocruz.planetapi.repositories.memory.PlanetRepositoryMemory
import com.giocruz.planetapi.usecases.AddPlanet
import com.giocruz.planetapi.usecases.FindPlanet
import com.giocruz.planetapi.usecases.ListAllPlanets
import com.giocruz.planetapi.usecases.RemovePlanet
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.client.RestTemplate

@Configuration
class ApplicationConfig(
    @Value("\${environment}")
    private val environment: String
) {

    private val planetRepositoryMemory = PlanetRepositoryMemory()

    @Bean
    fun addPlanetUseCase(): AddPlanet {
        val movieRepository: MovieRepository = when (environment) {
            "production" -> MovieRepositoryHttp(RestTemplate())
            else -> MovieRepositoryMemory()
        }
        return AddPlanet(planetRepositoryMemory, movieRepository)
    }

    @Bean
    fun findPlanetUseCase(): FindPlanet {
        return FindPlanet(planetRepositoryMemory)
    }

    @Bean
    fun listAllPlanetsUseCase(): ListAllPlanets {
        return ListAllPlanets(planetRepositoryMemory)
    }

    @Bean
    fun removePlanetUseCase(): RemovePlanet {
        return RemovePlanet(planetRepositoryMemory)
    }
}