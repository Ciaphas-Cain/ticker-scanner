package com.portfolio.wizard.tickerscanner.controller

import com.portfolio.wizard.tickerscanner.service.moex.MoExService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/scan")
class ScanController(@Autowired val moexSrvice: MoExService) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("moex")
    fun startMoExScan(): ResponseEntity<String> {
        logger.info { ">>> start moex scan started" }
        try {
            moexSrvice.getMoExTickers()
        } catch (e: Exception) {
            return ResponseEntity("Could not schedule moex scanning", HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity("Moex scanning was scheduled", HttpStatus.OK)
    }
}