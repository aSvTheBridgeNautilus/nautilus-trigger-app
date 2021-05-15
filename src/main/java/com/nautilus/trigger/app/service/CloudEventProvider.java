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

@Service
@RequiredArgsConstructor
public class CloudEventProvider implements ICLoudEventProvider {
	
	private final KafkaTemplate<String, CloudEvent> kafkaTemplate;
	private final ApplicationProperties applicationProperties;
	private final ObjectMapper objectMapper;

	@Override
	public void publish(PublishedEventRequest request) {
		// Build the event
		
		CloudEvent event = CloudEventBuilder
				.v1()
				.withExtension("payloadversion", "0.0.1")
				.withId(UUID.randomUUID().toString())
				.withType(request.getEventType())
				.withSource(request.getSource())
				.withDataContentType("application/json")
				.withTime(Instant.now().atOffset(ZoneOffset.UTC))
				.withData(PojoCloudEventData.wrap(request, objectMapper::writeValueAsBytes))
				.build();
		
		kafkaTemplate.send(applicationProperties.getNcbBalanceConsumer().getTopicName(), event);

	}

}
