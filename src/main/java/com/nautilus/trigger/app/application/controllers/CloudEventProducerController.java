package com.nautilus.trigger.app.application.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nautilus.trigger.app.application.dto.record.DuplicateP2PTransfersRecord;
import com.nautilus.trigger.app.application.dto.record.NCBCashOutNewRecord;
import com.nautilus.trigger.app.application.dto.record.NCBTransferCashOutFailureRecord;
import com.nautilus.trigger.app.service.CloudEventProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/trigger-app")
@RequiredArgsConstructor
@Slf4j
public class CloudEventProducerController {
	
	private final CloudEventProvider eventProvider;
	
	/*
	 * Duplicate balance_transfers
	 * */
	@PostMapping(value = "/publish/transfer/duplicate_transfer")
	public ResponseEntity<Object> produceDuplicateTransfersFromTransferStreamProccesor(
			@RequestBody DuplicateP2PTransfersRecord payloadRequest){
		log.info("[CloudEventProducerController:produceNCBTransferCashOutFailure] NCB Transfer Cash-Out Failure started");
		
		eventProvider.publishDuplicateTransfersFromTransferStreamProccesor(payloadRequest);
		
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("NCB Transfer Cash-Out Failure", "Message Sent!");
		responseBody.put("Event Type", payloadRequest.getEventType());
		responseBody.put("Payload", payloadRequest);
		
		return ResponseEntity.ok(responseBody);
	}
	
	/*
	 * Story - 1952, 2132
	 * */
	@PostMapping(value = "/publish/ncb/cash-out/failure")
	public ResponseEntity<Object> produceNCBTransferCashOutFailure(
			@RequestBody NCBTransferCashOutFailureRecord payloadRequest){
		log.info("[CloudEventProducerController:produceNCBTransferCashOutFailure] NCB Transfer Cash-Out Failure started");
		
		eventProvider.publishNCBTransferCashOutFailure(payloadRequest);
		
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("NCB Transfer Cash-Out Failure", "Message Sent!");
		responseBody.put("Event Type", payloadRequest.getEventType());
		responseBody.put("Payload", payloadRequest);
		
		return ResponseEntity.ok(responseBody);
	}
	
	/*
	 * Story - 1950
	 * */
	@PostMapping(value = "/publish/ncb/cash-out/new")
	public ResponseEntity<Object> produceNCBCashOutNew(
			@RequestBody NCBCashOutNewRecord payloadRequest){
		log.info("[CloudEventProducerController:produceNCBCashOutNew] NCB Cash-Out New started");
		
		eventProvider.publishNCBCashOutNew(payloadRequest);
		
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("NCB Cash-Out New", "Message Sent!");
		responseBody.put("Event Type", payloadRequest.getEventType());
		responseBody.put("Payload", payloadRequest);
		
		return ResponseEntity.ok(responseBody);
	}

}
