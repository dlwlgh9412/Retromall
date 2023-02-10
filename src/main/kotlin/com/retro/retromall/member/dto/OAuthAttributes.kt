package com.retro.retromall.member.dto

class OAuthAttributes(
    val tokenType: String,
    val accessToken: String,
    val accessTokenExpiresIn: Int,
    val refreshToken: String,
    val refreshTokenExpiresIn: Int,
    val scope: String
) {
}