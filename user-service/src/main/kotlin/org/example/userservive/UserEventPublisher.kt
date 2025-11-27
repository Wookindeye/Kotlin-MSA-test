package org.example.userservive

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class UserEventPublisher(
        private val rabbitTemplate: RabbitTemplate
) {
    fun userCreateEventPublishV1(event: UserCreatedEvent<PayloadV1>){
        rabbitTemplate.convertAndSend("userExchange", "user.created.v1",event)
    }
}

data class PayloadV1(
        val id: String,
        val name: String,
        val email: String
)

