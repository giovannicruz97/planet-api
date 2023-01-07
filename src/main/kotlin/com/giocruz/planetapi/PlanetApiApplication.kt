package com.giocruz.planetapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class PlanetApiApplication

fun main(args: Array<String>) {
	runApplication<PlanetApiApplication>(*args)
}
