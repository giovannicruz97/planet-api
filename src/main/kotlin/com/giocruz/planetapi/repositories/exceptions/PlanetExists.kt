package com.giocruz.planetapi.repositories.exceptions

import java.lang.Exception

class PlanetExists(message: String) : Exception(message) {
}