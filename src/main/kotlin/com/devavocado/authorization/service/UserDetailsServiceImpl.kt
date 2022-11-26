package com.devavocado.authorization.service

import com.devavocado.authorization.Account
import com.devavocado.authorization.AccountRepository
import org.springframework.security.access.hierarchicalroles.RoleHierarchy
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val accountRepository: AccountRepository,
    private val passwordEncoder: PasswordEncoder,
    private val roleHierarchy: RoleHierarchy
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val accounts = accountRepository.getAccountByUsername(username ?: throw IllegalArgumentException(""))
            ?: throw Exception("test")

        return User.builder()
            .username(accounts.username)
            .password(passwordEncoder.encode(accounts.password))
            .authorities(getAuthorities(accounts)).build()
    }

    fun getAuthorities(user: Account): Set<GrantedAuthority> {
        val reduced = user.roles.split(",").fold(setOf<GrantedAuthority>()) { acc, role ->
            val role = SimpleGrantedAuthority(role.uppercase())
            val reachable = roleHierarchy.getReachableGrantedAuthorities(mutableSetOf(role)).toSet()

            acc.plus(reachable)
        }

        return reduced
    }
}
