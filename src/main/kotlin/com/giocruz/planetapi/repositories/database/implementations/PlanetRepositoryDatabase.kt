package com.giocruz.planetapi.repositories.database.implementations

import com.giocruz.planetapi.entities.Planet
import com.giocruz.planetapi.repositories.database.models.Planet as PlanetModel
import com.giocruz.planetapi.repositories.database.interfaces.PlanetRepositoryDatabaseInterface
import com.giocruz.planetapi.repositories.exceptions.PlanetExists
import com.giocruz.planetapi.repositories.interfaces.PlanetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class PlanetRepositoryDatabase(
        @Autowired
        private val jpaPlanetRepository: PlanetRepositoryDatabaseInterface
) : PlanetRepository {
    override fun findById(id: UUID): Planet? {
        val found = jpaPlanetRepository.findById(id)
        if (found.isPresent) return found.get().toEntity()
        return null
    }

    override fun findByName(name: String): Planet? {
        val found = jpaPlanetRepository.findByName(name.lowercase())
        if (found.isPresent) return found.get().toEntity()
        return null
    }

    override fun remove(id: UUID) {
        jpaPlanetRepository.deleteById(id)
    }

    override fun add(name: String, weather: String, terrain: String, movieAppearances: Int) {
        val found = findByName(name)
        if (found !== null) throw PlanetExists("Planet $name exists!")
        val entity = PlanetModel(UUID.randomUUID(), name, weather, terrain, movieAppearances)
        jpaPlanetRepository.save(entity)
    }

    override fun listAll(): MutableList<Planet> {
        val list = jpaPlanetRepository.findAll().toMutableList()
        return list.map { planet: PlanetModel -> planet.toEntity() }.toMutableList()
    }
}