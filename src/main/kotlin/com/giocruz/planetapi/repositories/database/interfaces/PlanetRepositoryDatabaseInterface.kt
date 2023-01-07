package com.giocruz.planetapi.repositories.database.interfaces

import com.giocruz.planetapi.repositories.database.models.Planet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface PlanetRepositoryDatabaseInterface: JpaRepository<Planet, UUID> {
    fun findByName(name: String): Optional<Planet>
}