package com.pnonyusa.sanlambanking.sanlambanking.config;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.regions.Region;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for AWS SNS client.
 *
 * - Uses values loaded from application.yml via AwsSnsProperties.
 * - Provides a singleton SnsClient bean configured with the specified AWS region.
 *
 * This approach ensures better environment flexibility and aligns with externalized config strategy.
 */

@Configuration
@RequiredArgsConstructor
public class SnsClientConfig {

    private final AwsSnsProperties snsProperties;
    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
                .region(Region.of(snsProperties.getRegion()))
                .build();
    }
}
