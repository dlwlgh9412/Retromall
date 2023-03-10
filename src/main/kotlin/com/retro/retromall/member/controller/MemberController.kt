package com.retro.retromall.member.controller

import com.retro.retromall.member.dto.TokenAttributes
import com.retro.retromall.member.dto.LoginAttributes
import com.retro.retromall.member.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping("/login")
    fun login(@RequestBody loginAttributes: LoginAttributes): ResponseEntity<TokenAttributes> {
        val tokenInfo = memberService.findMemberByOauth(loginAttributes.oAuthType, loginAttributes.authorizationCode)
        return ResponseEntity.ok(tokenInfo)
    }
}