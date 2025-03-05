package com.fmartinier.killerpartyback.service

import com.fmartinier.killerpartyback.domain.SessionEntity
import com.fmartinier.killerpartyback.repository.SessionRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class SessionService(
    val sessionRepository: SessionRepository
) {

    fun create(): String {
        val savedEntity = sessionRepository.save(
            SessionEntity(userCanJoin = true)
        )
        return savedEntity.id!!
    }

    fun close(sessionId: String) {
        checkSessionExist(sessionId)
        val session = sessionRepository.findById(sessionId).get()
        sessionRepository.save(session.copy(userCanJoin = false))
    }

    fun checkSessionExist(sessionId: String) {
        if (!sessionRepository.existsById(sessionId)) {
            throw Exception("Session $sessionId not found")
        }
    }
}