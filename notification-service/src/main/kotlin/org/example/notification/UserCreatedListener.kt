package org.example.notification

import org.apache.commons.logging.LogFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.io.Serializable

@Component
class UserCreatedListener {

    private val log = LoggerFactory.getLogger(UserCreatedListener::class.java)

    @RabbitListener(queues = ["userCreatedQueue"])
    fun userCreatedReceiveV1(userCreatedEvent: UserCreatedEvent<PayloadV1>){
        val s: String = "이메일 발송 완료, Email : " + userCreatedEvent.payload.email
        log.info(s)
    }

}
data class UserCreatedEvent<T>(
        val version: Int,
        val payload: T
)
data class PayloadV1(
        val id: String,
        val name: String,
        val email: String
)