package com.microservice.app.utility;

import java.time.LocalDateTime;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.random.RandomDataGenerator;

import com.microservice.app.entity.Transaction;

public class RandomTxBuilder {
	
	public static Transaction build() {
		var txnId = RandomStringUtils.randomAlphanumeric(10);
		var userId = new RandomDataGenerator().nextLong(1L, 10L);
		var types = "DEBIT,CREDIT,INTEREST,SETTLEMENT".split(",");
		var typeId = new Random().nextInt(types.length);
		var txType = types[typeId];
		var amount = new RandomDataGenerator().nextUniform(1D, 100D);
		var txnDate = LocalDateTime.now();
		
		return new Transaction(txnId, userId, txType, amount, txnDate);
	}
	
}
