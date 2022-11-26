package com.devavocado.authorization

import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository: JpaRepository<Account, Long> {
    fun getAccountBySeqIn(seq: Set<Long>): List<Account>

    fun getAccountByUsername(username: String): Account?
}