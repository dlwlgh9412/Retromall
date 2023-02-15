package com.retro.retromall.member.controller

import com.retro.retromall.member.dto.TokenAttributes
import com.retro.retromall.member.dto.LoginAttributes
import com.retro.retromall.member.service.MemberWriteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController(
    private val memberWriteService: MemberWriteService
) {
    @PostMapping("/login")
    fun login(@RequestBody loginAttributes: LoginAttributes): ResponseEntity<TokenAttributes> {
        val tokenInfo = memberWriteService.findMemberByOauth(loginAttributes.oAuthType, loginAttributes.authorizationCode)
        return ResponseEntity.ok(tokenInfo)
    }
}