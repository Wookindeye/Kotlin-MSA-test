package org.example.userservive

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

interface UserRepository: MongoRepository<User, String> {
}