package com.fmartinier.killerpartyback

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class KillerpartybackApplication

fun main(args: Array<String>) {
    runApplication<KillerpartybackApplication>(*args)
}
