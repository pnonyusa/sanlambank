package com.pnonyusa.sanlambanking.sanlambanking.service;

import software.amazon.awssdk.services.sns.model.PublishRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pnonyusa.sanlambanking.sanlambanking.model.WithdrawalEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Phaphamani Nonyusa
 * Event publisher that sends withdrawal events to an AWS SNS topic.
 *
 * Converts the event to JSON and publishes it using AWS SDK's SnsClient.
 */

@Component
@RequiredArgsConstructor
public class SnsEventPublisher implements EventPublisher{

    private static final Logger logger = LoggerFactory.getLogger(SnsEventPublisher.class);
    private final SnsClient snsClient;
    private final String topicArn = "arn:aws:sns:af-south-1:PNONYUSA_TEST_ID:SANLAM_BANK_TOPIC";


    @Override
    @SneakyThrows
    public void publish(WithdrawalEvent event) {
        try {
            String message = new ObjectMapper().writeValueAsString(event);
            PublishRequest request = PublishRequest.builder()
                    .topicArn(topicArn)
                    .message(message)
                    .build();

            snsClient.publish(request);

            logger.info("Withdrawal event published: {}", message);

        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize event");
        } catch (SnsException e) {
            logger.error("Failed to publish SNS message");
        }
    }
}
