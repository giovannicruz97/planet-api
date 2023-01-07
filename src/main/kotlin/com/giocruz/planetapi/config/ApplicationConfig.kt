package com.giocruz.planetapi.config

import com.giocruz.planetapi.repositories.database.implementations.PlanetRepositoryDatabase
import com.giocruz.planetapi.repositories.http.MovieRepositoryHttp
import com.giocruz.planetapi.repositories.interfaces.MovieRepository
import com.giocruz.planetapi.repositories.interfaces.PlanetRepository
import com.giocruz.planetapi.repositories.memory.MovieRepositoryMemory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.giocruz.planetapi.repositories.memory.PlanetRepositoryMemory
import com.giocruz.planetapi.usecases.AddPlanet
import com.giocruz.planetapi.usecases.FindPlanet
import com.giocruz.planetapi.usecases.ListAllPlanets
import com.giocruz.planetapi.usecases.RemovePlanet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.client.RestTemplate

@Configuration
class ApplicationConfig(
        @Value("\${environment}")
        private val environment: String,
        @Autowired
        private val planetRepositoryDatabase: PlanetRepositoryDatabase
) {

    private val planetRepositoryMemory = PlanetRepositoryMemory()

    private fun getPlanetRepository(environment: String): PlanetRepository {
        return when (environment) {
            "production" -> planetRepositoryDatabase
            else -> planetRepositoryMemory
        }
    }

    private fun getMovieRepository(environment: String): MovieRepository {
        return when (environment) {
            "production" -> MovieRepositoryHttp(RestTemplate())
            else -> MovieRepositoryMemory()
        }
    }

    @Bean
    fun addPlanetUseCase(): AddPlanet {
        val movieRepository = getMovieRepository(environment)
        val planetRepository = getPlanetRepository(environment)
        return AddPlanet(planetRepository, movieRepository)
    }

    @Bean
    fun findPlanetUseCase(): FindPlanet {
        val planetRepository = getPlanetRepository(environment)
        return FindPlanet(planetRepository)
    }

    @Bean
    fun listAllPlanetsUseCase(): ListAllPlanets {
        val planetRepository = getPlanetRepository(environment)
        return ListAllPlanets(planetRepository)
    }

    @Bean
    fun removePlanetUseCase(): RemovePlanet {
        val planetRepository = getPlanetRepository(environment)
        return RemovePlanet(planetRepository)
    }
}