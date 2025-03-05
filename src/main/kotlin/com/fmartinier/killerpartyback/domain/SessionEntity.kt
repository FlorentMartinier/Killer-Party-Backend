package com.fmartinier.killerpartyback.domain

import jakarta.persistence.*

@Entity
@Table(name = "session")
data class SessionEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,
    val userCanJoin: Boolean = true,
)