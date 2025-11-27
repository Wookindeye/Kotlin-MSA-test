package org.example.userservive

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class UserResolver(
        private val userService: UserService
) {
    @MutationMapping
    fun createUser(@Argument name: String, @Argument email: String): User =
        userService.createUser(name, email)

    @QueryMapping
    fun getAllUsers(): List<User> =
            userService.getAllUsers()
}