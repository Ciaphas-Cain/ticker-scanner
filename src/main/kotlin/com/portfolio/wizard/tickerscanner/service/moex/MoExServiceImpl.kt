package com.portfolio.wizard.tickerscanner.service.moex

import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class MoExServiceImpl(@Autowired val restTemplate: RestTemplate) : MoExService {
    private val logger = KotlinLogging.logger {}

    val gson = Gson()

    override fun getMoExTickers() {
        logger.info { ">>> getMoExTickers called" }
        val response = restTemplate.getForObject(
            MoexConsts.ALL_SECURITIES,
            String::class.java
        )
        val testModel = gson.fromJson(response, MoExSecuritiesResponse::class.java)
        print(testModel.securities.data[6])
    }
}

data class MoExSecuritiesResponse(
    val securities: Securities,
)

data class Securities(
    val columns: List<String>,
    val data: List<List<String>>,
)