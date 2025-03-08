package com.fmartinier.killerpartyback.domain.dto

class SessionDto (
    val id: String,
    val userCanJoin: Boolean,
    val users: List<UserDto>,
)