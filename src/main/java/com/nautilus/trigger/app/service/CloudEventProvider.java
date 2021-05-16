package com.nautilus.trigger.app.service;

import java.net.URI;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nautilus.trigger.app.application.dto.interfaces.PublishedEventRequest;
import com.nautilus.trigger.app.application.properties.ApplicationProperties;
import com.nautilus.trigger.app.service.interfaces.ICLoudEventProvider;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.core.data.PojoCloudEventData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudEventProvider implements ICLoudEventProvider {
	
	private final KafkaTemplate<String, CloudEvent> kafkaTemplate;
	private final ApplicationProperties applicationProperties;
	private final ObjectMapper objectMapper;
	
	private CloudEvent buildCloudEventFrom(PublishedEventRequest request) {
		log.info("[CloudEventProvider:buildCloudEventFrom] building event for " 
							+ request.getEventType()
							+ " "
							+ request.getSource());
		
		return CloudEventBuilder
				.v1()
				.withExtension("payloadversion", "0.0.1")
				.withId(UUID.randomUUID().toString())
				.withType(request.getEventType())
				.withSource(request.getSource())
				.withDataContentType("application/json")
				.withTime(Instant.now().atOffset(ZoneOffset.UTC))
				.withData(PojoCloudEventData.wrap(request, objectMapper::writeValueAsBytes))
				.build();
	}

	@Override
	public void publishNCBTransferCashOutFailure(PublishedEventRequest request) {
		// Build the event
		
		CloudEvent event = buildCloudEventFrom(request);
		
		// Balance stream procesor
		log.info("[CloudEventProvider:publishNCBTransferCashOutFailure] Story - 1952: sending message to Balance");
		kafkaTemplate.send(applicationProperties.getNcbBalanceConsumer().getTopicName(), event);
		// Transfer stream procesor
		log.info("[CloudEventProvider:publishNCBTransferCashOutFailure] Story - 2132: sending message to Transfer");
		kafkaTemplate.send(applicationProperties.getTransferConsumer().getTopicName(), event);
		
	}
	
	public void publishNCBCashOutNew(PublishedEventRequest request) {
		// Build the event
		
		CloudEvent event = buildCloudEventFrom(request);

		// Balance stream procesor
		log.info("[CloudEventProvider:publishNCBTransferCashOutFailure] Story - 1950: sending message to Transfer");
		kafkaTemplate.send(applicationProperties.getTransferConsumer().getTopicName(), event);

	}

}
