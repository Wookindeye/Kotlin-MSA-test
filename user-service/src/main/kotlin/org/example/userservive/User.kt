package org.example.userservive

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document
data class User(
        @Id val id: String = UUID.randomUUID().toString(),
        val name: String,
        val email: String
)
