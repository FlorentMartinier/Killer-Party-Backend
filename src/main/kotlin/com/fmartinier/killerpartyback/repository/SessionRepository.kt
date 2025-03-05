package com.fmartinier.killerpartyback.repository

import com.fmartinier.killerpartyback.domain.SessionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface SessionRepository : JpaRepository<SessionEntity, String>