package com.fmartinier.killerpartyback.service

import com.fmartinier.killerpartyback.domain.SessionEntity
import com.fmartinier.killerpartyback.domain.dto.SessionDto
import com.fmartinier.killerpartyback.domain.dto.UserDto
import com.fmartinier.killerpartyback.repository.SessionRepository
import com.fmartinier.killerpartyback.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class SessionService(
    val sessionRepository: SessionRepository,
    val userRepository: UserRepository,
) {

    fun create(): SessionDto {
        val savedEntity = sessionRepository.save(
            SessionEntity(userCanJoin = true)
        )
        return SessionDto(
            id = savedEntity.id!!,
            userCanJoin = savedEntity.userCanJoin,
            users = emptyList(),
        )
    }

    fun close(sessionId: String) {
        assertSessionExist(sessionId)
        val session = sessionRepository.findById(sessionId).get()
        sessionRepository.save(session.copy(userCanJoin = false))
    }

    fun findById(sessionId: String): SessionDto {
        assertSessionExist(sessionId)
        val sessionEntity = sessionRepository.findById(sessionId).get()
        val users = userRepository.findAllBySessionId(sessionId).map {
            UserDto(
                sessionId = it.sessionId,
                name = it.name,
                phoneNumber = it.phoneNumber,
                associatedIpAddress = it.associatedIpAddress,
                state = it.state,
            )
        }
        return SessionDto(
            id = sessionEntity.id!!,
            userCanJoin = sessionEntity.userCanJoin,
            users = users,
        )
    }

    fun assertSessionExist(sessionId: String) {
        if (!sessionRepository.existsById(sessionId)) {
            throw Exception("Session $sessionId not found")
        }
    }
}