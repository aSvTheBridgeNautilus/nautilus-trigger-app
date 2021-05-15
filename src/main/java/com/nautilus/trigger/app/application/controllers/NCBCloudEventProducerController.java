package com.nautilus.trigger.app.application.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nautilus.trigger.app.application.dto.record.NCBTransferCashOutFailureRecord;
import com.nautilus.trigger.app.service.CloudEventProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/trigger-app")
@RequiredArgsConstructor
public class NCBCloudEventProducerController {
	
	private final CloudEventProvider eventProvider;
	
	public ResponseEntity<Object> produceNCBTransferCashOutFailure(
			@RequestBody NCBTransferCashOutFailureRecord payloadRequest){
		
		eventProvider.publish(payloadRequest);
		
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("NCB Transfer Cash-Out Failure", "Message Sent!");
		responseBody.put("Payload", payloadRequest);
		
		return ResponseEntity.ok(responseBody);
	}

}
