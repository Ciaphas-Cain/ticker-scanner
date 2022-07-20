package com.portfolio.wizard.tickerscanner.messaging

import com.portfolio.wizard.tickerscanner.service.moex.MoexConsts
import mu.KotlinLogging
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MoExSenderImpl(@Autowired var rabbitTemplate: RabbitTemplate): MoExSender {
    private val logger = KotlinLogging.logger {}
    override fun send(ticker: String) {
        rabbitTemplate.convertAndSend(MoexConsts.QUEUE_NAME, ticker)
        logger.info{" Sent '$ticker' to '$MoexConsts.QUEUE_NAME'"}
    }
}