package com.fmartinier.killerpartyback.domain

import com.fmartinier.killerpartyback.domain.enums.UserState
import jakarta.persistence.*

@Entity
@Table(name = "`user`") // user is a postgres keyword. Must use ` character.
data class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,
    val sessionId: String = "",
    val name: String = "",
    val phoneNumber: String = "",
    val associatedIpAddress: String = "",
    val state: UserState = UserState.WAITING,
)