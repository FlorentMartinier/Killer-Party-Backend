package com.fmartinier.killerpartyback.service

import com.fmartinier.killerpartyback.domain.UserEntity
import com.fmartinier.killerpartyback.domain.dto.UserDto
import com.fmartinier.killerpartyback.domain.enums.UserState
import com.fmartinier.killerpartyback.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val sessionService: SessionService
) {

    fun create(user: UserDto): UserEntity {
        sessionService.assertSessionExist(user.sessionId)

        return userRepository.save(
            UserEntity(
                sessionId = user.sessionId,
                name = user.name,
                phoneNumber = user.phoneNumber,
                associatedIpAddress = user.associatedIpAddress,
                state = UserState.WAITING,
            )
        )
    }

    fun findAllFromSessionId(sessionId: String): List<UserEntity> {
        return userRepository.findAllBySessionId(sessionId)
    }

    fun existFromWaitingSession(sessionId: String, userIp: String): Boolean {
        return userRepository.findAllBySessionIdAndAssociatedIpAddress(sessionId, userIp)
            .any { it.state == UserState.WAITING }
    }
}