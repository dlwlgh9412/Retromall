package com.retro.aop

import com.retro.aop.annotation.MemberAuthentication
import com.retro.retromall.member.dto.MemberAttributes
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest

@Component
class MemberAttributeResolver(
    private val jwtTokenProvider: JwtTokenProvider
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == MemberAttributes::class.java && parameter.hasParameterAnnotation(MemberAuthentication::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val token = resolveToken(webRequest.nativeRequest as HttpServletRequest)
        val claims = jwtTokenProvider.parseClaims(token)
        return MemberAttributes(id = claims.get("id", Long::class.java))
    }

    private fun resolveToken(request: HttpServletRequest): String {
        val token = request.getHeader("Authorization")
        if (StringUtils.hasText(token) && token.startsWith("Bearer")) {
            return token.substring(7)
        }

        return "";
    }
}