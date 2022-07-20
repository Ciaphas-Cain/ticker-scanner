# Ticker scanner
Ticker scanner is a service for getting tickers from Moscow Exchange and passing them to rabbitmq for further processing.

### Prerequisites
* run rabbit via `docker run -d -p 5672:5672 -p 15672:15672 --name my-rabbit rabbitmq:3-management
  ` command
### How to use
For triggering the whole process of scanning '/scan/moex' endpoint should be used