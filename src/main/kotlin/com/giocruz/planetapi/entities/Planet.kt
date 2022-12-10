package com.giocruz.planetapi.entities

import java.util.UUID

data class Planet(val id: UUID, val name: String, val weather: String, val terrain: String, val movieAppearances: Int)