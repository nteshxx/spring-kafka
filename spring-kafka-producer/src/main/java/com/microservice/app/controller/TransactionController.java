package com.microservice.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.app.constant.Topics;
import com.microservice.app.utility.RandomTxBuilder;
import com.microservice.app.utility.ResponseBuilder;

@RestController
@RequestMapping("/api/tx")
public class TransactionController {

	@Autowired
	private KafkaTemplate<Object, Object> kafkaTemplate;

	@PostMapping("/send-new")
	public ResponseEntity<Object> sendNewRandomTx() {
		try {
			var newTx = RandomTxBuilder.build();
			var result = kafkaTemplate.send(Topics.TXNS_QUEUE, newTx);
			result.whenComplete((r, e) -> {
				if (e == null) {
					System.out.println("------------------------------------------------------");
					System.out.println("Offset: " + r.getRecordMetadata().offset() + ", Tx: " + newTx);
					System.out.println("------------------------------------------------------");
				} else {
					System.out.println("Failed: " + e.getMessage());
				}
			});
			return ResponseBuilder.build(HttpStatus.OK, "Message Sent Successfully", null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBuilder.build(HttpStatus.INTERNAL_SERVER_ERROR, "Message Sending Failed", e.getMessage(), e.getStackTrace());
		}
	}

}
