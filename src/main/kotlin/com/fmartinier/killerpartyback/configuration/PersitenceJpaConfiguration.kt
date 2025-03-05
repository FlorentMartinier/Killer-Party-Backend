package com.fmartinier.killerpartyback.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.fmartinier.killerpartyback.repository"])
class PersitenceJpaConfiguration