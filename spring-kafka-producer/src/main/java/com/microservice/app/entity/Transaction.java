package com.microservice.app.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	String id;

	Long userId;

	String type;

	Double amount;

	LocalDateTime dateTime;

}
