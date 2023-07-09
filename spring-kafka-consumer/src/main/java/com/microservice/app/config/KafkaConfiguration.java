package com.microservice.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.messaging.Message;

import com.microservice.app.constant.Topics;
import com.microservice.app.entity.Transaction;

@Configuration
public class KafkaConfiguration {

	@Bean
	JsonMessageConverter jsonMessageConverter() {
		return new JsonMessageConverter();
	}

	@KafkaListener(topics = Topics.TXNS_QUEUE, groupId = Topics.TXNS_GROUP)
	public void onTxnsQueue(Message<Transaction> newTransaction) {
		System.out.println("--------------------------------------------------------");
		System.out.println("New Transaction Received: " + newTransaction.getPayload());
		newTransaction.getHeaders().forEach((String s, Object o) -> System.out.println(s + " = " + o));
		System.out.println("--------------------------------------------------------");
	}

}
