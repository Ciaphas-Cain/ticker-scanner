package com.portfolio.wizard.tickerscanner.messaging

import com.portfolio.wizard.tickerscanner.service.moex.MoexConsts
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MoExSenderImpl(@Autowired var rabbitTemplate: RabbitTemplate): MoExSender {
    override fun send(ticker: String) {
        rabbitTemplate.convertAndSend(MoexConsts.QUEUE_NAME, ticker)
        println(" [x] Sent '$ticker'")
    }
}