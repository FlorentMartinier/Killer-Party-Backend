package com.fmartinier.killerpartyback.controller

import com.fmartinier.killerpartyback.domain.UserEntity
import com.fmartinier.killerpartyback.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/user"])
class UserController(
    val userService: UserService
) {

    @PostMapping(value = ["create"])
    fun create(
        @RequestParam("sessionId") sessionId: String,
        @RequestParam("phoneNumber") phoneNumber: String,
        @RequestParam("associatedIpAddress") associatedIpAddress: String,
    ): UserEntity {
        return userService.create(
            sessionId = sessionId,
            phoneNumber = phoneNumber,
            associatedIpAddress = associatedIpAddress,
        )
    }
}