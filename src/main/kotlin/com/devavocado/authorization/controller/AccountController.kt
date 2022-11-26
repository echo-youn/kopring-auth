package com.devavocado.authorization.controller

import com.devavocado.authorization.Account
import com.devavocado.authorization.AccountRepository
import jakarta.annotation.security.RolesAllowed
import org.springframework.context.annotation.Role
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.AuthenticatedPrincipal
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class AccountController(
    private val accountRepository: AccountRepository
) {
    @GetMapping("/user")
    fun getUser(): ResponseEntity<Account?> {
        return ResponseEntity.ok(null)
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/user/admin")
    fun roleAdmin(): ResponseEntity<List<Account>> {
        return ResponseEntity.ok(listOf())
    }

    @PreAuthorize("hasAuthority('ROLE_GUEST')")
    @GetMapping("/user/guest")
    fun roleGuest(
    ): ResponseEntity<List<Account>> {
        return ResponseEntity.ok(listOf())
    }

    @PreAuthorize("hasAuthority('ROLE_USER1')")
    @GetMapping("/user/user1")
    fun roleUser1(
    ): ResponseEntity<List<Account>> {
        return ResponseEntity.ok(listOf())
    }

    @PreAuthorize("hasAuthority('ROLE_USER2')")
    @GetMapping("/user/user2")
    fun roleUser2(): ResponseEntity<List<Account>> {
        return ResponseEntity.ok(listOf())
    }
}