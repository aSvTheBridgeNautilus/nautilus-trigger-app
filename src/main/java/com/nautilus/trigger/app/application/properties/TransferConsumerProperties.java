package com.nautilus.trigger.app.application.properties;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TransferConsumerProperties {

  private String topicName;
  private String groupId;
  private Set<String> events;

}
