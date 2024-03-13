package br.com.challenge.san.giorno.localstack

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
class LocalStackIntegrationTest {

    @Container
    val localstack = LocalStackContainer().withServices(SQS)

    @Test
    fun testSqsIntegration() {
        val sqsEndpoint = localstack.getEndpointOverride(SQS)

        val sqsClient: AmazonSQS = AmazonSQSClientBuilder.standard()
            .withEndpointConfiguration(
                AwsClientBuilder.EndpointConfiguration(sqsEndpoint.toString(), localstack.region)
            )
            .build()
    }
}
