package com.fmartinier.killerpartyback.controller

import com.fmartinier.killerpartyback.service.SessionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/session"])
class SessionController(
    val sessionService: SessionService
) {

    @PostMapping(value = ["create"])
    fun create(): String {
        return sessionService.create()
    }

    @PostMapping(value = ["close"])
    fun close(
        @RequestParam("sessionId") sessionId: String,
    ) {
        sessionService.close(sessionId)
    }
}