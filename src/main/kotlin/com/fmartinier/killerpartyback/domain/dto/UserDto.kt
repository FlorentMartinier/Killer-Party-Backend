package com.fmartinier.killerpartyback.domain.dto

import com.fmartinier.killerpartyback.domain.enums.UserState

data class UserDto(
    val sessionId: String,
    val name: String,
    val phoneNumber: String,
    val associatedIpAddress: String,
    val state: UserState,
)