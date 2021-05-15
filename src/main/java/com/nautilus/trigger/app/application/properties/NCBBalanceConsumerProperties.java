package com.nautilus.trigger.app.application.properties;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NCBBalanceConsumerProperties {
	
	private String topicName;
	private String groupId;
	private Set<String> events;

}
