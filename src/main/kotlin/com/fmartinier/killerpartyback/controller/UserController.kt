package com.fmartinier.killerpartyback.controller

import com.fmartinier.killerpartyback.domain.UserEntity
import com.fmartinier.killerpartyback.domain.dto.UserDto
import com.fmartinier.killerpartyback.service.UserService
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/user"])
@CrossOrigin(origins = ["*"]) // FIXME : cibler les origines possibles une fois le front en production
class UserController(
    val userService: UserService,
    val messagingTemplate: SimpMessagingTemplate
) {

    @PostMapping(value = ["create"])
    fun create(
        @RequestBody user: UserDto,
    ): UserEntity {
        println("Creating user with name ${user.name}, for session ${user.sessionId}")
        val createdUser = userService.create(user)
        messagingTemplate.convertAndSend("/topic/session/${user.sessionId}", createdUser)
        return createdUser
    }

    @GetMapping(value = ["session/{sessionId}"])
    fun getFromSession(
        @PathVariable sessionId: String,
    ): List<UserEntity> {
        return userService.findAllFromSessionId(sessionId)
    }

    @GetMapping(value = ["{userIp}/session/{sessionId}"])
    fun existFromWaitingSession(
        @PathVariable userIp: String,
        @PathVariable sessionId: String,
    ): Boolean {
        return userService.existFromWaitingSession(sessionId, userIp)
    }
}