package com.giocruz.planetapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.giocruz.planetapi.repositories.memory.PlanetRepositoryMemory
import com.giocruz.planetapi.usecases.AddPlanet
import com.giocruz.planetapi.usecases.FindPlanet
import com.giocruz.planetapi.usecases.ListAllPlanets
import com.giocruz.planetapi.usecases.RemovePlanet

@Configuration
class ApplicationConfig(private val planetRepositoryMemory: PlanetRepositoryMemory = PlanetRepositoryMemory()) {

    @Bean
    fun addPlanetUseCase(): AddPlanet {
        return AddPlanet(planetRepositoryMemory)
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