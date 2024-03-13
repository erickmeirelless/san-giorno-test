package br.com.challenge.san.giorno.service

import br.com.challenge.san.giorno.dto.PaymentRequest
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.model.SendMessageRequest
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import io.awspring.cloud.messaging.core.QueueMessagingTemplate
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import java.util.UUID


@Service
class SQSEventPublisher {

    private val logger = LoggerFactory.getLogger(SQSEventPublisher::class.java)

    @Autowired
    private lateinit var queueMessagingTemplate: QueueMessagingTemplate

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    fun publishEvent(queueName: String, message: PaymentRequest) {
        logger.info("Generating event : {}", message)
        try {
            val messagePayload = objectMapper.writeValueAsString(message)
            queueMessagingTemplate.convertAndSend(queueName, messagePayload)
            logger.info("Event has been published in SQS.")
        } catch (e: Exception) {
            logger.error("Exception occurred while pushing event to sqs : {} and stacktrace ; {}", e.message, e)
        }
    }
}
