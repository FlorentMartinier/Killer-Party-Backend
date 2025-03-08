package com.fmartinier.killerpartyback.repository

import com.fmartinier.killerpartyback.domain.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : JpaRepository<UserEntity, String> {

    fun findAllBySessionId(sessionId: String): List<UserEntity>

    fun findAllBySessionIdAndAssociatedIpAddress(sessionId: String, ipAdress: String): List<UserEntity>
}