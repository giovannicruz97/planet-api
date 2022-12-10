package com.giocruz.planetapi.config

import com.giocruz.planetapi.repositories.memory.MovieRepositoryMemory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.giocruz.planetapi.repositories.memory.PlanetRepositoryMemory
import com.giocruz.planetapi.usecases.AddPlanet
import com.giocruz.planetapi.usecases.FindPlanet
import com.giocruz.planetapi.usecases.ListAllPlanets
import com.giocruz.planetapi.usecases.RemovePlanet

@Configuration
class ApplicationConfig(
    private val planetRepositoryMemory: PlanetRepositoryMemory = PlanetRepositoryMemory(),
    private val movieRepositoryMemory: MovieRepositoryMemory = MovieRepositoryMemory()
) {

    @Bean
    fun addPlanetUseCase(): AddPlanet {
        return AddPlanet(planetRepositoryMemory, movieRepositoryMemory)
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