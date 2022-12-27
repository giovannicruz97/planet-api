package com.giocruz.planetapi.repositories.http

import com.giocruz.planetapi.repositories.exceptions.InvalidPlanetName
import com.giocruz.planetapi.repositories.exceptions.SourceNotAvailable
import com.giocruz.planetapi.repositories.exceptions.UnknownError
import com.giocruz.planetapi.repositories.http.dtos.MovieResultDTO
import com.giocruz.planetapi.repositories.http.dtos.Planet
import com.giocruz.planetapi.repositories.interfaces.MovieRepository
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.UnknownHttpStatusCodeException

class MovieRepositoryHttp(private val httpClient: RestTemplate) : MovieRepository {
    override fun getMovieAppearances(planetName: String): Int {
        val planetNameInLowerCase = planetName.lowercase()
        try {
            val response =
                httpClient.getForEntity(
                    "https://swapi.dev/api/planets?name=${planetNameInLowerCase}",
                    MovieResultDTO::class.java
                )
            val found =
                response.body?.results?.filter { planet: Planet -> planet.name.lowercase() == planetNameInLowerCase }
            if (found.isNullOrEmpty()) {
                return 0
            }
            return found.first().films.size ?: 0
        } catch (exception: HttpClientErrorException.BadRequest) {
            throw InvalidPlanetName(exception.message ?: "$planetName invalid")
        } catch (exception: HttpServerErrorException) {
            throw SourceNotAvailable(exception.message ?: "SWAPI isn't available")
        } catch (exception: UnknownHttpStatusCodeException) {
            throw UnknownError(exception.message ?: "Something went wrong with SWAPI")
        }
    }
}