package com.devavocado.authorization

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.AccessDecisionVoter
import org.springframework.security.access.expression.SecurityExpressionHandler
import org.springframework.security.access.hierarchicalroles.RoleHierarchy
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl
import org.springframework.security.access.vote.RoleHierarchyVoter
import org.springframework.security.authorization.AuthorityAuthorizationManager
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/h2-console/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .defaultSuccessUrl("/")
            .and()
            .logout().permitAll()
            .and()
            .formLogin().permitAll()
            .and()
            .headers()
            .frameOptions().sameOrigin()

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun roleHierarchy(): RoleHierarchy {
        val roleHierarchy = RoleHierarchyImpl()
        val hierarchy = "ROLE_ADMIN > ROLE_USER1 > ROLE_USER2 > ROLE_GUEST"
        roleHierarchy.setHierarchy(hierarchy)
        return roleHierarchy
    }
}