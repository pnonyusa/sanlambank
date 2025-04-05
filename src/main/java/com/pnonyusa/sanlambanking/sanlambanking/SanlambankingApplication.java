package com.pnonyusa.sanlambanking.sanlambanking;

import com.pnonyusa.sanlambanking.sanlambanking.config.AwsSnsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AwsSnsProperties.class)
public class SanlambankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanlambankingApplication.class, args);
	}

}
