package com.nautilus.trigger.app.application.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nautilus.trigger.app.application.dto.record.NCBTransferCashOutFailureRecord;
import com.nautilus.trigger.app.service.CloudEventProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/trigger-app")
@RequiredArgsConstructor
@Slf4j
public class NCBCloudEventProducerController {
	
	private final CloudEventProvider eventProvider;
	
	@PostMapping(value = "/publish")
	public ResponseEntity<Object> produceNCBTransferCashOutFailure(
			@RequestBody NCBTransferCashOutFailureRecord payloadRequest){
		log.info("[NCBCloudEventProducerController:produceNCBTransferCashOutFailure] NCB Transfer Cash-Out Failure started");
		
		eventProvider.publish(payloadRequest);
		
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("NCB Transfer Cash-Out Failure", "Message Sent!");
		responseBody.put("Event Type", payloadRequest.getEventType());
		responseBody.put("Payload", payloadRequest);
		
		return ResponseEntity.ok(responseBody);
	}

}
