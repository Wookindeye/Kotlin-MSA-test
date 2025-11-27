package org.example.userservive

data class UserCreatedEvent<T>(
        val version: Int,
        val payload: T
)
