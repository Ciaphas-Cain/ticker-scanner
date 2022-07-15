package com.portfolio.wizard.tickerscanner.messaging

import com.portfolio.wizard.tickerscanner.service.moex.MoexConsts
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {
    @Bean
    fun hello(): Queue {
        return Queue(MoexConsts.QUEUE_NAME)
    }
}