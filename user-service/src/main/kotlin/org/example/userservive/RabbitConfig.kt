package org.example.userservive

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {

    @Bean
    fun jackson2JsonMessageConverter(): Jackson2JsonMessageConverter =
            Jackson2JsonMessageConverter()

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        val template = RabbitTemplate(connectionFactory)
        template.messageConverter = jackson2JsonMessageConverter()
        return template
    }

    @Bean
    fun  userExchange(): TopicExchange = TopicExchange("userExchange")

    @Bean
    fun userCreatedQueue(): Queue = Queue("userCreatedQueue")

    @Bean
    fun bind() = BindingBuilder.bind(userCreatedQueue())
            .to(userExchange())
            .with("user.created.v1")

}