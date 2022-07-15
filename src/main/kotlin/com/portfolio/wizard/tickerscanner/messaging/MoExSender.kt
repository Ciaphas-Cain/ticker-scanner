package com.portfolio.wizard.tickerscanner.messaging

interface MoExSender {
    fun send(ticker: String)
}