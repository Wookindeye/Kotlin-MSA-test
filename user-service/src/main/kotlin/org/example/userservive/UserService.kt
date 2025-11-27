package org.example.userservive

import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository,
        private val eventPublisher: UserEventPublisher
) {
    fun createUser(name: String, email: String): User {
        val user = userRepository.save(User(name = name, email = email))
        eventPublisher.userCreateEventPublishV1(
                UserCreatedEvent(1, PayloadV1(user.id,user.name, user.email)))
        return user
    }

    fun getAllUsers(): List<User> =
            userRepository.findAll()

}