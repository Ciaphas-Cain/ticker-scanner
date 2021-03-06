package com.portfolio.wizard.tickerscanner.controller

import com.ninjasquad.springmockk.MockkBean
import com.portfolio.wizard.tickerscanner.service.moex.MoExService
import io.mockk.every
import io.mockk.justRun
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ScanControllerTests(@Autowired val restTemplate: TestRestTemplate) {
    @MockkBean
    private lateinit var moexService: MoExService

    @BeforeEach
    fun setup() {
        println(">>> ScanControllerTests setup")
        justRun { moexService.getMoExTickers() }
    }

    @Test
    fun `Assert startMOEX status code`() {
        println(">>> Assert startMOEX status code")
        val response = restTemplate.getForEntity("/scan/moex", String::class.java)
        assertThat(response?.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `Verify moexService is called during scanning moex`() {
        println(">>> Verify moexService is called during scanning moex")
        restTemplate.getForEntity("/scan/moex", String::class.java)
        verify { moexService.getMoExTickers() }
    }

    @Test
    fun `Verify INTERNAL_SERVER_ERROR is returned in case of error`() {
        println(">>> Verify moexService return 500 code")
        every { moexService.getMoExTickers() } throws Exception("test")
        val response = restTemplate.getForEntity("/scan/moex", String::class.java)
        assertThat(response?.statusCode).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
    }

}