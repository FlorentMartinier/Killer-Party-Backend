package com.fmartinier.killerpartyback.controller

import com.fmartinier.killerpartyback.domain.dto.SessionDto
import com.fmartinier.killerpartyback.service.SessionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/session"])
@CrossOrigin(origins = ["*"]) // FIXME : cibler les origines possibles une fois le front en production
class SessionController(
    val sessionService: SessionService
) {

    @PostMapping(value = ["create"])
    fun create(): SessionDto {
        return sessionService.create()
    }

    @PostMapping(value = ["close/{sessionId}"])
    fun close(
        @PathVariable sessionId: String,
    ) {
        sessionService.close(sessionId)
    }

    @GetMapping(value = ["/{sessionId}"])
    fun findBySession(
        @PathVariable sessionId: String,
    ): SessionDto {
        return sessionService.findById(sessionId)
    }
}