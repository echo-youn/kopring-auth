package com.devavocado.authorization

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Account {
    @Id
    var seq: Long = 0
    var age: Int = 0
    var sex: String = ""
    var password: String = ""
    var username: String = ""
    var roles: String = ""
}
