package com.portfolio.wizard.tickerscanner.service.moex

import com.google.gson.Gson
import com.portfolio.wizard.tickerscanner.messaging.MoExSender
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class MoExServiceImpl(@Autowired val restTemplate: RestTemplate, @Autowired val sender: MoExSender) : MoExService {
    private val logger = KotlinLogging.logger {}

    val gson = Gson()

    override fun getMoExTickers() {
        logger.info { ">>> getMoExTickers called" }
        val response = restTemplate.getForObject(
            MoexConsts.ALL_SECURITIES,
            String::class.java
        )
        val responseJson = gson.fromJson(response, MoExSecuritiesResponse::class.java)
        send(responseJson)
    }

    fun send(moexResponse: MoExSecuritiesResponse) {
        moexResponse.securities.data.forEach{
            sender.send(it[0])
        }
    }
}

data class MoExSecuritiesResponse(
    val securities: Securities,
)

data class Securities(
    val columns: List<String>,
    val data: List<List<String>>,
)