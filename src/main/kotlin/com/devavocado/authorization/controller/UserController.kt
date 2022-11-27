package com.devavocado.authorization.controller

import com.devavocado.authorization.Account
import com.devavocado.authorization.service.UserDetailsServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class UserController(
    private val userDetailsServiceImpl: UserDetailsServiceImpl
) {
    @GetMapping("/user/signup")
    fun signUpPage(): String {
        return "signup"
    }

    @ResponseBody
    @PostMapping(
        value = ["/user/signup"],
        consumes = ["application/x-www-form-urlencoded;charset=UTF-8"]
    )
    fun signUp(
        user: Account
    ): ResponseEntity<Account> {
        return userDetailsServiceImpl.registUser(user).let {
            ResponseEntity.ok(it)
        }
    }
}
