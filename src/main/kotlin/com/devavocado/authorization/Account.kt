package com.devavocado.authorization

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
class Account {
    @Id
    var seq: Long = 0
    val age: Int = 0
    val sex: String = ""
    val password: String = ""
    val username: String = ""
    val roles: String = ""
}
