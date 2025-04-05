package com.pnonyusa.sanlambanking.sanlambanking.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Phaphamani Nonyusa
 * Binds properties from application.yml under `aws.sns`
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "aws.sns")
public class AwsSnsProperties {
    private String region;
    private String topicArn;
}
