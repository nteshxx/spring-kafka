package com.microservice.app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.microservice.app.constant.Topics;

@Configuration
public class KafkaConfiguration {

	@Bean
	NewTopic TxnsQueue() {
		return TopicBuilder.name(Topics.TXNS_QUEUE)
				.partitions(5)
				.replicas(1)
				.build();
	}

}
