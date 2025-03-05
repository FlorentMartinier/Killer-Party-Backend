package com.fmartinier.killerpartyback.service

import com.fmartinier.killerpartyback.domain.UserEntity
import com.fmartinier.killerpartyback.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val sessionService: SessionService
) {

    fun create(sessionId: String, phoneNumber: String, associatedIpAddress: String): UserEntity {
        sessionService.checkSessionExist(sessionId)

        return userRepository.save(
            UserEntity(
                sessionId = sessionId,
                phoneNumber = phoneNumber,
                associatedIpAddress = associatedIpAddress,
            )
        )
    }
}