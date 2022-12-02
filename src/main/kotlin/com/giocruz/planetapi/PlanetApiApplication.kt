package com.giocruz.planetapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlanetApiApplication

fun main(args: Array<String>) {
	runApplication<PlanetApiApplication>(*args)
}
