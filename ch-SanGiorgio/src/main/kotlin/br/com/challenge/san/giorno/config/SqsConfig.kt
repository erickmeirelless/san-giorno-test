package br.com.challenge.san.giorno.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import io.awspring.cloud.messaging.core.QueueMessagingTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Configuration
@Profile("!test")
open class SQSConfig {

    @Value("\${cloud.aws.region.static}")
    private lateinit var region: String

    @Value("\${cloud.aws.credentials.access-key}")
    private lateinit var accessKeyId: String

    @Value("\${cloud.aws.credentials.secret-key}")
    private lateinit var secretAccessKey: String

    @Value("\${cloud.aws.end-point.uri}")
    private lateinit var sqsUrl: String

    @Bean
    @Primary
    open fun amazonSQSAsync(): AmazonSQSAsync = AmazonSQSAsyncClientBuilder.standard()
        .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(sqsUrl, region))
        .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKeyId, secretAccessKey)))
        .build()

    @Bean
    fun queueMessagingTemplate(): QueueMessagingTemplate = QueueMessagingTemplate(amazonSQSAsync())

    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper()
}