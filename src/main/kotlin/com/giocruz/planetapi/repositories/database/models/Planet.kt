package com.giocruz.planetapi.repositories.database.models

import com.giocruz.planetapi.entities.Planet
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.util.UUID

@Entity
class Planet(
        @Id
        @GeneratedValue
        private val id: UUID,
        @Column(nullable = false, unique = true, length = 255)
        private val name: String,
        @Column(nullable = false, length = 255)
        private val weather: String,
        @Column(nullable = false, length = 255)
        private val terrain: String,
        @Column(nullable = false, length = 10)
        private val movieAppearances: Int
) {
        fun toEntity(): Planet {
                return Planet(id, name, weather, terrain, movieAppearances)
        }
}